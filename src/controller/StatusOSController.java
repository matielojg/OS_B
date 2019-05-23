package controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.StatusOSDAO;
import model.StatusOSModel;

//ARRUMA O FORMULARIO DE INSERÇÃO Q TÁ COM A PRIORIDADE COMO INPUT
@Path("status")
public class StatusOSController {
	private StatusOSDAO dao = new StatusOSDAO();

	@GET
	@Path("listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<StatusOSModel> listar() {
		try {
			List<StatusOSModel> lista = dao.listaAll(StatusOSModel.class);
			return lista;	
		} catch (Exception e) {
			throw e;
		}
		
	}
}
