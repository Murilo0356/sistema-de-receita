package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException; 
import model.Usuario;

public class UsuarioDao extends GenericDao<Usuario> {

    public UsuarioDao() {
        super(Usuario.class);
    }

    public List<Usuario> listarTodos() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }


    public Usuario buscarPorEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                     .setParameter("email", email)
                     .getSingleResult();
        } catch (NoResultException execao) {
            return null; 
        } finally {
            em.close();
        }
    }
}