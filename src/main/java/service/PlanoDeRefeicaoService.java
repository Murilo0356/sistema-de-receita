package service;

import dao.PlanoDeRefeicaoDao;
import model.PlanoDeRefeicao;
import model.Usuario;
import java.util.List;

public class PlanoDeRefeicaoService {

    private PlanoDeRefeicaoDao planoDao;

    public PlanoDeRefeicaoService() {
        this.planoDao = new PlanoDeRefeicaoDao();
    }

    public String criarPlano(PlanoDeRefeicao plano, Usuario usuario) {
        if (plano == null || usuario == null) {
            return "Erro: Plano e Usuário são obrigatórios.";
        }

        if (plano.getNome() == null || plano.getNome().isEmpty()) {
            return "Erro: O plano precisa de um nome.";
        }

        long qtdPlanosAtuais = planoDao.contarPlanosDoUsuario(usuario.getId());

        if (qtdPlanosAtuais >= 10) {
            return "Erro: Limite atingido! O usuário já possui 10 planos de refeição.";
        } else {
  
            
            plano.setUsuario(usuario);
            
            try {
                planoDao.salvar(plano);
                return "Plano de Refeição criado com sucesso!";
            } catch (Exception e) {
                return "Erro ao salvar plano: " + e.getMessage();
            }
        }
    }

    public List<PlanoDeRefeicao> listarPorUsuario(Usuario usuario) {
        if (usuario != null) {
            return planoDao.listarPorUsuario(usuario.getId());
        }
        return null;
    }
}