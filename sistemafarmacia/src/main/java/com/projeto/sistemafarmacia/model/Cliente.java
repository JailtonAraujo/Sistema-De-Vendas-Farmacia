package com.projeto.sistemafarmacia.model;

import javax.persistence.Entity;

@Entity
public class Cliente extends Pessoa {

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
	 this.cpf = cpf; }
	 
	  public Cliente(int iD, String nome,String cpf) { super(iD,nome); this.cpf = cpf; }

	public String toString() {
		return "Cliente [ cfp = "+cpf+", ID = "+getID()+", Nome = "+getNome()+", "
			 + "Contato [ID = "+getContato().getID()+", Email = "+getContato().getEmail()+", Telefone = "+getContato().getTelefone()+"] ]";
	}

	
	  
	
	  

	
	  

	
	 
	
	
	  
	

}
