package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.PlanoDeRefeicao;

public class PlanoDeRefeicaoDao extends GenericDao<PlanoDeRefeicao> {

    public PlanoDeRefeicaoDao() {
        super(PlanoDeRefeicao.class);
    }

    public List<PlanoDeRefeicao> listarTodos() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM PlanoDeRefeicao p", PlanoDeRefeicao.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    public List<PlanoDeRefeicao> listarPorUsuario(long idUsuario) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM PlanoDeRefeicao p WHERE p.usuario.id = :id", PlanoDeRefeicao.class)
                     .setParameter("id", idUsuario)
                     .getResultList();
        } finally {
            em.close();
        }
    }
 
    public long contarPlanosDoUsuario(long idUsuario) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT COUNT(p) FROM PlanoDeRefeicao p WHERE p.usuario.id = :id", Long.class)
                     .setParameter("id", idUsuario)
                     .getSingleResult();
        } finally {
            em.close();
        }
    }
}