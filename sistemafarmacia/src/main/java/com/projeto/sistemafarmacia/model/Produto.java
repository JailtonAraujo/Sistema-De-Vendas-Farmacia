package com.projeto.sistemafarmacia.model;

import java.util.Objects;

public class Produto{
	
	private long idProduto;
	private int idTabela;
	private String nome;
	private String descricao;
	private int estoque;
	private double preco;

	public Produto() {}
	
	public Produto(long id,String nome, String descricao, int estoque, double preco) {
		this.idProduto = id;
		this.nome = nome;
		this.descricao = descricao;
		this.estoque = estoque;
		this.preco = preco;
	}
	

	public int getIdTabela() {
		return idTabela;
	}

	public void setIdTabela(int idTabela) {
		this.idTabela = idTabela;
	}

	public long getId() {
		return idProduto;
	}

	public void setId(long id) {
		this.idProduto = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", idTabela=" + idTabela + ", nome=" + nome + ", descricao="
				+ descricao + ", estoque=" + estoque + ", preco=" + preco + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricao, estoque, idProduto, idTabela, nome, preco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(descricao, other.descricao) && estoque == other.estoque && idProduto == other.idProduto
				&& idTabela == other.idTabela && Objects.equals(nome, other.nome)
				&& Double.doubleToLongBits(preco) == Double.doubleToLongBits(other.preco);
	}
	
	
	

}
