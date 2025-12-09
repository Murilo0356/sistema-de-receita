package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import model.Ingrediente;

public class IngredienteDao extends GenericDao<Ingrediente> {

    public IngredienteDao() {
        super(Ingrediente.class);
    }

    public List<Ingrediente> listarTodos() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT i FROM Ingrediente i", Ingrediente.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    public Ingrediente buscarPorNome(String nome) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT i FROM Ingrediente i WHERE LOWER(i.descricao) = LOWER(:nome)", Ingrediente.class)
                     .setParameter("nome", nome)
                     .getSingleResult();
        }  finally {
            em.close();
        }
    }
}