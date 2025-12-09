package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Receita;
import Enums.TipoDieta;

public class ReceitaDao extends GenericDao<Receita> {

	public ReceitaDao() {
		super(Receita.class);
	}

	public List<Receita> listarTodas() {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery("SELECT r FROM Receita r", Receita.class).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Receita> buscarPorTipoDieta(TipoDieta tipo) {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery("SELECT r FROM Receita r WHERE r.dieta.tipo = :tipo", Receita.class)
					.setParameter("tipo", tipo).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Receita> buscarPorAutor(long idUsuario) {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery("SELECT r FROM Receita r WHERE r.autor.id = :id", Receita.class)
					.setParameter("id", idUsuario).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Receita> buscarPorTitulo(String termo) {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery("SELECT r FROM Receita r WHERE LOWER(r.titulo) LIKE LOWER(:termo)", Receita.class)
					.setParameter("termo", "%" + termo + "%").getResultList();
		} finally {
			em.close();
		}
	}

	public List<Receita> buscarReceitasMaisPopulares(int limite) {
		EntityManager em = getEntityManager();
		try {

			String jpql = "SELECT r FROM PlanoReceita pr " + "JOIN pr.receita r " + "GROUP BY r "
					+ "ORDER BY COUNT(pr) DESC";

			return em.createQuery(jpql, Receita.class).setMaxResults(limite) // Ex: Trazer só as top 5
					.getResultList();
		} finally {
			em.close();
		}
	}
}