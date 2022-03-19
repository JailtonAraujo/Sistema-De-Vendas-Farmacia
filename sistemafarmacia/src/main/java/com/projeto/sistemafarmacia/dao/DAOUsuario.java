package com.projeto.sistemafarmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projeto.sistemafarmacia.model.Usuario;

public class DAOUsuario {
	
	Connection connection = null;
	
	public List<Usuario> buscarUsuario(String Search) {
		try {
			connection = SingleConnection.getConnection();
			String sql = "select usuario.ID, usuario.nome, usuario.senha, usuario.login, usuario.isAdmin from usuario usuario where usuario.nome like ? and usuario.isAdmin is false;";
			List<Usuario> usuarios = new ArrayList<Usuario>();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Search+"%");
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setNome(resultSet.getString("nome"));
				usuario.setLogin(resultSet.getString("login"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setID(resultSet.getInt("ID"));
				usuario.setAdmin(resultSet.getBoolean("isAdmin"));
				
				usuarios.add(usuario);
			}
			
			statement.close();
			connection.commit();
			return usuarios;
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			SingleConnection.closeConection();
		}
		
		return null;
	}
}
