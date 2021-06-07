package br.com.treino.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.treino.jdbc.model.Categoria;
import br.com.treino.jdbc.model.Produto;

public class ProdutoDAO {
	
	private Connection connection;

	public ProdutoDAO(Connection conn) {
		this.connection = conn;
	}
	
	public void salvar(Produto produto){
		String sql = "insert into produtos (nome, descricao, categoria_id) values (?, ?, ?);";
		
		try(PreparedStatement pstm = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());
			pstm.setInt(3, produto.getCategoriaId());
			
			pstm.execute();
			
			try(ResultSet rst = pstm.getGeneratedKeys()){
				while(rst.next()) {
					produto.setId(rst.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Produto> listar(){
		List<Produto> produtos = new ArrayList<Produto>();
		try(PreparedStatement stm = this.connection.prepareStatement("select * from produtos")){
			stm.execute();
			trasformarResultSetEmProduto(produtos, stm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}
	
	public List<Produto> buscar(Categoria ct){
		List<Produto> produtos = new ArrayList<Produto>();
		String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTOS WHERE CATEGORIA_ID = ?";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setInt(1, ct.getId());
			pstm.execute();

			trasformarResultSetEmProduto(produtos, pstm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}
	
	public void deletar(Integer id){
		System.out.println("ID: " + id);
		try (PreparedStatement stm = this.connection.prepareStatement("delete from produtos where id = ?")){
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterar(String nome, String descricao, Integer id){
		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE PRODUTOS P SET P.NOME = ?, P.DESCRICAO = ? WHERE ID = ?")) {
			stm.setString(1, nome);
			stm.setString(2, descricao);
			stm.setInt(3, id);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void trasformarResultSetEmProduto(List<Produto> produtos, PreparedStatement pstm){
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));

				produtos.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
