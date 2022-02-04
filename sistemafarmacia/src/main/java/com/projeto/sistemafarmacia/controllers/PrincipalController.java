package com.projeto.sistemafarmacia.controllers;


import java.net.URL;
import java.util.ResourceBundle;


import com.projeto.sistemafarmacia.Interfaces.InterfaceCRUD;
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

public class PrincipalController implements Initializable, InterfaceCRUD<Usuario>{

	@FXML
    private Label UsuarioLabe;

    @FXML
    void eventCadastrar(ActionEvent event) {
    	this.ShowView("/fxml/ViewCliente.fxml", "Usuário");
    }
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub	
	}
	
	@Override
	public void ShowView(String Resource, String Title){
		try {
		Parent root = FXMLLoader.load(getClass().getResource(Resource));
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle(Title);
		stage.setMaximized(false);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/Img/Icon_User_Menu.png")));
		stage.show();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	@Override
	public Usuario ObterModelo() {
		// TODO Auto-generated method stub
		return null;
	}


}
