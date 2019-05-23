package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Utilitarios {

	public String getRaizServidor() {
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath = "";
		try {
			fullPath = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String pathArr[] = fullPath.split("/WEB-INF/classes/");
		System.out.println(fullPath);
		System.out.println(pathArr[0]);
		fullPath = pathArr[0];
		return fullPath;
	}

	public void salvarArquivo(InputStream uploadedInputStream, String caminho) {

		try {
			caminho = caminho.replaceAll("file:/", "");
			System.out.println(caminho);

			OutputStream out = null;
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(caminho));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void deletarArquivo(String caminho) {
		try {
			File objFile = new File(caminho);
			if (objFile.exists()) {
				objFile.delete();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
