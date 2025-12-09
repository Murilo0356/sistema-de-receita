package service;

import dao.UsuarioDao;
import model.Usuario;

public class UsuarioService {

    private UsuarioDao usuarioDao;

    public UsuarioService() {
        this.usuarioDao = new UsuarioDao();
    }

    public String cadastrarUsuario(Usuario usuario) {
        
        if (usuario == null) {
            return "Erro: Usuário inválido.";
        }
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            return "Erro: Nome é obrigatório.";
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            return "Erro: E-mail é obrigatório.";
        }
        if (usuario.getSenha() == null || usuario.getSenha().length() < 3) {
            return "Erro: A senha deve ter pelo menos 3 caracteres.";
        }

        if (usuarioDao.buscarPorEmail(usuario.getEmail()) != null) {
            return "Erro: Já existe um usuário cadastrado com este e-mail.";
        }

        try {
            usuarioDao.salvar(usuario);
            return "Usuário " + usuario.getNome() + " cadastrado com sucesso!";
        } catch (Exception execao) {
            return "Erro técnico: " +execao.getMessage();
        }
    }

    public Usuario fazerLogin(String email, String senha) {
        if (email == null || email.isEmpty() || senha == null || senha.isEmpty()) {
            return null; 
        }

        Usuario usuarioEncontrado = usuarioDao.buscarPorEmail(email);
        
      
        if (usuarioEncontrado != null && usuarioEncontrado.getSenha().equals(senha)) {
            return usuarioEncontrado; 
        }
        
        return null;
    }
}