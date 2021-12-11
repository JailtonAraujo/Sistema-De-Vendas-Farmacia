package com.projeto.sistemafarmacia.model;

import javax.persistence.Entity;

@Entity
public class Usuario extends Pessoa{

	private String UserName;
	private String PassWord;
	
	
	/*
	public Usuario(int ID, String Nome, Contato contato, String userName, String passWord) {
		super(ID, Nome, contato);
		UserName = userName;
		PassWord = passWord;
	}
	
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
	
	
}
