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
			PreparedStatement statement = null;
			
			if(produto.getIdTabela() > 0) {
				String sql = "update produto set idproduto = ?, nome = ?, descricao = ?, estoque = ?, preco = ? where idtabela = ?";
				
				statement = connection.prepareStatement(sql);
				statement.setLong(1, produto.getId());
				statement.setString(2, produto.getNome());
				statement.setString(3, produto.getDescricao());
				statement.setInt(4, produto.getEstoque());
				statement.setDouble(5, produto.getPreco());
				statement.setInt(6, produto.getIdTabela());
				
				statement.executeUpdate();
			}else {
			String sql = "insert into produto (idproduto, nome, descricao, estoque, preco) values (?, ?, ?, ?, ?)";
			
			statement = connection.prepareStatement(sql);
			statement.setLong(1, produto.getId());
			statement.setString(2, produto.getNome());
			statement.setString(3, produto.getDescricao());
			statement.setInt(4, produto.getEstoque());
			statement.setDouble(5, produto.getPreco());
			
			statement.execute();
			}
			
			connection.commit();
			statement.close();
			
			return true;
			
		}catch(Exception ex) {
			try {
				connection.rollback();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Causa: "+ex.getMessage(), "ERRO AO SALVAR PRODUTO!", 0);
		}finally {
			SingleConnection.closeConection();
		}
		return false;
	}
	
	public List<Produto> buscarProdutosPorNome(String nome){
		
		try {
			
			connection = SingleConnection.getConnection();
			
			String sql = "select idTabela, nome, descricao, estoque, preco, idproduto from produto where nome like ?;";
			
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
			return produtos;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			SingleConnection.closeConection();
		}
		
		
		return null;
	}
	
	public boolean excluirProduto(int idTableProduto) {
		try {
			connection = SingleConnection.getConnection();
			
			String sql = "delete from produto where idtabela = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idTableProduto);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();
			return true;
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ESTE PRODUTO ESTA ASSOCIADO Á ALGUNS PEDIDOS, PORTANTO, NÃO PODE SER EXCLUIDO!", "ERRO AO EXCLUIR PRODUTO!", 0);
		}finally {
			SingleConnection.closeConection();
		}
		return false;
	}
	
}
