package com.projeto.sistemafarmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.projeto.sistemafarmacia.model.Usuario;

public class DAOLogin {

	private Connection connection = null;
	private static Usuario userLogado = new Usuario();

	public boolean Logar(Usuario usuario) {

		try {

			connection = SingleConnection.getConnection();

			String sql = "select ID, nome, isAdmin from usuario where login = ? and senha = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				userLogado.setID(resultSet.getInt("ID"));
				userLogado.setNome(resultSet.getString("nome"));
				userLogado.setAdmin(resultSet.getBoolean("isAdmin"));
				connection.commit();
				statement.close();
				return true; // Autenticado
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			SingleConnection.closeConection();
		}
		return false;

	}

	@SuppressWarnings("static-access")
	public Usuario getUserLogado() {
		return this.userLogado;
	}
}
