package com.projeto.sistemafarmacia.model;

import java.util.Objects;

public class Endereco {
	
	private int ID;
	private String Logradouro;
	private String Cidade;
	private String Numero;

	public Endereco() {}
	
	
	
	public Endereco(String logradouro, String cidade, String numero) {
		Logradouro = logradouro;
		Cidade = cidade;
		Numero = numero;
	}



	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getLogradouro() {
		return Logradouro;
	}

	public void setLogradouro(String logradouro) {
		Logradouro = logradouro;
	}

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public String getNumero() {
		return Numero;
	}

	public void setNumero(String numero) {
		Numero = numero;
	}

	@Override
	public String toString() {
		return "Endereco [ID=" + ID + ", Logradouro=" + Logradouro + ", Cidade=" + Cidade + ", Numero=" + Numero + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(Cidade, ID, Logradouro, Numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(Cidade, other.Cidade) && ID == other.ID && Objects.equals(Logradouro, other.Logradouro)
				&& Numero == other.Numero;
	}

}
