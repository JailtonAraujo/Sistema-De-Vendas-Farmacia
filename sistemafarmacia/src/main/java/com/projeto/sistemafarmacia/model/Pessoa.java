package com.projeto.sistemafarmacia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cascade;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "empid_generator")
	@TableGenerator(name = "empid_generator", initialValue = 1, allocationSize = 1)
	private int ID;
	private String Nome;
	
	@OneToOne(mappedBy = "pessoa")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Contato contato;
	
	
	
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




	
	
	
	
}
