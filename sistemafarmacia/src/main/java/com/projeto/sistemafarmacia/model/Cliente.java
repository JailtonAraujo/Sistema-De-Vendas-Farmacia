package com.projeto.sistemafarmacia.model;

public class Cliente extends Pessoa {

	private Long cpf;

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}


	 public Cliente() {}

	
	 
	 public Cliente(int iD, String nome, Contato contato,Long cpf) {
	 super(iD,nome, contato); 
	 this.cpf = cpf; }
	 
	  public Cliente(int iD, String nome,Long cpf) { super(iD,nome); this.cpf = cpf; }

	public String toString() {
		return "Cliente [ cfp = "+cpf+", ID = "+getID()+", Nome = "+getNome()+", "
			 + "Contato [ID = "+getContato().getID()+", Email = "+getContato().getEmail()+", Telefone = "+getContato().getTelefone()+"] ]";
	}

	
	  
	
	  

	
	  

	
	 
	
	
	  
	

}
