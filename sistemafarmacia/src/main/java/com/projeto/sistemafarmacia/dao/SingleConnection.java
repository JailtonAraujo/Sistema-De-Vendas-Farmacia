package com.projeto.sistemafarmacia.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class SingleConnection {

	private static String url = "jdbc:mysql://localhost:3306/bd_sisfarmacia?autoReconnect=true";
	private static String user = "root";
	private static String password = "";

	private static Connection connection = null;

	

	public static Connection getConnection() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");// Carregando driver de conexão com banco
			connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false);

			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public static void closeConection() {
		try {
			connection.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao fechar conexão, causa: "+e.getMessage());
		}
	}
}
