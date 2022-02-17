package com.projeto.sistemafarmacia.model;

import java.io.Serializable;
import java.util.Objects;

public class Produto implements Serializable{
	
	private long id;
	private String nome;
	private String descrição;
	private int estoque;
	private double preco;

	public Produto(long id, String nome, String descrição, int estoque, double preco) {
		this.id = id;
		this.nome = nome;
		this.descrição = descrição;
		this.estoque = estoque;
		this.preco = preco;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrição() {
		return descrição;
	}

	public void setDescrição(String descrição) {
		this.descrição = descrição;
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
		return "Produto [id=" + id + ", nome=" + nome + ", descrição=" + descrição + ", estoque=" + estoque + ", preco="
				+ preco + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(descrição, estoque, id, nome, preco);
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
		return Objects.equals(descrição, other.descrição) && estoque == other.estoque && id == other.id
				&& Objects.equals(nome, other.nome)
				&& Double.doubleToLongBits(preco) == Double.doubleToLongBits(other.preco);
	}
	
	
	

}
