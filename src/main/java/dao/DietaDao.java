package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Dieta;

public class DietaDao extends GenericDao<Dieta> {

	public DietaDao() {
		super(Dieta.class);
	}

	public List<Dieta> listarTodas() {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery("SELECT d FROM Dieta d", Dieta.class).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Object[]> relatorioDietasMaisPopulares() {
		EntityManager em = getEntityManager();
		try {
			String jpql = "SELECT d.descricao, COUNT(r) FROM Receita r " + "JOIN r.dieta d " + "GROUP BY d.descricao "
					+ "ORDER BY COUNT(r) DESC";

			return em.createQuery(jpql, Object[].class).getResultList();
		} finally {
			em.close();
		}
	}
}