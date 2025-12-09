package service;

import dao.DietaDao;
import model.Dieta;
import java.util.List;

public class DietaService {

  DietaDao dietaDao  = new DietaDao();

 

    public List<Dieta> listarTodas() {
        return dietaDao.listarTodas();
    }
    
    public List<Object[]> gerarRelatorioPopularidade() {
        return dietaDao.relatorioDietasMaisPopulares();
    }


    public String cadastrarDieta(Dieta dieta) {
        
      
        
        if (dieta == null) {
            return "Erro: A dieta não pode ser vazia.";
        }

        if (dieta.getTipo() == null) {
            return "Erro: É obrigatório selecionar um TIPO de dieta (ex: Vegana, Low Carb).";
        }

        if (dieta.getDescricao() == null || dieta.getDescricao().trim().isEmpty()) {
            return "Erro: A descrição da dieta é obrigatória.";
        }

        
        try {
            dietaDao.salvar(dieta);
            return "Dieta '" + dieta.getDescricao() + "' cadastrada com sucesso!";
        } catch (Exception execao) {
            
            return "Erro ao salvar: Verifique se essa dieta ou tipo já não estão cadastrados. Detalhe: " + execao.getMessage();
        }
    }
}