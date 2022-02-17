package com.projeto.sistemafarmacia.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertController extends Exception implements Initializable{

	
	public AlertController(String exception) {
		this.lblMsg.setText(exception);

		try {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/ViewAlert.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Exception!");
		stage.setMaximized(false);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		}catch(Exception ex) {
			lblMsg.setText(ex.getMessage());
		}
	}
	
	
	@FXML
	private JFXButton btnOk;

	@FXML
	private Label lblMsg;

	@FXML
	void eventClose(ActionEvent event) {
		Stage stage1 = (Stage) btnOk.getScene().getWindow();
		stage1.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	
}
