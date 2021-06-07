package br.com.treino.jdbc.model;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

	private Integer id;
	private String nome;
	private List<Produto> produtos = new ArrayList<>();
	
	public Categoria(String nome) {
		this.nome = nome;
	}
	
	public Categoria(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Integer getId() {
		return this.id;
	}
	@Override
	public String toString() {
		return "Id: " + this.id + " Nome: " + this.nome;
	}

	public String getNome() {
		return this.nome;
	}
	
	public List<Produto> getProdutos(){
		return this.produtos;
	}
	
	public void adicionarProduto(Produto produto) {
		this.produtos.add(produto);
	}
}
