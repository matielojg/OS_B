package controller;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import util.Utilitarios;

@Path("upload")
public class UploadController {
	
	@POST
	@Path("salvar")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String salvar(
			@FormDataParam("file") InputStream arquivo,
            @FormDataParam("file") FormDataContentDisposition header ) {
		
	    String caminho = "/uploads/"+header.getFileName();
		String caminhoCompleto = new Utilitarios().getRaizServidor()+caminho;
		new Utilitarios().salvarArquivo(arquivo, caminhoCompleto);

		return header.getFileName();
	}
	
	
}
