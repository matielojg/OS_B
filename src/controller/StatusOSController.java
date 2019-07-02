package controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

	@GET
	@Path("listTecnicoPorStatus")
	@Produces(MediaType.APPLICATION_JSON)
	public List<StatusOSModel> listTecnicoPorStatus(	
			@QueryParam("statusid") long statusid,
			@QueryParam("statusid1") long statusid1,
			@QueryParam("statusid2") long statusid2,
			@QueryParam("pagina") int pagina, 
			@QueryParam("limitePorPagina") int limitePorPagina) {
	
		try {
			 
			List<StatusOSModel> lista = dao.listTechnicalByStatus(statusid,statusid1,statusid2,pagina,limitePorPagina);
			return lista;	
		} catch (Exception e) {
			throw e;
		}
		
	}
	
}
