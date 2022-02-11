package com.projeto.sistemafarmacia.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class SobreController implements Initializable{

	@FXML
    private JFXButton btnClose;
		
    @FXML
    void eventFClose(ActionEvent event) {
    	Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
    }
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
