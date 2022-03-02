package com.projeto.sistemafarmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.projeto.sistemafarmacia.model.Pedido;
import com.projeto.sistemafarmacia.model.Produto;
import com.projeto.sistemafarmacia.model.itemPedido;

public class DAOLancamentoVenda {

	private Connection connection = null;

	public boolean salvarPedido(Pedido pedido) {
		
		try {
			
			String sqlPedido = "insert into pedido (dataPedido, precoTotal, quantidadeTotal, pagamento, idCliente, idUsuario) values(?, ?, ?, ?, ?, ?)";
			
			connection = SingleConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, pedido.getData());
			statement.setDouble(2, pedido.getPrecoTotal());
			statement.setInt(3, pedido.getQuantidadeTotal());
			statement.setInt(4, pedido.getPagamento());
			statement.setInt(5, pedido.getCliente().getID());
			statement.setInt(6, pedido.getUsuario().getID());
			
			statement.execute();
			ResultSet resultSet = statement.getGeneratedKeys();
			resultSet.next();
			int idPedido = resultSet.getInt(1);
			
			for(itemPedido iten: pedido.getListaDeItens()) {
				iten.setIdPedido(idPedido);
				this.salvarItemPedido(iten);
				this.atualizarEstoque(iten.getProduto());
			}
			
			connection.commit();
			return true;
			
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
		
		return false;
	}
	
		public void salvarItemPedido(itemPedido itemPedido) throws SQLException {
			
			String sqlItemPedido = "insert into itempedido (idPedido, quantidade, idProduto) values (?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(sqlItemPedido);
			statement.setInt(1, itemPedido.getIdPedido());
			statement.setInt(2, itemPedido.getQuantidade());
			statement.setInt(3, itemPedido.getProduto().getIdTabela());
			statement.execute();
		}
		
		public void atualizarEstoque(Produto produto) throws SQLException {
			String sql = "update produto set estoque = ? where idProduto = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, produto.getEstoque());
			statement.setInt(2, produto.getIdTabela());
			
			statement.execute();
			
		}
	
	
}
