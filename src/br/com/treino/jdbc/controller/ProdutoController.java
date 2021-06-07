package br.com.treino.jdbc.controller;


import java.util.List;
import br.com.treino.jdbc.dao.ProdutoDAO;
import br.com.treino.jdbc.factory.ConnectionFactory;
import br.com.treino.jdbc.model.Produto;

public class ProdutoController {
	private ProdutoDAO produtoDao;
	
	public ProdutoController() {
		this.produtoDao = new ProdutoDAO(new ConnectionFactory().recuperarConexao());
	}
	public void deletar(Integer id) {
		produtoDao.deletar(id);
		System.out.println("Deletando produto");
	}

	public void salvar(Produto produto) {
		produtoDao.salvar(produto);
		System.out.println("Salvando produto");
	}

	public List<Produto> listar() {
		List<Produto> produtos = produtoDao.listar();
		return produtos;
	}

	public void alterar(String nome, String descricao, Integer id) {
		produtoDao.alterar(nome, descricao, id);
		System.out.println("Alterando produto");
	}
}
