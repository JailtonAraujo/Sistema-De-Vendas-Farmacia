package com.projeto.sistemafarmacia.model;

import java.util.List;

public class Pedido {

	private int idPedido;
	private String dataPedido;
	private double precoTotal;
	private Cliente cliente;
	private Usuario usuario;
	
	List<itemPedido> listaDeItens;

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
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

	public void setCliente(int idCliente) {
		this.cliente.setID(idCliente);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(int idUsuario) {
		this.usuario.setID(idUsuario);
	}

	public List<itemPedido> getListaDeItens() {
		return listaDeItens;
	}

	public void setListaDeItens(List<itemPedido> listaDeItens) {
		this.listaDeItens = listaDeItens;
	}
	
	
	
}
