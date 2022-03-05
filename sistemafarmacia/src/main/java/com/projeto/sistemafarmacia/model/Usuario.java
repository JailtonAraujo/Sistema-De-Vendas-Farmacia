package com.projeto.sistemafarmacia.model;

import java.util.Objects;
public class Usuario extends Pessoa {

	private String login;
	private String senha;

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String Login) {
		this.login = Login;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String Senha) {
		this.senha = Senha;
	}

	/*
	@Override
	public String toString() {
		return "Usuario [login=" + login + ", senha=" + senha + ", getID()=" + getID() + ", getNome()="
				+ getNome() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
*/
	
	@Override
	public String toString() {
		return getNome();
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(senha, login);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(senha, other.senha) && Objects.equals(login, other.login);
	}

}
