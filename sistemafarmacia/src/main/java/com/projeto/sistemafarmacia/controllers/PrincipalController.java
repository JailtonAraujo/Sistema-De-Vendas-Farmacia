package com.projeto.sistemafarmacia.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ResourceBundle;

import com.projeto.sistemafarmacia.Interfaces.InterfaceCRUD;
import com.projeto.sistemafarmacia.dao.DAOLogin;
import com.projeto.sistemafarmacia.model.Usuario;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PrincipalController implements Initializable, InterfaceCRUD<Usuario> {

	private DAOLogin daoLogin = new DAOLogin();
	
	@FXML
	private Label UsuarioLabe;

	@FXML
	private Label lblData;
	
	@FXML
	private Label lblDivisao;
	
	@FXML
    private MenuItem opcaoSair;
	
	@FXML
    private Menu menuUsuario;
	
	@FXML
    private MenuItem menuItemCadastrarUsuario;
	
	@FXML
    void eventShowUsuario(ActionEvent event) {
		ShowView("/fxml/ViewUsuario.fxml", "USUARIO");
    }

	LocalDate data = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	// data = data.format(DataTimeFormatte.ofpattern("dd/MM/yyyy"));



	@FXML
	void eventCadastrar(ActionEvent event) {
		this.ShowView("/fxml/ViewCliente.fxml", "CLIENTE");
	}

	@FXML
	void eventProduto(ActionEvent event) {
		this.ShowView("/fxml/ViewProdutos.fxml", "PRODUTOS");
	}

	@FXML
	void actionLancamentoDeVendas(ActionEvent event) {
		this.ShowView("/fxml/ViewCadastroDeVendas.fxml", "VENDAS");
	}

	@FXML
	void actionSobre(ActionEvent event) {
		this.ShowView("/fxml/ViewSobre.fxml", "SOBRE");
	}
	

    @FXML
    void actionHistoricoDeVendas(ActionEvent event) {
    	this.ShowView("/fxml/ViewHistoricoDeVendas.fxml", "Historico de Vendas");
    }


	@FXML
    void eventSair(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		validarAdmin();
		UsuarioLabe.setText(new DAOLogin().getUserLogado().getNome());
		lblData.setText(data.format(formatter));
		// TODO Auto-generated method stub
	}

	@Override
	public void ShowView(String Resource, String Title) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(Resource));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle(Title);
			stage.setMaximized(false);
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/Img/Icon_User_Menu.png")));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void validarAdmin() {
		if(daoLogin.getUserLogado().isAdmin()) {
			lblDivisao.setText("DIVIS??O: ADMINISTRADOR");
			menuUsuario.setVisible(true);
		}else {
			lblDivisao.setText("DIVIS??O: FUNCIONARIO");
			menuUsuario.setVisible(false);
		}
	}

	@Override
	public Usuario ObterModelo() {
		// TODO Auto-generated method stub
		return null;
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
