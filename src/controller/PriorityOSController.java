package controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.PriorityOSDAO;
import model.PriorityOSModel;

//salva as paradas q tão coisadas e testa
@Path("priority")
public class PriorityOSController {
	private PriorityOSDAO dao = new PriorityOSDAO();

	@GET
	@Path("listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PriorityOSModel> listar() {

		try {
			List<PriorityOSModel> lista;
			lista = dao.listaAll(PriorityOSModel.class);
			return lista;
		} catch (Exception e) {
			System.out.println("deu pau no priority: ");
			throw e;
		}

	}
}
