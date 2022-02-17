package com.projeto.sistemafarmacia.model;


public abstract class Pessoa {
	private int ID;
	private String Nome;
	private Contato contato;
	
	public Pessoa() {}
	
	
	
	public Pessoa(int iD, String nome, Contato contato) {
		ID = iD;
		Nome = nome;
		this.contato = contato;
	}

	public Pessoa(int iD, String nome) {
		ID = iD;
		Nome = nome;
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
		return "Pessoa [ID=" + ID + ", Nome=" + Nome + ", contato=" + contato + "]";
	}




	
	
	
	
}
