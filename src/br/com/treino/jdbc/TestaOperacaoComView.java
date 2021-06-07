package br.com.treino.jdbc;

import javax.swing.JFrame;

import br.com.treino.jdbc.view.ProdutoCategoriaView;

public class TestaOperacaoComView {

	public static void main(String[] args) {
		ProdutoCategoriaView produtoCategoriaFrame = new ProdutoCategoriaView();
		produtoCategoriaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
