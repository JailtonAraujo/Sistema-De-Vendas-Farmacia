package com.projeto.sistemafarmacia.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
	private Usuario usuarioSelecionado = new Usuario();
	
	@FXML
    private JFXComboBox<String> boxDivisão;

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
    private TableColumn<Usuario, String> columnNomeTblUsuario;

    @FXML
    private TableView<Usuario> tblUsuario;

    @FXML
    private TextField txtBusca;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtLogin;

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtSenha;

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
    	
		if (ObterModelo() != null) {
			if (daoUsuario.cadastrarUsuario(ObterModelo())) {
				JOptionPane.showMessageDialog(null, "USUARIO SALVO COM SUCESSO!", "SUCESSO!", 1);
			}
		} else {
			JOptionPane.showMessageDialog(null, "DADOS INVALIDOS, VERIFIQUE-OS!", "ERRO!", 0);
		}
	}

    @FXML
    void onMouseClickTable(MouseEvent event) {
    	usuarioSelecionado = listaUsuarios.get(tblUsuario.getSelectionModel().getSelectedIndex());
    	setarCompos();
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
		montarComboBoxDivisao();
	}

	@Override
	public Usuario ObterModelo() {
		String id = txtId.getText();
		String nome = txtNome.getText();
		String login = txtLogin.getText();
		String senha = txtSenha.getText();
		String setor = boxDivisão.getSelectionModel().getSelectedItem().toString();
		
		if(id == null || id.isBlank()) {
			id = "0";
		}
		
		String [] dados = {nome,login,senha};
		
		if(ValidarCampo(dados)) {
			Usuario usuario = new Usuario();
			boolean isAdmin = false;
			usuario.setNome(nome);
			usuario.setID(Integer.parseInt(id));
			usuario.setLogin(login);
			usuario.setSenha(senha);
			
			if(boxDivisão.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("ADMINISTRADOR")) {
				isAdmin = true;
			}else {
				isAdmin = false;
			}
			
			return usuario;
		}
		return null;
	}

	@Override
	public void ShowView(String Resource, String Title) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void LimparCampos() {
		txtId.setText("");
		txtLogin.setText("");
		txtNome.setText("");
		txtSenha.setText("");
		
	}

	@Override
	public boolean ValidarCampo(String[] dados) {
		boolean validado = true;
		for(int i = 0; i < dados.length;i++) {
			if(dados[i] == null || dados[i].isEmpty() || dados[i].trim() == "") {
				validado = false;
				break;
			}
		}
		return validado;
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
		txtId.setText(Integer.toString(usuarioSelecionado.getID()));
		txtLogin.setText(usuarioSelecionado.getLogin());
		txtNome.setText(usuarioSelecionado.getNome());
		txtSenha.setText(usuarioSelecionado.getSenha());
		
	}
	
	public void montarColunas() {
		columnLogin.setCellValueFactory(new PropertyValueFactory("login"));
		columnNomeTblUsuario.setCellValueFactory(new PropertyValueFactory("nome"));
		columnIdTblUsuario.setCellValueFactory(new PropertyValueFactory("ID"));
		
	}
	
	public void montarComboBoxDivisao() {
		List<String> listaDivisao = new ArrayList<String>();
		listaDivisao.add("FUNCIONARIO");
		listaDivisao.add("ADMINISTRADOR");
		ObservableList<String> observableListDivisao = FXCollections.observableArrayList(listaDivisao);
		boxDivisão.getItems().setAll(observableListDivisao);
		
		boxDivisão.getSelectionModel().selectFirst();
	}

}
