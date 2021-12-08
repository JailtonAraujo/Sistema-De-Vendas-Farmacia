package com.projeto.sistemafarmacia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.TableGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "empid_generator")
	@TableGenerator(name = "empid_generator", initialValue = 1, allocationSize = 1)
	private int ID;
	private String Nome;
	
	
	
	private Contato contato;
	private Endereco endereco;
}
