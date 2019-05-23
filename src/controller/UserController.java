package controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import DAO.UserDAO;
import model.UserModel;
import util.Utilitarios;

@Path("user")
public class UserController {
	private UserDAO dao = new UserDAO();

	
	@GET
	@Path("listarUser")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserModel> listarUser() {
		List<UserModel> lista = dao.listaAll(UserModel.class);
		return lista;
	}
	
	@GET
	@Path("listarUserPorStatus")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserModel> listarUserPorStatus() {
		List<UserModel> lista = dao.listaAll(UserModel.class);
		return lista;
	}
/*
	@GET
	@Path("listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserModel> listar(@QueryParam("pagina") int pagina,
			@QueryParam("limitePorPagina") int limitePorPagina) {
		// System.out.println("abc");
		List<UserModel> lista = dao.listPaginado(pagina, limitePorPagina);
		return lista;
		// return null;
	}
*/
	@POST
	@Path("salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void salvar(UserModel user) {
		System.out.println("entrou no salvar do back" + user);
		dao.save(user);
	}

	@PUT
	@Path("alterar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void alterar(UserModel user, @QueryParam("nomeArquivoAnterior") String nomeArquivoAnterior) {
		if (nomeArquivoAnterior != null) {
			String caminho = "/uploads/" + nomeArquivoAnterior;
			String caminhoCompleto = new Utilitarios().getRaizServidor() + caminho;
			new Utilitarios().deletarArquivo(caminhoCompleto);
		}
		dao.update(user);
	}

	@GET
	@Path("buscar")
	@Consumes(MediaType.APPLICATION_JSON)
	public UserModel buscarPorId(@QueryParam("id") long id) {
		return (UserModel) dao.findById(UserModel.class, id);
	}

	@DELETE
	@Path("deletar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deletarPorId(@QueryParam("id") long id) {
		UserModel user = (UserModel) dao.findById(UserModel.class, id);
		if (user.getFoto() != null) {
			String caminho = "/uploads/" + user.getFoto();
			String caminhoCompleto = new Utilitarios().getRaizServidor() + caminho;
			new Utilitarios().deletarArquivo(caminhoCompleto);
		}
		dao.delete(UserModel.class, id);
	}

}
