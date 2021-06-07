package br.com.treino.jdbc.model;

public class Produto {
	
	private Integer id;
	private String nome;
	private String descricao;
	private Integer categoriaId;
	
	public Produto(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}
	public Produto(Integer id, String nome, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	public Produto(Integer id, String nome, String descricao, Integer categoriaId) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
	}
	public String getNome() {
		return this.nome;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

	public Integer getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;		
	}
	
	@Override
	public String toString() {
		return "Id: " + this.id + " Nome: " + this.nome + " Descrição: " + this.descricao;
	}
	
	public Integer getCategoriaId() {
		return categoriaId;
	}
	
	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}
}
