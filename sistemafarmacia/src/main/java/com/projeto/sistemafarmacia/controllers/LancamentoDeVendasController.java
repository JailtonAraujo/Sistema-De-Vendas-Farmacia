package com.projeto.sistemafarmacia.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.projeto.sistemafarmacia.Interfaces.InterfaceCRUD;
import com.projeto.sistemafarmacia.dao.DAOCliente;
import com.projeto.sistemafarmacia.dao.DAOProduto;
import com.projeto.sistemafarmacia.model.Cliente;
import com.projeto.sistemafarmacia.model.Produto;
import com.projeto.sistemafarmacia.model.itemPedido;
import com.projeto.sistemafarmacia.util.FormatCadastrarExibir;

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

public class LancamentoDeVendasController implements Initializable, InterfaceCRUD<Produto>{

	private DAOProduto daoProduto = new DAOProduto();
	private DAOCliente daoCliente = new DAOCliente();
	private List<Produto> listaDeProdutos;
	private List<Cliente> listClientes;
	private ObservableList<Produto> observableListProduto = FXCollections.observableArrayList();
	private ObservableList<String> observableListClientes = FXCollections.observableArrayList();
	private ObservableList<Produto> observableListItensPedido = FXCollections.observableArrayList();
	private Produto objetoSelecionado = new Produto();
	private Produto itemSelecionado = new Produto();
	private Cliente clienteSelected = new Cliente();
	private FormatCadastrarExibir format = new FormatCadastrarExibir();
	private List<Produto> listaItemsPedido = new ArrayList<Produto>();
	
	@FXML
	private JFXComboBox<String> boxCliente;
	
	@FXML
    private JFXCheckBox checkCredito;

    @FXML
    private JFXCheckBox checkDebito;
	
	@FXML
    private Button btnBuscaProduto;

    @FXML
    private JFXButton btnSair;
    
    @FXML
    private JFXButton btnBuscarClienteNome;
    
    @FXML
    private JFXTextField txtBuscaClienteNome;
    
    @FXML
    private JFXTextField txtCodigoDoProduto;

    @FXML
    private JFXTextField txtEstoque;
    
    @FXML
    private JFXTextField txtCpf;

    @FXML
    private JFXTextField txtPreco;

    @FXML
    private JFXTextField txtQuantidade;
    
    @FXML
    private JFXTextField txtNome;

    @FXML
    private TableView<Produto> tblProdutos;

    @FXML
    private TableColumn<Produto, String> colunmDescricao;

    @FXML
    private TableColumn<Produto, Integer> colunmEstoque;

    @FXML
    private TableColumn<Produto, String> colunmNome;

    @FXML
    private TableColumn<Produto, Double> colunmPreco;
    
    @FXML
    private TableView<Produto> tblItemPedido;
    
    @FXML
    private TableColumn<Produto, String> columnTblItemName;

    @FXML
    private TableColumn<Produto, Double> columnTblItemPreco;

    @FXML
    private TableColumn<Produto, Integer> columnTblItemQuantidade;
    
    @FXML
    private TextField txtBuscaProduto;
    
    @FXML
    private TextField txtPrecoTotal;


    @FXML
    private TextField txtTotalItens;

    @FXML
    void eventBuscarProduto(ActionEvent event) {
    	this.atualizarTabela();
    	
    }

    @FXML
    void eventSair(ActionEvent event) {
    	Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
    }
    
    @FXML
    void eventClickTable(MouseEvent event) {
    	setarCompos();
    }
    
    @FXML
    void eventCheckCredito(MouseEvent event) {
    	if(checkDebito.isSelected()) {
    		checkDebito.setSelected(false);
    	}
    }

    @FXML
    void eventCheckDebito(MouseEvent event) {
    	if(checkCredito.isSelected()) {
    		checkCredito.setSelected(false);
    	}
    }
    
    @FXML
    void eventPressd(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER) {
    		atualizarTabela();
    	}
    }
    
    @FXML
    void eventRemoverItem(ActionEvent event) {
    	
    	if(tblItemPedido.getSelectionModel().getSelectedIndex() >= 0 && tblItemPedido.getSelectionModel().getSelectedIndex() < listaItemsPedido.size()) {
    		
    		listaItemsPedido.remove(tblItemPedido.getSelectionModel().getSelectedIndex());
        	atualizarTblItens();
    		
    	}else {
    		JOptionPane.showMessageDialog(null, "Nenhum Produto Selecionado!");
    	}
    	
    }
    
    @FXML
    void eventFecharPedido(ActionEvent event) {

    }
    
    @FXML
    void eventCancelarCompra(ActionEvent event) {

    }
    
    @FXML
    void eventAdicionar(ActionEvent event) {
    	if(objetoSelecionado != null) {
    		if(Integer.parseInt(txtQuantidade.getText()) > 0) {  
    			itemSelecionado.setEstoque(Integer.parseInt(txtQuantidade.getText()));
    			listaItemsPedido.add(itemSelecionado);
    			atualizarTblItens();
    		}else {
    			JOptionPane.showMessageDialog(null, "Informe a Quantidade do Produto");
    		}
    	}else {
    		JOptionPane.showMessageDialog(null, "Nenhum Produto foi selecionado!");
    	}
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		montarCulunas();
		checkCredito.setSelected(true);
	}
	
	 @FXML
	    void eventBuscarClienteNome(ActionEvent event) {
		 	atualizarBoxCliente();
	    }
	 
	 @FXML
	    void eventBoxClientes(ActionEvent event) throws ParseException {
		 
		 if(boxCliente.getSelectionModel().getSelectedIndex() >= 0 && boxCliente.getSelectionModel().getSelectedIndex() < listClientes.size()) {
			 clienteSelected = listClientes.get(boxCliente.getSelectionModel().getSelectedIndex());
			 txtCpf.setText(format.cpfToExbir(clienteSelected.getCpf()));
		 }else {}
		 
	    }

	
	
	@Override
	public void atualizarTabela() {
		observableListProduto.clear();
		listaDeProdutos = daoProduto.buscarProdutosPorNome(txtBuscaProduto.getText());
		
		for (Produto produto : listaDeProdutos) {
			observableListProduto.add(produto);
		}
		tblProdutos.getItems().setAll(observableListProduto);
		tblProdutos.getSelectionModel().selectFirst();
	}
	

	@Override
	public Produto ObterModelo() {
		
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
	
	public void montarCulunas() {
		colunmNome.setCellValueFactory(new PropertyValueFactory("nome"));
		colunmDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
		colunmEstoque.setCellValueFactory(new PropertyValueFactory("estoque"));
		colunmPreco.setCellValueFactory(new PropertyValueFactory("preco"));
		
		columnTblItemName.setCellValueFactory(new PropertyValueFactory("nome"));
		columnTblItemQuantidade.setCellValueFactory(new PropertyValueFactory("estoque"));
		columnTblItemPreco.setCellValueFactory(new PropertyValueFactory("preco"));
		
	}

	@Override
	public void setarCompos() {
		objetoSelecionado = tblProdutos.getItems().get(tblProdutos.getSelectionModel().getSelectedIndex());
		itemSelecionado =  tblProdutos.getItems().get(tblProdutos.getSelectionModel().getSelectedIndex());
		txtCodigoDoProduto.setText(Long.toString(objetoSelecionado.getId()));
		txtEstoque.setText(Integer.toString(objetoSelecionado.getEstoque()));
		txtPreco.setText(Double.toString(objetoSelecionado.getPreco()));
		txtNome.setText(objetoSelecionado.getNome());
	}
	
	public void atualizarBoxCliente(){
		listClientes = daoCliente.buscarClientePorNome(txtBuscaClienteNome.getText());
		observableListClientes.clear();
		for (Cliente cliente : listClientes) {
			observableListClientes.add(cliente.getNome());
		}
		
		boxCliente.getItems().setAll(observableListClientes);
		boxCliente.getSelectionModel().selectFirst();
		
	}
	
	public void atualizarTblItens() {
		observableListItensPedido.clear();
		double precoTotal = 0.0;
		int quantTotal = 0;
		for (Produto produto : listaItemsPedido) {
			quantTotal += produto.getEstoque();
			precoTotal += (produto.getPreco() * produto.getEstoque());
			observableListItensPedido.add(produto);
		}
		
		txtPrecoTotal.setText(Double.toString(precoTotal));
		txtTotalItens.setText(Integer.toString(quantTotal));
		
		tblItemPedido.getItems().setAll(observableListItensPedido);
		tblItemPedido.getSelectionModel().selectFirst();
		
	}

}
