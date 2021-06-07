package br.com.treino.jdbc.etc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.treino.jdbc.factory.ConnectionFactory;

public class TestaConexao {

	public static void main(String[] args) {
		
		try (Connection conn = new ConnectionFactory().recuperarConexao()){
			
			try(PreparedStatement stm = conn.prepareStatement("select * from produtos")){
				stm.execute();
				ResultSet rst = stm.getResultSet();
				
				TestaListagem.listaProdutos(rst);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		System.out.println("Deu bom!");
	}
	


}
