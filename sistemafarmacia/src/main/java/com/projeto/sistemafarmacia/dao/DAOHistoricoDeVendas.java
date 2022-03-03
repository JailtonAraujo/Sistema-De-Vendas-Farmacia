package com.projeto.sistemafarmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.projeto.sistemafarmacia.model.Cliente;
import com.projeto.sistemafarmacia.model.Pedido;
import com.projeto.sistemafarmacia.model.Produto;
import com.projeto.sistemafarmacia.model.Usuario;
import com.projeto.sistemafarmacia.model.itemPedido;

public class DAOHistoricoDeVendas {

	private Connection connection = null;
	
	public List<Pedido> BuscarPedidos(String data) {
		try {
			List<Pedido> listaDePedidos = new ArrayList<Pedido>();
			String sql = "select pedido.idPedido, pedido.dataPedido, pedido.precoTotal, pedido.quantidadeTotal, pedido.pagamento, cliente.nome as cliente, usuario.nome as usuario\r\n"
					+ "from pedido \r\n"
					+ "inner join usuario on usuario.ID = pedido.idUsuario\r\n"
					+ "inner join cliente on cliente.ID = pedido.idCliente where pedido.dataPedido = ?;";
			connection = SingleConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, data);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Pedido pedido = new Pedido();
				
				Usuario usuario = new Usuario();
				usuario.setNome(resultSet.getString("usuario"));
				
				Cliente cliente = new Cliente();
				cliente.setNome(resultSet.getString("cliente"));
				
				List<itemPedido> itemPedidos = this.listarItemPedido(resultSet.getInt("idPedido"));
				
				pedido.setIdPedido(resultSet.getInt("idPedido"));
				pedido.setData(resultSet.getString("dataPedido"));
				pedido.setPrecoTotal(resultSet.getDouble("precoTotal"));
				pedido.setQuantidadeTotal(resultSet.getInt("quantidadeTotal"));
				pedido.setPagamento(resultSet.getInt("pagamento"));
				pedido.setListaDeItens(itemPedidos);
			}
			
			return listaDePedidos;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<itemPedido> listarItemPedido(int idPedido){
		
		try {
			String sql = "select itempedido.quantidade, idProduto from itempedido where itempedido.idPedido = ?;";
			List<itemPedido> itemPedidos = new ArrayList<itemPedido>();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idPedido);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				itemPedido itemPedido = new itemPedido();
				
				Produto produto;
				produto = this.buscarProdutoId(resultSet.getInt("idProduto"));
				
				itemPedido.setQuantidade(resultSet.getInt("quantidade"));
				itemPedido.setProduto(produto);
				
				itemPedidos.add(itemPedido);
			}
			
			return itemPedidos;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Produto buscarProdutoId(int idProduto) {
		
		try {
			String sql = "select produto.nome, produto.preco from produto where produto.idtabela = ?;";
			Produto produto = new Produto();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idProduto);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			
			produto.setNome(resultSet.getString("nome"));
			produto.setPreco(resultSet.getDouble("preco"));
			
			return produto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
