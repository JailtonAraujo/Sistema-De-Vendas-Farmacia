package com.projeto.sistemafarmacia.model;

import java.util.Objects;

import javax.persistence.Entity;

@Entity
public class Usuario extends Pessoa {

	private String UserName;
	private String PassWord;

	/*
	 * public Usuario(int ID, String Nome, Contato contato, String userName, String
	 * passWord) { super(ID, Nome, contato); UserName = userName; PassWord =
	 * passWord; }
	 * 
	 */
	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	@Override
	public String toString() {
		return "Usuario [UserName=" + UserName + ", PassWord=" + PassWord + ", getID()=" + getID() + ", getNome()="
				+ getNome() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(PassWord, UserName);
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
		return Objects.equals(PassWord, other.PassWord) && Objects.equals(UserName, other.UserName);
	}

}
