package controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.tomcat.dbcp.dbcp2.ConnectionFactory;

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
	@Path("listarUserPorFuncao") // usar para atribuir tecnico a ordem
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserModel> listarUserPorFuncao(@QueryParam("id") long id) {
		List<UserModel> lista = dao.listUserFunction(id);
		return lista;
	}
	/*
	 * @GET
	 * 
	 * @Path("listar")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public List<UserModel>
	 * listar(@QueryParam("pagina") int pagina,
	 * 
	 * @QueryParam("limitePorPagina") int limitePorPagina) { //
	 * System.out.println("abc"); List<UserModel> lista = dao.listPaginado(pagina,
	 * limitePorPagina); return lista; // return null; }
	 */

	public String converterParaMD5(String s) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(s.getBytes(), 0, s.length());
		String retorno = new BigInteger(1, m.digest()).toString(16);
		System.out.println(s + " em MD5 é: " + retorno);
		return retorno;
	}

	@POST
	@Path("salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void salvar(UserModel user) throws NoSuchAlgorithmException {
		System.out.println("entrou no salvar do back" + user);

		user.setSenha(converterParaMD5(user.getSenha()));

		dao.save(user);
	}

	@PUT
	@Path("alterar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void alterar(UserModel user, @QueryParam("nomeArquivoAnterior") String nomeArquivoAnterior) throws NoSuchAlgorithmException {
		if (nomeArquivoAnterior != null) {
			String caminho = "/uploads/" + nomeArquivoAnterior;
			String caminhoCompleto = new Utilitarios().getRaizServidor() + caminho;
			new Utilitarios().deletarArquivo(caminhoCompleto);
		}
		user.setSenha(converterParaMD5(user.getSenha()));
		dao.update(user);
		System.out.println("PASSOU POR FORA DO IF UPDATE USER");
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

	@GET
	@Path("buscar")
	@Consumes(MediaType.APPLICATION_JSON)
	public UserModel buscarPorId(@QueryParam("id") long id) {
		return (UserModel) dao.findById(UserModel.class, id);
	}
}
