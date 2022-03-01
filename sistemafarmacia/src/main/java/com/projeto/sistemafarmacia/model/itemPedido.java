package com.projeto.sistemafarmacia.model;

public class itemPedido {

	private int idItemPedido;
	private int idPedido;
	private int quantidade;

	private Produto produto;

	public itemPedido() {
	}

	public itemPedido(int idPedido, int quantidade, Produto produto) {
		this.idPedido = idPedido;
		this.quantidade = quantidade;
		this.produto = produto;
	}

	public int getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(int idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto.setIdTabela(produto.getIdTabela());
	}

}
