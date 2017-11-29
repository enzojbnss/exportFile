
package br.com.yesh.exportFoto.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexao {

	public static Connection getConexaoMySQL() throws Exception, SQLException {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/training4farma",
					"root","M3l1ss4@");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static Connection getConexaoPostgreSQL() throws Exception, SQLException {
		try {

			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(
					"jdbc:postgresql://localhost/agenda", "root", "root");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static Connection getConexaoOracleXE() throws Exception, SQLException {
		try {

			Class.forName("oracle.jdbc.OracleDriver");
			return DriverManager.getConnection(
							"jdbc:oracle:thin:@172.16.208.40:1522/apldep.dpie.educ.guarulhos.sp.gov.br",
							"sepas", "sissepas");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
