package com.projeto.sistemafarmacia.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertController implements Initializable {

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

	public void setLabel(String msg) {
		this.lblMsg.setText(msg);
	}

}
