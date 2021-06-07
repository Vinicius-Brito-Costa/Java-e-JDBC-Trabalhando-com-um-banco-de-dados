package br.com.treino.jdbc.etc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.treino.jdbc.factory.ConnectionFactory;

public class TestaRemocao {

	public static void main(String[] args) {
		try(Connection conn = new ConnectionFactory().recuperarConexao()){
			try(PreparedStatement stm = conn.prepareStatement("delete from produtos where id = ?;")){
				Integer id = 16;
				System.out.println("Deletando produto com id: " + id + "....");
				stm.setInt(1, id);
				stm.execute();
				Integer linhasMod = stm.getUpdateCount();
				System.out.println("Linhas modificadas: " + linhasMod);
				
				try(PreparedStatement stm2 = conn.prepareStatement("select * from produtos;")){
					System.out.println("Lista Atualizada:");
					stm2.execute();
					TestaListagem.listaProdutos(stm.getResultSet());
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			
			
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
}
