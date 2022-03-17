package com.projeto.sistemafarmacia.model;

import java.util.Objects;

public class Contato {
	private int ID;
	private Long telefone;
	private String email;
	private int idPessoa;

	public Contato() {
	}

	public Contato(int iD, Long telefone, String email, int idPessoa) {
		ID = iD;
		this.telefone = telefone;
		this.email = email;
		this.idPessoa = idPessoa;
	}

	public Contato(Long telefone, String email) {
		this.telefone = telefone;
		this.email = email;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public long getTelefone() {
		return this.telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	@Override
	public String toString() {
		return "Contato [ID=" + ID + ", telefone=" + telefone + ", email=" + email + ", idPessoa=" + idPessoa + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID, email, idPessoa, telefone);
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
		return ID == other.ID && Objects.equals(email, other.email) && idPessoa == other.idPessoa
				&& Objects.equals(telefone, other.telefone);
	}

}
