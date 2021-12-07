package com.projeto.sistemafarmacia.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private JFXButton BtnEntrar;

    @FXML
    private JFXPasswordField TextPassWord;

    @FXML
    private JFXTextField TextUserName;

    @FXML
    void Entrar(ActionEvent event) {
        System.out.println("jailtonfn");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}