package controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.SectorDAO;
import model.SectorModel;

@Path("sector")
public class SectorController {
	private SectorDAO dao = new SectorDAO();

	@GET
	@Path("listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SectorModel> listar() {

		try {
			List<SectorModel> lista = dao.listaAll(SectorModel.class);
			return lista;
		} catch (Exception e) {
			System.out.println("deu pau no sector: ");
			throw e;
		}

	}
}
