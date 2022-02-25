package com.projeto.sistemafarmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.projeto.sistemafarmacia.model.Cliente;

public class DAOCliente {

	private Connection connection = null;
	
	
	public boolean Insert (Cliente cliente){
		
		try {
		
		String sqlCliente = "insert into cliente (nome, cpf, idcontato, idendereco) values (?, ?, ?, ?)";
		String sqlContato = "insert into contato (email, telefone) values (?, ?)";
		String sqlEndereco = "insert into endereco (logradouro, cidade, numero) values (?, ?, ?)";
		
		connection = SingleConnection.getConnection();
		
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
			connection.close();
			
			return true;
		
		
		}catch(Exception ex) {
			try {
				connection.rollback();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			ex.printStackTrace();
		}
		
		
		return false;
	}
	
	public List<Cliente> buscarClientePorNome(String Nome) {
		
		try {
			
			connection = SingleConnection.getConnection();
			
			String sql = "select ID, nome, cpf from cliente where nome like ?";
			
			List<Cliente> listCliente = new ArrayList<Cliente>();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, Nome+"%");
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Cliente cliente = new Cliente();
				cliente.setID(resultSet.getInt("ID"));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setCpf(resultSet.getLong("cpf"));
				
				listCliente.add(cliente);
			}
			
			statement.close();
			connection.close();
			
			return listCliente;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
