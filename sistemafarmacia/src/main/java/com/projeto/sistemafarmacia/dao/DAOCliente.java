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
		
		String sqlCliente = "insert into cliente (nome, cpf) values (?, ?)";
		String sqlContato = "insert into contato (email, telefone, pessoa_fk) values (?, ?, ?)";
		String sqlEndereco = "insert into endereco (logradouro, cidade, numero, pessoa_fk) values (?, ?, ?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, cliente.getNome());
		statement.setLong(2, cliente.getCpf());
		
		statement.execute();
		
	
			ResultSet rs = statement.getGeneratedKeys();//Pegando id auto gerarado do cliente;
			rs.next();
			int idPessoa = rs.getInt(1);
			
			statement = connection.prepareStatement(sqlContato);
			statement.setString(1, cliente.getContato().getEmail());
			statement.setLong(2, cliente.getContato().getTelefone());
			statement.setInt(3, idPessoa);
			
			statement.execute();
			
			statement = connection.prepareStatement(sqlEndereco);
			statement.setString(1, cliente.getEndereco().getLogradouro());
			statement.setString(2, cliente.getEndereco().getCidade());
			statement.setInt(3, cliente.getEndereco().getNumero());
			statement.setInt(4, idPessoa);
			
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
