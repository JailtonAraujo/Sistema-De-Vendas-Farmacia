package com.projeto.sistemafarmacia.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class HistoricoDeVendasController implements Initializable{

	@FXML
    private JFXButton btnSair;

    @FXML
    void actionSair(ActionEvent event) {
    	Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
