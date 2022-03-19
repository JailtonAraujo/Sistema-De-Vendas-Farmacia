package com.projeto.sistemafarmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.projeto.sistemafarmacia.model.Usuario;

public class DAOUsuario {
	
	Connection connection = null;
	
	public List<Usuario> buscarUsuario(String Search) {
		try {
			connection = SingleConnection.getConnection();//**POR MOTIVOS DE SEGURANÇA NÃO TRAZIDO  USUARIO DO TIPO ADMIN**//
			String sql = "select usuario.ID, usuario.nome, usuario.senha, usuario.login, usuario.isAdmin from usuario where usuario.nome like ? and usuario.isAdmin is false;";
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
	
	public boolean cadastrarUsuario(Usuario usuario) {
		try {
			connection = SingleConnection.getConnection();
			PreparedStatement statement = null;
			
			if(usuario.getID() > 0) {
				
				String sql = "update usuario set nome=?, isAdmin=?, login=?, senha=? where ID = ?";
				
				statement = connection.prepareStatement(sql);
				statement.setString(1, usuario.getNome());
				statement.setBoolean(2, usuario.isAdmin());
				statement.setString(3, usuario.getLogin());
				statement.setString(4, usuario.getSenha());
				statement.setInt(5, usuario.getID());
				
				statement.executeUpdate();
			}else {
			
			String sql = "insert into usuario (nome, isAdmin, login, senha) values (?, ?, ?, ?)";
			
			statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getNome());
			statement.setBoolean(2, usuario.isAdmin());
			statement.setString(3, usuario.getLogin());
			statement.setString(4, usuario.getSenha());
			
			statement.execute();
			}
			connection.commit();
			statement.close();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "JA EXISTE UM USUARIO COM ESSAS INFORMAÇÕES!","ERRO AO CADASTRAR USUARIO!",0);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			SingleConnection.closeConection();
		}
		
		return false;
	}
	
	public boolean excluirUsuario(int idUsuario) {
		try {
			connection = SingleConnection.getConnection();
			String sql = "delete from usuario where ID = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idUsuario);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ESTE USUARIO ESTA RELEACIONADO A ALGUMAS VENDAS, POR ESTE MOTIVO NÃO É POSSIVEL EXCLUIR O MESMO!","ERRO AO EXCLUIR USUARIO!",0);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			SingleConnection.closeConection();
		}
		return false;
	}
}
