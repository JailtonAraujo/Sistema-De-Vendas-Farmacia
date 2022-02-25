package com.projeto.sistemafarmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.projeto.sistemafarmacia.model.Usuario;

public class DAOLogin {

	private Connection connection = null;
	
	private static String nomeUser = "";
	
	
	public boolean Logar (Usuario usuario) throws SQLException {
		
		connection = SingleConnection.getConnection();
		
		String sql = "select ID, nome from usuario where login = ? and senha = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, usuario.getLogin());
		statement.setString(2, usuario.getSenha());
		
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			nomeUser = resultSet.getString("nome");
			connection.commit();
			statement.close();
			connection.close();
			return true; //Autenticado
		}else {
			return false; //Não Autenticado
		}
		
	}
	
	public String getUserName() {
		return this.nomeUser;
	}
}
