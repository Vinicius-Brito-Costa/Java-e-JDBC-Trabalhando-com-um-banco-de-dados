package br.com.treino.jdbc.etc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.treino.jdbc.dao.CategoriaDAO;
import br.com.treino.jdbc.factory.ConnectionFactory;
import br.com.treino.jdbc.model.Categoria;

public class TestaListagemDeCategorias {
	
	public static void main(String[] args) {
		try(Connection connection = new ConnectionFactory().recuperarConexao()){
			CategoriaDAO categoriaDao = new CategoriaDAO(connection);
			List<Categoria> listaDeCategorias = categoriaDao.listarComProdutos();
			
			listaDeCategorias.forEach(cat -> {
				System.out.println(cat.getNome());
				cat.getProdutos().forEach(System.out::println);
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
