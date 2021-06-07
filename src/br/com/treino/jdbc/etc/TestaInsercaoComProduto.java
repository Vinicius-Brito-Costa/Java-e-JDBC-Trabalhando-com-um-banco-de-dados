package br.com.treino.jdbc.etc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.treino.jdbc.dao.ProdutoDAO;
import br.com.treino.jdbc.factory.ConnectionFactory;
import br.com.treino.jdbc.model.Produto;

public class TestaInsercaoComProduto {

	public static void main(String[] args) {
		Produto playstation = new Produto("Playstation 5", "Sony Playstation 5 825gb");
		
		try(Connection conn = new ConnectionFactory().recuperarConexao()){
			ProdutoDAO produtoDao = new ProdutoDAO(conn);
			produtoDao.salvar(playstation);
			List<Produto> listaProdutos = produtoDao.listar();
			listaProdutos.forEach(System.out::println);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(playstation);
	}
}
