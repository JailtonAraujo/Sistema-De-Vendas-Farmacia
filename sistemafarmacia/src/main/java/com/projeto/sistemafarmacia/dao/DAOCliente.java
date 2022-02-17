package com.projeto.sistemafarmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.projeto.sistemafarmacia.model.Cliente;

public class DAOCliente {

	private Connection connection = null;
	
	public DAOCliente() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean Insert (Cliente cliente) throws SQLException {
		
		try {
		
		String sqlCliente = "insert into cliente (nome, cpf) values (?, ?)";
		String sqlContato = "insert into contato (email, telefone, pessoa_fk) values (?, ?, ?)";
		String sqlEndereco = "insert int endereco (logradouro, cidade, numero, pessoa_fk) values (?, ?, ?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(sqlCliente);
		statement.setString(1, cliente.getNome());
		statement.setLong(2, cliente.getCpf());
		
		statement.execute();
		
		
			
		
		
		}catch(Exception ex) {
			connection.rollback();
			ex.printStackTrace();
		}
		
		
		return false;
	}
	
	
}
