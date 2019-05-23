	package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO<T extends EntidadeBase> {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("dao-generico");
	protected static EntityManager manager = emf.createEntityManager();

	public T findById(Class<T> clazz, Long id) {
		manager.clear(); // limpeza de cache de queries
		return manager.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> listaAll(Class<T> clazz) {
		//manager.clear(); // limpeza de cache de queries
		return manager.createQuery("Select t from " + clazz.getSimpleName() + " t").getResultList();
	}

	public boolean save(T obj) {
		try {
			manager.clear(); // limpeza de cache de queries
			manager.getTransaction().begin();
			manager.persist(obj);
			manager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			manager.getTransaction().rollback();
			return false;
		}
		return true;
	}

	public boolean update(T obj) {
		try {
			manager.clear(); // limpeza de cache de queries
			manager.getTransaction().begin();
			manager.merge(obj);
			manager.getTransaction().commit();
						
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		}
		return true;
	}

	public boolean delete(Class<T> clazz, Long id) {
		manager.clear(); // limpeza de cache de queries
		T t = findById(clazz, id);
		try {
			manager.getTransaction().begin();
			manager.remove(t);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			return false;
		}
		return true;
	}

}