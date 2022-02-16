package com.projeto.sistemafarmacia.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.projeto.sistemafarmacia.Interfaces.InterfaceCRUD;
import com.projeto.sistemafarmacia.dao.GenericDAO;
import com.projeto.sistemafarmacia.model.Cliente;
import com.projeto.sistemafarmacia.model.Contato;
import com.projeto.sistemafarmacia.model.Endereco;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

	private final GenericDAO<Cliente> genericDAO = new GenericDAO<Cliente>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	void eventSalvar(ActionEvent event) {

		Cliente cliente = this.ObterModelo();


			if (this.genericDAO.Salvar(cliente)) {

				JOptionPane.showMessageDialog(null, "CLIENTE CADASTRADO COM SUCESSO!", "SUCESSO!", 1);
				this.LimparCampos();
			} else {

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

		String id = txtId.getText();
		String nome = txtNome.getText();
		String cpf = txtCpf.getText();
		String logradouro = txtLogradouro.getText();
		String cidade = txtCidade.getText();
		String numero = txtNumero.getText();
		String telefone = txtTelefone.getText();
		String email = txtEmail.getText();

		if (id == null || id.equalsIgnoreCase("")) {
			id = "0";
			cliente = new Cliente(Integer.parseInt(id), nome, cpf);
			contato = new Contato(telefone, email);
			endereco = new Endereco(logradouro, cidade, numero);

			contato.setPessoa(cliente);
			cliente.setContato(contato);

			if (this.ValidarCampo(cliente)) {
				return cliente;
			} else {
				JOptionPane.showMessageDialog(null,
						"Os Campos Não Foram Preenchidos Corretamente, Por Favor, Confira-os!", "ERRO!", 0);
			}

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
	public void ShowView(String Resource, String Title, String msg) {
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

	public boolean ValidarCampo(Cliente cliente) {
		if ((cliente.getNome() != null && cliente.getNome().length() > 0)
				&& (cliente.getCpf() != null && cliente.getCpf().length() > 0 && cliente.getCpf().length() == 12)
				&& (cliente.getContato().getEmail() != null && cliente.getContato().getEmail().length() > 0)
				&& (cliente.getContato().getTelefone() != null && cliente.getContato().getTelefone().length() > 0
						&& cliente.getContato().getTelefone().length() == 11)) {
			return true;
		} else {
			return false;
		}
	}
}
