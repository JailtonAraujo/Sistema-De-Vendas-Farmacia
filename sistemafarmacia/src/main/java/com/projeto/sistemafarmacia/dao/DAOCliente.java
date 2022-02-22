package com.projeto.sistemafarmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.projeto.sistemafarmacia.model.Cliente;

public class DAOCliente {

	private Connection connection = null;
	
	public DAOCliente() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean Insert (Cliente cliente){
		
		try {
		
		String sqlCliente = "insert into cliente (nome, cpf, idcontato, idendereco) values (?, ?, ?, ?)";
		String sqlContato = "insert into contato (email, telefone) values (?, ?)";
		String sqlEndereco = "insert into endereco (logradouro, cidade, numero) values (?, ?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(sqlContato, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, cliente.getContato().getEmail());
			statement.setLong(2, cliente.getContato().getTelefone());
		
			statement.execute();
		
			ResultSet rs = statement.getGeneratedKeys();//Pegando id auto gerarado do contato;
			rs.next();
			int idcontato = rs.getInt(1);
			
			
			statement = connection.prepareStatement(sqlEndereco, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, cliente.getEndereco().getLogradouro());
			statement.setString(2, cliente.getEndereco().getCidade());
			statement.setInt(3, cliente.getEndereco().getNumero());
			
			statement.execute();
			rs = statement.getGeneratedKeys();
			rs.next();
			int idendereco = rs.getInt(1);
			
			statement = connection.prepareStatement(sqlCliente);
			statement.setString(1, cliente.getNome());
			statement.setLong(2, cliente.getCpf());
			statement.setInt(3, idcontato);
			statement.setInt(4, idendereco);
			
			statement.execute();
			
			connection.commit();
			statement.close();
			
			return true;
		
		
		}catch(Exception ex) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		}
		
		
		return false;
	}
	
	
}
