package com.projeto.sistemafarmacia.model;

import java.util.List;
import java.util.Objects;

public class Pedido {

	private int idPedido;
	private String dataPedido;
	private double precoTotal;
	private int quantidadeTotal;
	private int pagamento;
	
	private Cliente cliente;
	private Usuario usuario;
	
	List<itemPedido> listaDeItens;
	
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
		this.cliente = cliente;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(cliente, dataPedido, idPedido, listaDeItens, pagamento, precoTotal, quantidadeTotal,
				usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(dataPedido, other.dataPedido)
				&& idPedido == other.idPedido && Objects.equals(listaDeItens, other.listaDeItens)
				&& pagamento == other.pagamento
				&& Double.doubleToLongBits(precoTotal) == Double.doubleToLongBits(other.precoTotal)
				&& quantidadeTotal == other.quantidadeTotal && Objects.equals(usuario, other.usuario);
	}
	
	
	
}
