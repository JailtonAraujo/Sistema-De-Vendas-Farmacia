package com.projeto.sistemafarmacia.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ManyToAny;

@Entity
public class Contato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	private String Telefone;
	private String Email;
	
	
	@OneToOne
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@JoinColumn(name = "Pessoa_fk")
	private Pessoa pessoa;
	
	

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


	
	
	
}
