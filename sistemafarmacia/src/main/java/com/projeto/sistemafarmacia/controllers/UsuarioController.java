package com.projeto.sistemafarmacia.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.projeto.sistemafarmacia.Interfaces.InterfaceCRUD;
import com.projeto.sistemafarmacia.model.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class UsuarioController implements Initializable, InterfaceCRUD<Usuario>{

	
	@FXML
    private JFXComboBox<?> boxDivisão;

    @FXML
    private Button btnBusca;

    @FXML
    private JFXButton btnExcluir;

    @FXML
    private JFXButton btnLimpar;

    @FXML
    private JFXButton btnSair;

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private TableColumn<?, ?> columnCpfTblCpf;

    @FXML
    private TableColumn<?, ?> columnIdTblUsuario;

    @FXML
    private TableColumn<?, ?> columnLogradouroTblUsuario;

    @FXML
    private TableColumn<?, ?> columnNomeTblUsuario;

    @FXML
    private TableColumn<?, ?> columnTelefoneTblusuario;

    @FXML
    private TableView<?> tblUsuario;

    @FXML
    private TextField txtBusca;

    @FXML
    private JFXTextField txtCidade;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtLogin;

    @FXML
    private JFXTextField txtLogradouro;

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtNumero;

    @FXML
    private JFXTextField txtSenha;

    @FXML
    private JFXTextField txtTelefone;

    @FXML
    void actionBtnBuscar(ActionEvent event) {

    }

    @FXML
    void actionExcluirUsuario(ActionEvent event) {

    }

    @FXML
    void eventLimpar(ActionEvent event) {

    }

    @FXML
    void eventReleasedTelefone(KeyEvent event) {

    }

    @FXML
    void eventSair(ActionEvent event) {

    }

    @FXML
    void eventSalvar(ActionEvent event) {

    }

    @FXML
    void onMouseClickTable(MouseEvent event) {

    }

    @FXML
    void releasedBuscarCliente(KeyEvent event) {

    }

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@Override
	public Usuario ObterModelo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ShowView(String Resource, String Title) throws IOException {
		// TODO Auto-generated method stub
		
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
