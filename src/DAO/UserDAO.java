package DAO;

import java.util.List;

import javax.persistence.Query;

import model.FormModel;
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

	public List<UserModel> listPaginadoPorFunctionEUsuario(long functionid, long usuarioid, int pagina, int limitePorPagina) {
		manager.clear(); // limpeza de cache de queries
		Query query = manager
				.createQuery("Select f from FormModel f WHERE f.status = :status OR f.status= :status1 AND f.userResponsible = :usuario");
		FunctionModel function = new FunctionModel();
		function.setId(functionid);
		UserModel usuario = new UserModel();
		usuario.setId(usuarioid);
		query.setParameter("function", functionid);
		query.setParameter("usuario", usuario);
		query.setMaxResults(limitePorPagina);
		query.setFirstResult(pagina * limitePorPagina - limitePorPagina);
		return query.getResultList();
	}
	
	
}
