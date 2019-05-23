
package DAO;

import java.util.List;

import javax.persistence.Query;

import model.FormModel;
import model.StatusOSModel;
import model.UserModel;

@SuppressWarnings("rawtypes")
public class FormDAO extends DAO {

	@SuppressWarnings("unchecked")
	public List<FormModel> listPaginado(int pagina, int limitePorPagina) {
		manager.clear(); // limpeza de cache de queries
		Query query = manager.createQuery("Select f from FormModel f WHERE f.dateclose is null");
		/*
		 * INNER JOIN FETCH f.sectorProvider INNER JOIN FETCH f.sectorRequester " +
		 * " INNER JOIN FETCH f.service LEFT JOIN FETCH f.userResponsible INNER JOIN FETCH f.userRequester"
		 * + " INNER JOIN FETCH f.action INNER JOIN FETCH  " + "INNER JOIN FETCH
		 * f.priority INNER JOIN FETCH f.status
		 */
		query.setMaxResults(limitePorPagina);
		query.setFirstResult(pagina * limitePorPagina - limitePorPagina);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<FormModel> listFormEncerradas(int pagina, int limitePorPagina) {
		manager.clear(); // limpeza de cache de queries
		Query query = manager.createQuery("Select f from FormModel f WHERE f.dateclose is not null");
		query.setMaxResults(limitePorPagina);
		query.setFirstResult(pagina * limitePorPagina - limitePorPagina);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<FormModel> listPaginadoPorFuncionario(long id, int pagina, int limitePorPagina) {
		manager.clear(); // listar usuario logado por id
		Query query = manager.createQuery("Select f from FormModel f WHERE f.userRequester = :funcionario");
		UserModel funcionario = new UserModel();
		funcionario.setId(id);
		query.setParameter("funcionario", funcionario);
		query.setMaxResults(limitePorPagina);
		query.setFirstResult(pagina * limitePorPagina - limitePorPagina);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<FormModel> listPaginadoPorStatus(long statusid, int pagina, int limitePorPagina) {
		manager.clear(); // limpeza de cache de queries
		Query query = manager.createQuery("Select f from FormModel f WHERE f.status = :status");
		StatusOSModel status = new StatusOSModel(); // aqui vc tá mapeando o parametro :status.. então tem q ser
		status.setId(statusid);
		query.setParameter("status", status);
		query.setMaxResults(limitePorPagina);
		query.setFirstResult(pagina * limitePorPagina - limitePorPagina);
		return query.getResultList();
	}

	public List<FormModel> listPaginadoPorStatusEUsuario(long statusid,long statusid1, long usuarioid, int pagina,
			int limitePorPagina) {
		manager.clear(); // limpeza de cache de queries
		Query query = manager
				.createQuery("Select f from FormModel f WHERE f.status = :status OR f.status= :status1 AND f.userResponsible = :usuario");
		StatusOSModel status = new StatusOSModel();
		StatusOSModel status1 = new StatusOSModel();
		status.setId(statusid);
		status1.setId(statusid1);
		UserModel usuario = new UserModel();
		usuario.setId(usuarioid);
		query.setParameter("status", status);
		query.setParameter("status1", status1);
		query.setParameter("usuario", usuario);
		query.setMaxResults(limitePorPagina);
		query.setFirstResult(pagina * limitePorPagina - limitePorPagina);
		return query.getResultList();
	}

}
