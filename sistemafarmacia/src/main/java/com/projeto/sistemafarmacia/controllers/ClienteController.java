package com.projeto.sistemafarmacia.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.projeto.sistemafarmacia.Interfaces.InterfaceCRUD;
import com.projeto.sistemafarmacia.dao.DAOCliente;
import com.projeto.sistemafarmacia.model.Cliente;
import com.projeto.sistemafarmacia.model.Contato;
import com.projeto.sistemafarmacia.model.Endereco;
import com.projeto.sistemafarmacia.util.FormatCadastrarExibir;
import com.projeto.sistemafarmacia.util.TextFieldFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClienteController implements Initializable, InterfaceCRUD<Cliente> {

	@FXML
	private JFXTextField txtCidade;

	@FXML
	private JFXTextField txtCpf;

	@FXML
	private JFXTextField txtEmail;

	@FXML
	private JFXTextField txtId;

	@FXML
	private JFXTextField txtLogradouro;

	@FXML
	private JFXTextField txtNome;

	@FXML
	private JFXTextField txtNumero;

	@FXML
	private JFXTextField txtTelefone;

	@FXML
	private JFXButton btnSair;

	
	private DAOCliente daoCliente = new DAOCliente(); 
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	@FXML
    void eventReleased(KeyEvent event) {
		TextFieldFormatter formatter = new TextFieldFormatter();
		formatter.setMask("###.###.###-##");
		formatter.setCaracteresValidos("0123456789");
		formatter.setTf(txtCpf);
		formatter.formatter();
    }
	
	@FXML
    void eventReleasedTelefone(KeyEvent event) {
		TextFieldFormatter fieldFormatter = new TextFieldFormatter();
		fieldFormatter.setMask("(##)#####-####");
		fieldFormatter.setCaracteresValidos("0123456789");
		fieldFormatter.setTf(txtTelefone);
		fieldFormatter.formatter();
    }

	@FXML
	void eventSalvar(ActionEvent event) {
			
			if(ObterModelo() == null) {
				JOptionPane.showMessageDialog(null, "informações inválidas, confira os dados!");
			}else {
				
				if(daoCliente.Insert(ObterModelo())) {
					JOptionPane.showMessageDialog(null, "Cliente Cadastrado com Sucesso!");
				}
			}
		
		}

	

	@FXML
	void eventLimpar(ActionEvent event) {
		this.LimparCampos();
	}

	@FXML
	void eventSair(ActionEvent event) {
		Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
	}

	@Override
	public Cliente ObterModelo() {

		Cliente cliente;
		Contato contato;
		Endereco endereco;
		FormatCadastrarExibir cadastrarExibir = new FormatCadastrarExibir();

		String id = txtId.getText();
		String nome = txtNome.getText();
		String cpf = txtCpf.getText();
		String logradouro = txtLogradouro.getText();
		String cidade = txtCidade.getText();
		String numero = txtNumero.getText();
		String telefone = txtTelefone.getText();
		String email = txtEmail.getText();
		
		String dados [] = {nome, cpf, logradouro, cidade, numero, telefone, email};

		if (id == null || id.equalsIgnoreCase("") || id.isEmpty()) {
			id = "0";
		}
		
		if(ValidarCampo(dados)) {
			
			contato = new Contato(cadastrarExibir.telefoneToCadastrar(telefone), email);
			endereco = new Endereco(logradouro, cidade, Integer.valueOf(numero));
			
			cliente = new Cliente(nome, contato, endereco, cadastrarExibir.cpfToCadastrar(cpf));
			
			return cliente;
		}
		
		return null;

	}

	@Override
	public void LimparCampos() {

		txtId.setText("");
		txtNome.setText("");
		txtCpf.setText("");
		txtLogradouro.setText("");
		txtCidade.setText("");
		txtNumero.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");

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
			stage.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean ValidarCampo(String dados []) {
		
		boolean validado = true;
		
		if(dados[1].trim().length() < 14 || dados[5].trim().length() < 14 ) {
			validado = false;
		}else {
		
		for(int i = 0; i < dados.length; i++) {
			if(dados[i] == null || dados[i].trim().isEmpty() || dados.length < 0) {
				validado = false;
				break;
			}
		}
				
		}
		
		return validado;
	}
}
