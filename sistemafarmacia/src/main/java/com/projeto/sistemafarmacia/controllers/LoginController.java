package com.projeto.sistemafarmacia.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class LoginController implements Initializable {

	@FXML
	private JFXButton BtnEntrar;

	@FXML
	private AnchorPane TextPassWord;

	@FXML
	private JFXTextField TextUserName;

	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}
}
