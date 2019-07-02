package DAO;

import java.util.List;

import javax.persistence.Query;


import model.StatusOSModel;


public class StatusOSDAO extends DAO {

	public StatusOSDAO() {
		// TODO Auto-generated constructor stub
	}

	public List<StatusOSModel> listTechnicalByStatus(long statusid, long statusid1, long statusid2,int pagina, int limitePorPagina) {
		//System.out.printf("id 1 2 3" + statusid, statusid1 ,statusid2 );
		manager.clear(); // limpeza de cache de queries
		Query query = manager.createQuery("Select f from StatusOSModel f WHERE (f.id = :status OR (f.id = :statusid1 OR f.id = :statusid2))");
		StatusOSModel status = new StatusOSModel();
		StatusOSModel status1 = new StatusOSModel();
		StatusOSModel status2 = new StatusOSModel();
		status.setId(statusid);
		status1.setId(statusid1);
		status2.setId(statusid2);
		query.setParameter("status", status);
		query.setParameter("status1", status1);
		query.setParameter("status2", status2);
		query.setMaxResults(limitePorPagina);
		query.setFirstResult(pagina * limitePorPagina - limitePorPagina);
		return query.getResultList();
	}

}
