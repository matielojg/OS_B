package controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.FunctionDAO;
import model.FunctionModel;

@Path("function")
public class FunctionController {
	private FunctionDAO dao = new FunctionDAO();

	@GET
	@Path("listarFunction")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FunctionModel> listar() {
		List<FunctionModel> lista = dao.listaAll(FunctionModel.class);
		return lista;

	}

}