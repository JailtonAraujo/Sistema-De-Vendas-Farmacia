package com.projeto.sistemafarmacia.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.projeto.sistemafarmacia.dao.DAOHistoricoDeVendas;
import com.projeto.sistemafarmacia.model.Pedido;
import com.projeto.sistemafarmacia.model.Produto;
import com.projeto.sistemafarmacia.model.itemPedido;

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

public class HistoricoDeVendasController implements Initializable {

	private List<Pedido> listaDePedidos;
	private ObservableList<Pedido> observableListPedidos = FXCollections.observableArrayList();
	private List<Produto> listItemPedidoProdutos = new ArrayList<Produto>();
	private ObservableList<Produto> observableListItemPedidoProdutos = FXCollections.observableArrayList();
	private DAOHistoricoDeVendas daoHistoricoDeVendas = new DAOHistoricoDeVendas();
	private Pedido pedidoSelecionado = new Pedido();

	@FXML
	private JFXTextField txtClientePedido;

	@FXML
	private JFXTextField txtData;

	@FXML
	private JFXTextField txtId;

	@FXML
	private JFXTextField txtQuantPedido;

	@FXML
	private JFXTextField txtUsuarioPedido;

	@FXML
	private JFXTextField txtValPedido;

	@FXML
	private TableView<Produto> tblProdutosPedido;
	
	@FXML
    private TableColumn<Produto, String> columnNomeTblProdutos;

    @FXML
    private TableColumn<Produto, Double> columnPrecoTblProdutos;

    @FXML
    private TableColumn<Produto, Integer> columnQuantTblProdutos;
	
	@FXML
	private TableView<Pedido> tblPedidos;

	@FXML
	private TableColumn<Pedido, String> columnClienteTblPedidos;

	@FXML
	private TableColumn<Pedido, String> columnDataTblPedidos;

	@FXML
	private TableColumn<Pedido, String> colunmUsuarioTblPedidos;

	@FXML
	private JFXButton btnSair;

	@FXML
	private Button btnBuscarPedido;

	@FXML
	private TextField txtBuscarPedido;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		montarColunas();

	}

	@FXML
	void actionSair(ActionEvent event) {
		Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
	}

	@FXML
	void eventClickTblPedidos(MouseEvent event) {
		setarCampos();
		AtualizartblProdutos();
	}

	@FXML
	void eventBuscarPedido(ActionEvent event) {
		String dataTxt = txtBuscarPedido.getText();

		LocalDate dataFormatada = LocalDate.parse(dataTxt, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		listaDePedidos = daoHistoricoDeVendas.BuscarPedidos(dataFormatada.toString());
		AtualizarTblPedidos();
		
	}

	@FXML
	void eventPressedBuscarPedido(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {

		}
	}

	public void montarColunas() {
		columnClienteTblPedidos.setCellValueFactory(new PropertyValueFactory("Cliente"));
		columnDataTblPedidos.setCellValueFactory(new PropertyValueFactory("Data"));
		colunmUsuarioTblPedidos.setCellValueFactory(new PropertyValueFactory<Pedido, String>("Usuario"));
	
		columnNomeTblProdutos.setCellValueFactory(new PropertyValueFactory("nome"));
	    columnPrecoTblProdutos.setCellValueFactory(new PropertyValueFactory("preco"));
	    columnQuantTblProdutos.setCellValueFactory(new PropertyValueFactory("estoque"));

	}

	public void AtualizarTblPedidos() {
		observableListPedidos.clear();
		for (Pedido pedido : listaDePedidos) {
			observableListPedidos.add(pedido);
		}

		tblPedidos.getItems().setAll(observableListPedidos);
		tblPedidos.getSelectionModel().selectFirst();
	}
	
	public void AtualizartblProdutos() {
		observableListItemPedidoProdutos.clear();
		for (Produto produto : listItemPedidoProdutos) {
			observableListItemPedidoProdutos.add(produto);
		}
		
		tblProdutosPedido.getItems().setAll(observableListItemPedidoProdutos);
		tblProdutosPedido.getSelectionModel().selectFirst();
	}

	public void setarCampos() {
		pedidoSelecionado = tblPedidos.getItems().get(tblPedidos.getSelectionModel().getSelectedIndex());
		listItemPedidoProdutos.clear();
		
		for ( itemPedido itemPedido:pedidoSelecionado.getListaDeItens()) {
			listItemPedidoProdutos.add(itemPedido.getProduto());
		}
		
		txtClientePedido.setText(pedidoSelecionado.getCliente().getNome());
		txtUsuarioPedido.setText(pedidoSelecionado.getUsuario().getNome());
		txtData.setText(pedidoSelecionado.getData());
		txtId.setText(Integer.toString(pedidoSelecionado.getIdPedido()));
		txtQuantPedido.setText(Integer.toString(pedidoSelecionado.getQuantidadeTotal()));
		txtValPedido.setText(Double.toString(pedidoSelecionado.getPrecoTotal()));
	}
}
