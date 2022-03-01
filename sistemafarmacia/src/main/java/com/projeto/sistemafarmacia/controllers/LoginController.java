package com.projeto.sistemafarmacia.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.projeto.sistemafarmacia.Interfaces.InterfaceCRUD;
import com.projeto.sistemafarmacia.dao.DAOLogin;
import com.projeto.sistemafarmacia.model.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable, InterfaceCRUD<Usuario> {

	private DAOLogin daoLogin = new DAOLogin();
	
	 @FXML
	 private Label lblMsg;

	@FXML
	private JFXButton BtnEntrar;

	@FXML
	private JFXPasswordField TextPassWord;

	@FXML
	private JFXTextField TextUserName;

	@FXML
	void Entrar(ActionEvent event) {
		try {
		this.Logar();
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Causa da Exceção: "+ex.getMessage(), "ERRO!", 0, new ImageIcon(getClass().getResource("/img/Icon_information.png")));
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		// TODO
	}

	@Override
	public Usuario ObterModelo() {
		String login = TextUserName.getText();
		String senha = TextPassWord.getText();

		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);

		return usuario;
	}

	
	
	public void Logar() throws IOException, SQLException {
		Usuario usuario = this.ObterModelo();
		
		if ( (usuario.getLogin() == null || usuario.getLogin().isEmpty()) || (usuario.getSenha() == null || usuario.getSenha().isEmpty())) {
			this.lblMsg.setText("USUARIO OU SENHA INVALIDOS, CONFIRA-OS!");
		}else {
			if(daoLogin.Logar(usuario)) {
				JOptionPane.showMessageDialog(null, "Bem Vindo "+usuario.getLogin().toUpperCase());
				
				this.ShowView("/fxml/ViewPrincipal.fxml", "TELA PRINCIPAL");
			}else {
				this.lblMsg.setText("NENHUM USUARIO ENCONTRADO!");
			}
		}
	}
	
	@Override
	public void ShowView(String Resource, String Title) throws IOException {
		Stage stage1 = (Stage) BtnEntrar.getScene().getWindow();
		stage1.close();

		Parent root = FXMLLoader.load(getClass().getResource(Resource));
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle(Title);
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/Img/Icon_User_Menu.png")));
		stage.setMaximized(true);
		stage.setResizable(true);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}

	@Override
	public void LimparCampos() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean ValidarCampo(String[] dados) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void atualizarTabela() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setarCompos() {
		// TODO Auto-generated method stub
		
	}

}
