package com.projeto.sistemafarmacia.model;

import java.util.List;

public class Pedido {

	private int idPedido;
	private String dataPedido;
	private double precoTotal;
	private int quantidadeTotal;
	private int pagamento;
	
	private Cliente cliente;
	private Usuario usuario;
	
	
	public Pedido() {}
	
	public Pedido(String dataPedido, double precoTotal, int quantidadeTotal,int pagamento, Cliente cliente,
			Usuario usuario, List<itemPedido> listaDeItens) {
		this.dataPedido = dataPedido;
		this.precoTotal = precoTotal;
		this.quantidadeTotal = quantidadeTotal;
		this.pagamento = pagamento;
		this.cliente = cliente;
		this.usuario = usuario;
		this.listaDeItens = listaDeItens;
	}

	List<itemPedido> listaDeItens;

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	
	public int getPagamento() {
		return pagamento;
	}

	public void setPagamento(int pagamento) {
		this.pagamento = pagamento;
	}

	public int getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(int quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

	public String getData() {
		return dataPedido;
	}

	public void setData(String data) {
		this.dataPedido = data;
	}

	public double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente.setID(cliente.getID());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario.setID(usuario.getID());
	}

	public List<itemPedido> getListaDeItens() {
		return listaDeItens;
	}

	public void setListaDeItens(List<itemPedido> listaDeItens) {
		this.listaDeItens = listaDeItens;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", dataPedido=" + dataPedido + ", precoTotal=" + precoTotal
				+ ", quantidadeTotal=" + quantidadeTotal + ", pagamento=" + pagamento + ", cliente=" + cliente
				+ ", usuario=" + usuario + ", listaDeItens=" + listaDeItens + "]";
	}
	
	
	
	
}
