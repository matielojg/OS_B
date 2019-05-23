package controller;

import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import DAO.LoginDAO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.LoginModel;
import model.UserModel;

@Path("login")
public class LoginController {

	private @Context HttpServletRequest request;

	private LoginDAO dao = new LoginDAO();

	@GET
	@Path("checar")
	@Produces(MediaType.APPLICATION_JSON)
	public UserModel checar() {

		HttpSession sessao = request.getSession();
		Object usuarioLogado = sessao.getAttribute("abcde");
		System.out.println("o objeto: ");
		System.out.println(usuarioLogado);
		if (usuarioLogado != null) {
			System.out.println("checou e encontrou no back... devolvendo: "
					+ ((UserModel) sessao.getAttribute("abcde")).getNome());
			return (UserModel) usuarioLogado;
		} else {
			System.out.println("Não encontrou nada no metodo login checar ");
			return null;
		}

	}

	@GET
	@Path("deslogar")
	public void deslogar() {

		HttpSession sessao = request.getSession();
		sessao.removeAttribute("abcde");
		System.out.println("deslogou no back... a sessão ficou nula");

	}

	@POST
	@Path("validar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserModel autentica(LoginModel usuario) {
		System.out.println("entrou normal");
		UserModel usuarioLogado = dao.findByLoginAndSenha(usuario.getLogin(), usuario.getSenha());

		if (usuarioLogado != null) {
			System.out.println("Entrou aqui, então  o usuarioLogado não é nulo");

			long tempo = System.currentTimeMillis();
			String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, usuarioLogado.getSenha())
					.setSubject(usuarioLogado.getLogin()).setIssuedAt(new Date(tempo))
					.setExpiration(new Date(tempo + 900000)).claim("email", usuarioLogado.getEmail()).compact();
			JsonObject json = Json.createObjectBuilder().add("JWT", jwt).build();
			System.out.println("TOKEN" + jwt);
			HttpSession sessao = request.getSession();
			sessao.setAttribute("abcde", usuarioLogado);
			System.out.println(
					"logou no back... a sessão ficou assim: " + ((UserModel) sessao.getAttribute("abcde")).getNome());
			return usuarioLogado;
			

		} else {
			System.out.println("Entrou aqui, então  o usuarioLogado TÁ NULO!");
			return null;
			
		}

	}

}
