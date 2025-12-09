package dao;

import javax.persistence.EntityManager;

public abstract class GenericDao<T> {

	private final Class<T> classe;

	public GenericDao(Class<T> classe) {
		this.classe = classe;
	}

	protected EntityManager getEntityManager() {
		return JPAUtil.getEntityManager();
	}

	public T salvar(T entidade) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entidade);
			em.getTransaction().commit();
			return entidade;
		} finally {
			em.close();
		}
	}

	public T atualizar(T entidade) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			entidade = em.merge(entidade);
			em.getTransaction().commit();
			return entidade;
		} finally {
			em.close();
		}
	}

	public void remover(long id) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			T entidade = em.find(classe, id);
			if (entidade != null) {
				em.remove(entidade);
			}
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public T buscarPorId(long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(classe, id);
		} finally {
			em.close();
		}
	}
}