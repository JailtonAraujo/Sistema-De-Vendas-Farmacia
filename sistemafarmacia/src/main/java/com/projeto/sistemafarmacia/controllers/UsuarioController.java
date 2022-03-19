package com.projeto.sistemafarmacia.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.projeto.sistemafarmacia.Interfaces.InterfaceCRUD;
import com.projeto.sistemafarmacia.dao.DAOUsuario;
import com.projeto.sistemafarmacia.model.Usuario;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

@SuppressWarnings({ "rawtypes", "unchecked","unused" })
public class UsuarioController implements Initializable, InterfaceCRUD<Usuario>{

	private DAOUsuario daoUsuario = new DAOUsuario();
	private List<Usuario> listaUsuarios = new ArrayList<Usuario>();
	private ObservableList<Usuario> observableListUsuario = FXCollections.observableArrayList();
	
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
    private TableColumn<Usuario, Long> columnLogin;

    @FXML
    private TableColumn<Usuario, Integer> columnIdTblUsuario;

    @FXML
    private TableColumn<String, String> columnDivisaoTblusuario;

    @FXML
    private TableColumn<Usuario, String> columnNomeTblUsuario;

    @FXML
    private TableView<Usuario> tblUsuario;

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
    	 listaUsuarios =  daoUsuario.buscarUsuario(txtBusca.getText());
    	atualizarTabela();
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
    	Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
    }

    @FXML
    void eventSalvar(ActionEvent event) {

    }

    @FXML
    void onMouseClickTable(MouseEvent event) {

    }

    @FXML
    void releasedBuscarCliente(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER) {
    		daoUsuario.buscarUsuario(txtBusca.getText());
    		atualizarTabela();
    	}
    }

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		montarColunas();
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
		observableListUsuario.clear();
		for(Usuario usuario : listaUsuarios) {
			observableListUsuario.add(usuario);
		}
		
		tblUsuario.getItems().setAll(observableListUsuario);
		tblUsuario.getSelectionModel().selectFirst();
		
	}

	@Override
	public void setarCompos() {
		// TODO Auto-generated method stub
		
	}
	
	public void montarColunas() {
		columnLogin.setCellValueFactory(new PropertyValueFactory("login"));
		columnNomeTblUsuario.setCellValueFactory(new PropertyValueFactory("nome"));
		columnIdTblUsuario.setCellValueFactory(new PropertyValueFactory("ID"));
		
	}

}
