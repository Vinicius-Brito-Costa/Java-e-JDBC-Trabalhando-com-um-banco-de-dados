package br.com.treino.jdbc.etc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.treino.jdbc.factory.ConnectionFactory;

public class TestaListagem {

	public static void main(String[] args) {
		try (Connection conn = new ConnectionFactory().recuperarConexao()){
			
			PreparedStatement stm = conn.prepareStatement("select * from produtos");
			stm.execute();
			ResultSet rst = stm.getResultSet();
			
			TestaListagem.listaProdutos(rst);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		System.out.println("Deu bom!");
	}
	public static void listaProdutos(ResultSet rst) throws SQLException {
		while(rst.next()) {
			Integer id = rst.getInt("id");
			String nome = rst.getString("nome");
			String desc = rst.getString("descricao");
			
			System.out.println("ID: " + id + " NOME: " + nome + " DESCRIÇÃO: " + desc);
		}
	}
}
