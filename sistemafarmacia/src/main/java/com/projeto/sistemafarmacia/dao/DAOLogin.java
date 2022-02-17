package com.projeto.sistemafarmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.projeto.sistemafarmacia.model.Usuario;

public class DAOLogin {

	private Connection connection = null;
	
	private static String nomeUser = "";
	
	public DAOLogin() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean Logar (Usuario usuario) throws SQLException {
		String sql = "select * from usuario where login = ? and senha = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, usuario.getLogin());
		statement.setString(2, usuario.getSenha());
		
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			nomeUser = resultSet.getString("nome");
			connection.commit();
			statement.close();
			return true; //Autenticado
		}else {
			return false; //Não Autenticado
		}
		
	}
	
	public String getUserName() {
		return this.nomeUser;
	}
}
