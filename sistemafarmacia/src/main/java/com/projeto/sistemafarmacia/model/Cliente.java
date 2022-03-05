package com.projeto.sistemafarmacia.model;

public class Cliente extends Pessoa {

	private Long cpf;

	public Cliente(int iD, String nome, Contato contato, Endereco endereco, Long cpf) {
		super(iD, nome, contato, endereco);
		this.cpf = cpf;
	}

	public Cliente(String nome, Contato contato, Endereco endereco, Long cpf) {
		super(nome, contato, endereco);
		this.cpf = cpf;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Cliente() {
	}

	/*
	
	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", getEndereco()=" + getEndereco() + ", getContato()=" + getContato()
				+ ", getID()=" + getID() + ", getNome()=" + getNome()+"]";
	}
	*/
	@Override
	public String toString() {
		return getNome();
	}

	

}
