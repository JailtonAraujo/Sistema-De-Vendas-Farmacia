package com.projeto.sistemafarmacia.controllers;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClienteController implements Initializable, InterfaceCRUD<Cliente> {
	
	private DAOCliente daoCliente = new DAOCliente(); 
	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	private ObservableList<Cliente> observableListClientes =  FXCollections.observableArrayList();
	private FormatCadastrarExibir format = new FormatCadastrarExibir();
	private Cliente clienteSelecionado = new Cliente();

	@FXML
    private TableView<Cliente> tblUsuario;
	
	@FXML
    private TableColumn<Cliente, Integer> columnIdTblCLiente;
	
	@FXML
    private TableColumn<Cliente, String > columnNomeTblCliente;
	
	@FXML
    private TableColumn<Cliente, Long> columnCpfTblCliente;
	
	@FXML
    private TableColumn<Cliente, String> columnLogradouroTblCliente;
	
	@FXML
	private TableColumn<Cliente, String> columnTelefoneTblCliente;
	
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
	
	@FXML
	private Button btnBusca;
	
	@FXML
    private TextField txtBusca;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		montarColunas();

	}
	
	@FXML
    void actionExcluirCliente(ActionEvent event) {
		if(txtId.getText() != null && !txtId.getText().isEmpty()) {
			
			int opc = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o cliente selecionado?", "ATENÇÃO!",0);
			
			if(opc == 0) {
				if(daoCliente.excluircliente(clienteSelecionado)) {
					JOptionPane.showMessageDialog(null, "Cliente Excluido com sucesso!");
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "Nenhum cliente foi selecionado para exclusão!", "ERRO!",1);
		}
    }
	
	@FXML
    void onMouseClickTable(MouseEvent event) {
		clienteSelecionado = listaClientes.get(tblUsuario.getSelectionModel().getSelectedIndex());
		setarCompos();
    }
	
	@FXML
    void actionBtnBuscar(ActionEvent event) {
		listaClientes = daoCliente.listarClientes(txtBusca.getText());
		atualizarTabela();
    }

	
	@FXML
    void releasedBuscarCliente(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			listaClientes = daoCliente.listarClientes(txtBusca.getText());
			atualizarTabela();
		}
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
					this.LimparCampos();
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
		String idEndereco =  "0";
		String idContato = "0";
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
			idContato = "0";
			idEndereco = "0";		
		}else {
			idEndereco =  Integer.toString(clienteSelecionado.getEndereco().getID());
			idContato = Integer.toString(clienteSelecionado.getContato().getID());
		}
		
		if(ValidarCampo(dados)) {
			
			contato = new Contato(cadastrarExibir.telefoneToCadastrar(telefone), email);
			contato.setID(Integer.parseInt(idContato));
			endereco = new Endereco(logradouro, cidade, Integer.valueOf(numero));
			endereco.setID(Integer.parseInt(idEndereco));
			
			cliente = new Cliente(nome, contato, endereco, cadastrarExibir.cpfToCadastrar(cpf));
			cliente.setID(Integer.parseInt(id));
			
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

	@Override
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
	
	public void montarColunas() {
		columnNomeTblCliente.setCellValueFactory(new PropertyValueFactory("Nome"));
		columnCpfTblCliente.setCellValueFactory(new PropertyValueFactory("cpf"));
		columnIdTblCLiente.setCellValueFactory(new PropertyValueFactory("ID"));
		
		
		columnLogradouroTblCliente.setCellValueFactory((Param)-> new SimpleStringProperty (Param.getValue().getEndereco().getLogradouro()));
		columnTelefoneTblCliente.setCellValueFactory( (Param)-> new SimpleStringProperty(Long.toString(Param.getValue().getContato().getTelefone())));
		
	}

	@Override
	public void atualizarTabela() {
		observableListClientes.clear();
		for (Cliente cliente : listaClientes) {
			observableListClientes.add(cliente);
		}
		
		tblUsuario.getItems().setAll(observableListClientes);
		tblUsuario.getSelectionModel().selectFirst();
	}

	@Override
	public void setarCompos() {
		try {
		txtId.setText(Integer.toString(clienteSelecionado.getID()));
		txtNome.setText(clienteSelecionado.getNome());
		txtCpf.setText(format.cpfToExbir(clienteSelecionado.getCpf()));
		txtLogradouro.setText(clienteSelecionado.getEndereco().getLogradouro());
		txtCidade.setText(clienteSelecionado.getEndereco().getCidade());
		txtNumero.setText(Integer.toString(clienteSelecionado.getEndereco().getNumero()));
		txtEmail.setText(clienteSelecionado.getContato().getEmail());
		txtTelefone.setText(format.telefoneToExibir(clienteSelecionado.getContato().getTelefone()));
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
