package com.projeto.sistemafarmacia.model;

public abstract class Pessoa {
	private int ID;
	private String Nome;
	private Contato contato;
	private Endereco endereco;

	public Pessoa() {
	}

	public Pessoa(int iD, String nome, Contato contato, Endereco endereco) {
		ID = iD;
		Nome = nome;
		this.contato = contato;
		this.endereco = endereco;
	}

	public Pessoa(String nome, Contato contato, Endereco endereco) {
		Nome = nome;
		this.contato = contato;
		this.endereco = endereco;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	@Override
	public String toString() {
		return "Pessoa [ID=" + ID + ", Nome=" + Nome + ", contato=" + contato + ", endereco=" + endereco + "]";
	}

}
