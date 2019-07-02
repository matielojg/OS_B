package DAO;

import java.util.List;
import javax.persistence.Query;
import model.FunctionModel;
import model.UserModel;

@SuppressWarnings("rawtypes")
public class UserDAO extends DAO {

	@SuppressWarnings("unchecked")
	public List<UserModel> listPaginado(int pagina, int limitePorPagina) {
		manager.clear(); // limpeza de cache de queries
		Query query = manager.createQuery("Select f from UserModel f ");

		query.setMaxResults(limitePorPagina);
		query.setFirstResult(pagina * limitePorPagina - limitePorPagina);
		return query.getResultList();
	}

	public List<UserModel> listUserFunction(long id) {
		manager.clear(); // limpeza de cache de queries
		Query query = manager
				.createQuery("Select f from UserModel f WHERE f.function = :idtecnico ");
		FunctionModel idtecnico = new FunctionModel();
		idtecnico.setId(id);
		query.setParameter("idtecnico", idtecnico);
		return query.getResultList();
	}
	
	public UserModel findById(long id) {
		Object retorno = null;
		try {
			manager.clear(); // limpeza de cache de queries
			Query query = manager.createQuery("Select c from UserModel c WHERE c.id = :id");
			query.setParameter("id", id);
			query.setMaxResults(1);
			retorno = query.getSingleResult();
			if (retorno != null) {
				return (UserModel) retorno; 
				}
			else 
				return null;
			
			

		} catch (Exception ex) {
			return null;
		}
	}

}
