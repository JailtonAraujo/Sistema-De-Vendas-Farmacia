package com.projeto.sistemafarmacia.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa{
	
	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Cliente() {}
	
	
	
	public Cliente(int iD, String nome, Contato contato,String cpf) {
		super(iD,nome, contato);
		this.cpf = cpf;
	}

	public Cliente(int iD, String nome,String cpf) {
		super(iD,nome);
		this.cpf = cpf;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf);
	}

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", getContato()=" + getContato() + ", getID()=" + getID() + ", getNome()="
				+ getNome() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}
