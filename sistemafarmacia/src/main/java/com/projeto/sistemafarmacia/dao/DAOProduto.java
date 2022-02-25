package com.projeto.sistemafarmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.projeto.sistemafarmacia.model.Produto;

public class DAOProduto {

	private Connection connection = null;
	
	public boolean Insert (Produto produto) {
		
		try {
			
			connection = SingleConnection.getConnection();
			
			String sql = "insert into produto (idproduto, nome, descricao, estoque, preco) values (?, ?, ?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, produto.getId());
			statement.setString(2, produto.getNome());
			statement.setString(3, produto.getDescricao());
			statement.setInt(4, produto.getEstoque());
			statement.setDouble(5, produto.getPreco());
			
			
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
			JOptionPane.showMessageDialog(null, "Causa: "+ex.getMessage(), "ERRO AO CADASTRAR PRODUTO!", 0);
		}
		return false;
	}
	
	public List<Produto> buscarProdutosPorNome(String nome){
		
		try {
			
			connection = SingleConnection.getConnection();
			
			String sql = "select * from produto where nome like ?;";
			
			List<Produto> produtos = new ArrayList<Produto>();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, nome+"%");
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Produto produto = new Produto();
				
				produto.setIdTabela(resultSet.getInt("idTabela"));
				produto.setNome(resultSet.getString("nome"));
				produto.setDescricao(resultSet.getString("descricao"));
				produto.setEstoque(resultSet.getInt("estoque"));
				produto.setPreco(resultSet.getDouble("preco"));
				produto.setId(resultSet.getLong("idproduto"));
				
				produtos.add(produto);
			}
			
			statement.close();
			connection.close();
			return produtos;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
}
