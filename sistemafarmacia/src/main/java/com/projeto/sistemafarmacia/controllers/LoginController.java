package com.projeto.sistemafarmacia.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.projeto.sistemafarmacia.Interfaces.InterfaceCRUD;
import com.projeto.sistemafarmacia.dao.GenericDAO;
import com.projeto.sistemafarmacia.model.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class LoginController implements Initializable, InterfaceCRUD<Usuario> {

	private GenericDAO<Usuario> DAO = new GenericDAO<Usuario>();
	
	@FXML
	private JFXButton BtnEntrar;

	@FXML
	 private JFXPasswordField TextPassWord;

	@FXML
	private JFXTextField TextUserName;
	
	 @FXML
	    void Entrar(ActionEvent event) {
		 	this.Logar();
	    }


	@Override
	public void initialize(URL url, ResourceBundle rb) {
	
		// TODO
	}

	@Override
	public Usuario ObterModelo() {
		String UserName = TextUserName.getText();
		String PassWord = TextPassWord.getText();
		
		Usuario usuario = new Usuario();
		usuario.setUserName(UserName);
		usuario.setPassWord(PassWord);
		
		return usuario;
	}
	
	public void Logar() {
		Usuario usuario = this.ObterModelo();
		
		if((usuario.getUserName() == null || usuario.getUserName().length() <= 0) || (usuario.getPassWord() == null || usuario.getPassWord().length() <= 0)) {
			JOptionPane.showMessageDialog(null, "OS CAMPOS NÃO FORAM PREENCHIDOS CORRETAMENTE!");
			//this.LimparCampos
		}else {
			Usuario logado = DAO.Logar(usuario);
			
			if(logado == null) {
				JOptionPane.showMessageDialog(null, "NENHUM USUARIO COM ESSE LOGIN E SENHA FOI ENCONTRADO!");
			}else {
				if(usuario.equals(logado)) {
					JOptionPane.showMessageDialog(null, "BEM VINDO "+logado.getUserName().toUpperCase());
					/*CHAMADA DA TELA PRINCIPAL*/
				}
			}
		}
		
	}

	
}
