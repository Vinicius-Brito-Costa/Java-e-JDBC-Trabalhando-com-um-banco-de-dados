package br.com.treino.jdbc.etc;

import java.sql.SQLException;

import br.com.treino.jdbc.factory.ConnectionFactory;

public class TestaPoolConexoes {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory conn = new ConnectionFactory();
		
		for(int i = 0; i < 20; i++) {
			conn.recuperarConexao();
			System.out.println("Conexao numero " + (i + 1));
		}
	}

}
