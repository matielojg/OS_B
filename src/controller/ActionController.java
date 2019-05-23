package controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.ActionDAO;
import model.ActionModel;

@Path("action")
public class ActionController {
	private ActionDAO dao = new ActionDAO();

	@GET
	@Path("listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ActionModel> listar() {
		List<ActionModel> lista = dao.listaAll(ActionModel.class);
		return lista;
	}

	public void registrarNovaAcao(ActionModel acao) {
		System.out.println("tá alterando a ação");
		dao.save(acao);
	}
	
	public void registrarSalvaAcao(ActionModel acao1) {
		System.out.println("tá salvando a ação");
		dao.save(acao1);
	}
}

