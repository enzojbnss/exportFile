package br.com.yesh.exportFoto.jdbc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FotoDAO {

	public void exportaFoto() {
		String sql = "SELECT nomeCompleto,nomeArquivo,image FROM training4farma.participante where nomeCompleto != ''";
		try {
			Connection connection = FabricaDeConexao.getConexaoMySQL();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String nomeBanco = rs.getString("nomeArquivo");
				String nome = rs.getString("nomeCompleto");
				String nomeArquivo = "";
				if(nomeBanco.endsWith(".jpg")) {
					nomeArquivo = nome.concat(".jpg");
				}else if(nomeBanco.endsWith(".jpeg")) {
					nomeArquivo = nome.concat(".jpeg");
				}else if(nomeBanco.endsWith(".png")) {
					nomeArquivo = nome.concat(".png");
				}
				if(!nomeArquivo.isEmpty()) {
					Blob image = (Blob) rs.getBlob("image");
					System.out.println("inicio exportação" + nomeArquivo);
					salva(image, nomeArquivo);
				}
			}
			rs.close();
			stmt.close();
			connection.close();
			connection = null;
		}catch(

	SQLException e)
	{
		System.out.println(e.getMessage());
		throw new RuntimeException();
	}catch(
	Exception ex)
	{
		System.out.println(ex.getMessage());
		throw new RuntimeException();
	}

	}

	public void salva(Blob blob, String nomeArquivo) {
		File destino = new File("C:\\Users\\enzoj\\Pictures\\closeup", nomeArquivo);
		System.out.println("Tamanho do arquivo: " + destino.length());
		InputStream is;
		try {
			is = blob.getBinaryStream();
			FileOutputStream fos = new FileOutputStream(destino);
			int b = 0;
			while ((b = is.read()) != -1) {
				fos.write(b);
			}
			
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("falha ao salvar arquivo" + nomeArquivo);
		}
		System.out.println(nomeArquivo+" exprtado com sucesso!");
	}

}
