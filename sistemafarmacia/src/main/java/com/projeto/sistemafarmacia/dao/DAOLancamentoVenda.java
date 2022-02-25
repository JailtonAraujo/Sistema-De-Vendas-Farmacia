package com.projeto.sistemafarmacia.dao;

import java.sql.Connection;

public class DAOLancamentoVenda {

	private Connection connection = null;

	public DAOLancamentoVenda() {
		this.connection = SingleConnection.getConnection();
	}
	
	
}
