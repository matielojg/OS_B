package controller;

import java.sql.Timestamp;
import java.util.Date;
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

import DAO.FormDAO;
import model.ActionModel;
import model.FormModel;
import model.UserModel;
import util.Utilitarios;

@Path("form")
public class FormController {
	private FormDAO dao = new FormDAO();

	@GET
	@Path("listarPorFuncionario")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FormModel> listarPorFuncionario(
			@QueryParam("id")long id, 
			@QueryParam("pagina") int pagina,
			@QueryParam("limitePorPagina") int limitePorPagina) {
		List<FormModel> lista = dao.listPaginadoPorFuncionario(id, pagina, limitePorPagina);
		return lista;
	}

	@GET
	@Path("listarPorStatus")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FormModel> listarPorStatus(
			@QueryParam("statusid") long statusid, 
			@QueryParam("pagina") int pagina,
			@QueryParam("limitePorPagina") int limitePorPagina) {
		List<FormModel> lista = dao.listPaginadoPorStatus(statusid, pagina, limitePorPagina);
		return lista;
	}

	@GET
	@Path("listarPorStatusEUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FormModel> listarPorStatusEUsuario(
			@QueryParam("statusid") long statusid,
			@QueryParam("usuarioid") long usuarioid,
			@QueryParam("pagina") int pagina, 
			@QueryParam("limitePorPagina") int limitePorPagina) {
		List<FormModel> lista = dao.listPaginadoPorStatusEUsuario(statusid, usuarioid, pagina,
				limitePorPagina);
		return lista;
	}
	
	@GET
	@Path("listarPor2StatusEUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FormModel> listarPor2StatusEUsuario(
			@QueryParam("statusid") long statusid,
			@QueryParam("statusid1") long statusid1, 
			@QueryParam("usuarioid") long usuarioid,
			@QueryParam("pagina") int pagina, 
			@QueryParam("limitePorPagina") int limitePorPagina) {
		List<FormModel> lista = dao.listPaginadoPor2StatusEUsuario(statusid, statusid1, usuarioid, pagina,
				limitePorPagina);
		return lista;
	}
	@GET
	@Path("listarPorStatusPendenteSupenso")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FormModel> listarPorStatusPendenteSupenso(
			@QueryParam("statusid") long statusid,
			@QueryParam("statusid1") long statusid1, 
			@QueryParam("pagina") int pagina, 
			@QueryParam("limitePorPagina") int limitePorPagina) {
		List<FormModel> lista = dao.listPaginadoPorStatusSupervisor(statusid, statusid1, pagina,
				limitePorPagina);
		return lista;
	}

	@GET
	@Path("listarFormEncerradas") //BUSCA AS QUE O DATECLOSE IS NOT NULL
	@Produces(MediaType.APPLICATION_JSON)
	public List<FormModel> listarFormEncerradas(
			@QueryParam("pagina") int pagina,
			@QueryParam("limitePorPagina") int limitePorPagina) {
		List<FormModel> lista = dao.listFormEncerradas(pagina, limitePorPagina);
		return lista;
	}

	@GET
	@Path("listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FormModel> listar(
			@QueryParam("pagina") int pagina,
			@QueryParam("limitePorPagina") int limitePorPagina) {
		List<FormModel> lista = dao.listPaginado(pagina, limitePorPagina);
		return lista;
	}

	@POST
	@Path("salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void salvar(FormModel form) {
		System.out.println("chegou no back...");
		form.setDateopen(new Timestamp(new Date().getTime()));
		System.out.println(form.getDateopen());
		dao.save(form);
	}

	@PUT
	@Path("alterar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void alterar(FormModel formAtual, 
			@QueryParam("nomeArquivoAnterior") String nomeArquivoAnterior,
			@QueryParam("idUsuarioLogado") long idUsuarioLogado) {
		if (nomeArquivoAnterior != null) {
			String caminho = "/uploads/" + nomeArquivoAnterior;
			String caminhoCompleto = new Utilitarios().getRaizServidor() + caminho;
			new Utilitarios().deletarArquivo(caminhoCompleto);
		}

		// LÓGICA ESPECÍFICA PARA O DATECLOSE
		FormModel formAntesDeSalvar = (FormModel) dao.findById(FormModel.class, formAtual.getId());
		if (formAntesDeSalvar.getStatus().getId() != 4 && formAntesDeSalvar.getStatus().getId() != 7) {
			System.out.println("entrou no A - atual" + formAtual.getStatus().getId() + " antigo - "
					+ formAntesDeSalvar.getStatus().getId());
			if (formAtual.getStatus().getId() == 4 || formAtual.getStatus().getId() == 7)
				formAtual.setDateclose(new Timestamp(new Date().getTime()));
			else
				formAtual.setDateclose(null);
		}
		
		else {
			System.out.println("entrou no B - atual" + formAtual.getStatus().getId() + " antigo - "
					+ formAntesDeSalvar.getStatus().getId());
			if (formAntesDeSalvar.getStatus().getId() == 4 && formAtual.getStatus().getId() == 4)
				formAtual.setDateclose(formAntesDeSalvar.getDateclose());
			else if (formAntesDeSalvar.getStatus().getId() == 7 && formAtual.getStatus().getId() == 7)
				formAtual.setDateclose(formAntesDeSalvar.getDateclose());
			else
				formAtual.setDateclose(null);
		}

		
		
		
		
		/*
		 * AQUI TÁ CRIANDO UM OBJETO DE AÇÃO REFERENTE A UMA ALTERAÇÃO DE OS. PRA CADA
		 * ALTERAÇÃO, UM OBJETO DE AÇÃO SERÁ SALVO, CONTENDO DATA E HORA ATUAL +
		 * DESCRIÇÃO 'ALTERAÇÃO DEE OS' + O USUÁRIO Q ESTÁ LOGADO (Q ESTÁ FAZENDO A
		 * ALTERAÇAO VOCÊ SÓ VAI CONSEGUIR TESTAR ISSO, SE VOCÊ ESTIVER LOGADO NO
		 * FRONT... PQ PELO POSTMAN VC NUNCA VAI ESTAR EFETIVAMENTE LOGADO E VAI DAR
		 * ERRO AQUI. SACAS? entendi VAMOS COPIAR SEU FRONT E TESTAR ENTÃO... PERAE
		 */
		ActionController actionController = new ActionController();
		// criando a ação e setando dados iniciais
		ActionModel acao = new ActionModel();
		acao.setChange("ALTERAÇÃO DE OS");
		acao.setDateChange(new Timestamp(new Date().getTime()));
		// setando o usuário logado na ação
		UserModel userLogado = new UserModel();
		userLogado.setId(idUsuarioLogado);
		acao.setUser(userLogado);
		// setando form na ação
		acao.setForm(formAtual);
		System.out.println("vai salvar a caralha da ação");
		actionController.registrarNovaAcao(acao);
		dao.update(formAtual);
	}

	@GET
	@Path("buscar")
	@Consumes(MediaType.APPLICATION_JSON)
	public FormModel buscarPorId(@QueryParam("id") long id) {
		return (FormModel) dao.findById(FormModel.class, id);
	}

	@DELETE
	@Path("deletar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deletarPorId(@QueryParam("id") long id) {
		FormModel form = (FormModel) dao.findById(FormModel.class, id);
		dao.delete(FormModel.class, id);
	}

}
