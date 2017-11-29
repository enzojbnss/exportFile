package br.com.yesh.exportFoto;

import br.com.yesh.exportFoto.jdbc.FotoDAO;

public class Main {

	public static void main(String[] args) {
		FotoDAO dao = new FotoDAO();
		dao.exportaFoto();
	}

}
