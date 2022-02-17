package com.projeto.sistemafarmacia.model;

import java.util.Objects;



public class Contato {
	private int ID;
	private String Telefone;
	private String Email;
	
	private Pessoa pessoa;
	
	public Contato() {}
	
	

	public Contato(int iD, String telefone, String email, Pessoa pessoa) {
		ID = iD;
		Telefone = telefone;
		Email = email;
		this.pessoa = pessoa;
	}

	public Contato(String telefone, String email) {
		Telefone = telefone;
		Email = email;
	}


	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getTelefone() {
		return Telefone;
	}


	public void setTelefone(String telefone) {
		Telefone = telefone;
	}


	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
	}



	@Override
	public String toString() {
		return "Contato [ID=" + ID + ", Telefone=" + Telefone + ", Email=" + Email + ", pessoa=" + pessoa + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(Email, ID, Telefone, pessoa);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		return Objects.equals(Email, other.Email) && ID == other.ID && Objects.equals(Telefone, other.Telefone)
				&& Objects.equals(pessoa, other.pessoa);
	}


	
	
	
}
