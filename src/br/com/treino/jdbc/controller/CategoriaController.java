package br.com.treino.jdbc.controller;

import java.util.List;

import br.com.treino.jdbc.dao.CategoriaDAO;
import br.com.treino.jdbc.factory.ConnectionFactory;
import br.com.treino.jdbc.model.Categoria;

public class CategoriaController {
	private CategoriaDAO categoriaDao;
	
	public CategoriaController() {
		categoriaDao = new CategoriaDAO(new ConnectionFactory().recuperarConexao());
	}
	public List<Categoria> listar() {
		return categoriaDao.listar();
	}
}
