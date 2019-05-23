package controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.ServiceDAO;
import model.ServiceModel;

@Path("service")
public class ServiceController {
	private ServiceDAO dao = new ServiceDAO();

	@GET
	@Path("listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ServiceModel> listar() {
		
		
		try {
			List<ServiceModel> lista = dao.listaAll(ServiceModel.class);
			return lista;	
		} catch (Exception e) {
			System.out.println("deu pau no service: ");
			throw e;
		}
		
	}
}
