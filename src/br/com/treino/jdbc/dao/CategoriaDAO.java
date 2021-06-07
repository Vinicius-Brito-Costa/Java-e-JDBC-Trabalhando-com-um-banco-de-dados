package br.com.treino.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.treino.jdbc.model.Categoria;
import br.com.treino.jdbc.model.Produto;

public class CategoriaDAO {
	private Connection connection;
	
	public CategoriaDAO(Connection conn) {
		this.connection = conn;
	}

	public List<Categoria> listar(){
		List<Categoria> categorias = new ArrayList<>();
		String sql = "select * from categorias;";
		try(PreparedStatement pstm = this.connection.prepareStatement(sql)){
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()){
				while(rst.next()) {
					Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
					
					categorias.add(categoria);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return categorias;
	}
	
	public List<Categoria> listarComProdutos() throws SQLException {
		Categoria ultima = null;
		List<Categoria> categorias = new ArrayList<>();
		String sql = "select c.id, c.nome, p.id, p.nome, p.descricao from categorias c inner join produtos p on c.id = p.categoria_id;";
		try(PreparedStatement pstm = this.connection.prepareStatement(sql)){
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()){
				while(rst.next()) {
					if(ultima == null || !ultima.getNome().equals(rst.getString(2))) {
						Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
						
						ultima = categoria;
						
						categorias.add(categoria);
					}
					
					Produto produto = new Produto(rst.getInt(3), rst.getString(4), rst.getString(5));
					
					ultima.adicionarProduto(produto);
					
				}
			}
		}
		return categorias;
	}
}
