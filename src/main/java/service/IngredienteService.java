package service;

import dao.IngredienteDao;
import model.Ingrediente;
import model.InformacaoNutricional;
import java.util.List;

public class IngredienteService {

     IngredienteDao ingredienteDao =  new IngredienteDao();

    

    public List<Ingrediente> listarTodos() {
        return ingredienteDao.listarTodos();
    }

    public String criarIngrediente(String nome, double calorias, String descricaoNutricional) {
        
        if (nome == null || nome.isEmpty()) {
            return "Erro: Nome do ingrediente é obrigatório.";
        }

        if (ingredienteDao.buscarPorNome(nome) != null) {
            return "Erro: O ingrediente '" + nome + "' já está cadastrado.";
        }

        Ingrediente novo = new Ingrediente();
        novo.setDescricao(nome);

        InformacaoNutricional info = new InformacaoNutricional();
        info.setQuantidade(calorias);
        info.setDescricao(descricaoNutricional); 
        
        novo.setInformacaoNutricional(info);
        info.setIngrediente(novo); 

        try {
            ingredienteDao.salvar(novo);
            return "Ingrediente salvo com sucesso!";
        } catch (Exception execao) {
            return "Erro ao salvar: " + execao.getMessage();
        }
    }
    
 
    public Ingrediente buscarPorNome(String nome) {
        return ingredienteDao.buscarPorNome(nome);
    }
}