package br.com.treino.jdbc.etc;

import java.lang.RuntimeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.treino.jdbc.factory.ConnectionFactory;

public class TestaInsercaoComParametro {
	public static void main(String[] args) {

		try(Connection conn = new ConnectionFactory().recuperarConexao()){
			conn.setAutoCommit(false);
			
			try (PreparedStatement stm = conn.prepareStatement("INSERT INTO PRODUTOS (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)){
				adicionarVariavel("Anne Pro 2", "Teclado sem fio", stm);
				adicionarVariavel("Redmi 9T", "Celular Xiaomi", stm);
				
				conn.commit();
			}
			catch(RuntimeException ex) {
				ex.printStackTrace();
				conn.rollback();
				System.out.println("ROLLBACK EXECUTADO");
			}
			
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	private static void adicionarVariavel(String nome, String desc, PreparedStatement pstm) throws SQLException {
		pstm.setString(1, nome);
		pstm.setString(2, desc);
		
		if(nome == "Redmi 9T") {
			throw new RuntimeException("Deu ruim meu chapa");
		}
		pstm.execute();
		
		ResultSet rst = pstm.getGeneratedKeys();
		
		while(rst.next()) {
			Integer id = rst.getInt(1);
			
			System.out.println("Adicionado novo produto no Id: " + id);
		}
	}
}
