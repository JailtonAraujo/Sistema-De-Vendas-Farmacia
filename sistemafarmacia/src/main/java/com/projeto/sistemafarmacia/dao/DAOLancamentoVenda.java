package com.projeto.sistemafarmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.projeto.sistemafarmacia.model.Pedido;

public class DAOLancamentoVenda {

	private Connection connection = null;

	public boolean salvarPedido(Pedido pedido) {
		
		try {
			
			String sqlPedido = "insert into pedido (dataPedido, precoTotal, quantidadeTotal, pagamento, idCliente, idUsuario) values(?, ?, ?, ?, ?, ?)";
			String sqlItemPedido = "insert into itempedido (idPedido, quantidade) values (?, ?)";
			
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
			connection.commit();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			SingleConnection.closeConection();
		}
		
		return false;
	}
	
	
}
