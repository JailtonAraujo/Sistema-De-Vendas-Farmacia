package com.projeto.sistemafarmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.projeto.sistemafarmacia.model.Produto;

public class DAOProduto {

	private Connection connection = null;
	
	public DAOProduto () {
		connection = SingleConnection.getConnection();
	}
	
	public boolean Insert (Produto produto) {
		
		try {
			
			String sql = "insert into produto (id, nome, descricao, estoque, preco) values (?, ?, ?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, produto.getId());
			statement.setString(2, produto.getNome());
			statement.setString(3, produto.getDescrição());
			statement.setInt(4, produto.getEstoque());
			statement.setDouble(5, produto.getPreco());
			
			statement.execute();
			
			connection.commit();
			
			return true;
			
		}catch(Exception ex) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Causa: "+ex.getMessage(), "ERRO AO CADASTRAR PRODUTO!", 0);
		}
		return false;
	}
	
}
