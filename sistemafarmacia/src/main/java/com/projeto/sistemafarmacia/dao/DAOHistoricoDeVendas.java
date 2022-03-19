package com.projeto.sistemafarmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.projeto.sistemafarmacia.model.Cliente;
import com.projeto.sistemafarmacia.model.Pedido;
import com.projeto.sistemafarmacia.model.Produto;
import com.projeto.sistemafarmacia.model.Usuario;
import com.projeto.sistemafarmacia.model.itemPedido;

public class DAOHistoricoDeVendas {

	private Connection connection = null;
	
	public List<Pedido> BuscarPedidos(String [] intervaloData, String filtro, String search) {
		try {
			List<Pedido> listaDePedidos = new ArrayList<Pedido>();
			String sql = "select pedido.idPedido, pedido.dataPedido, pedido.precoTotal, pedido.quantidadeTotal, pedido.pagamento, cliente.nome as cliente, cliente.cpf, usuario.nome as usuario\r\n"
					+ "from pedido\r\n"
					+ "inner join usuario on usuario.ID = pedido.idUsuario\r\n"
					+ "left join cliente on cliente.ID = pedido.idCliente where pedido.dataPedido >= ? and pedido.dataPedido <= ? and "+filtro+" like ? order by pedido.dataPedido;";
			connection = SingleConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, intervaloData[0]);
			statement.setString(2, intervaloData[1]);
			statement.setString(3, search+"%");
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Pedido pedido = new Pedido();
				
				Usuario usuario = new Usuario();
				usuario.setNome(resultSet.getString("usuario"));
				
				Cliente cliente = new Cliente();
				cliente.setNome(resultSet.getString("cliente"));
				cliente.setCpf(resultSet.getLong("cpf"));
				
				List<itemPedido> itemPedidos = this.listarItemPedido(resultSet.getInt("idPedido"));
				
				pedido.setIdPedido(resultSet.getInt("idPedido"));
				pedido.setData(LocalDate.parse(resultSet.getString("dataPedido"), DateTimeFormatter.ofPattern("yyyy-MM-dd")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				
				pedido.setPrecoTotal(resultSet.getDouble("precoTotal"));
				pedido.setQuantidadeTotal(resultSet.getInt("quantidadeTotal"));
				pedido.setPagamento(resultSet.getInt("pagamento"));
				pedido.setCliente(cliente);
				pedido.setUsuario(usuario);
				pedido.setListaDeItens(itemPedidos);
				
				listaDePedidos.add(pedido);
			}
			
			statement.close();
			connection.commit();
			return listaDePedidos;
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			SingleConnection.closeConection();
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
				produto.setEstoque(itemPedido.getQuantidade());
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
