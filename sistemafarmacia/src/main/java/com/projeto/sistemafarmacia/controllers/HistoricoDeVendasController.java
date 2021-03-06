package com.projeto.sistemafarmacia.controllers;

import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.projeto.sistemafarmacia.dao.DAOHistoricoDeVendas;
import com.projeto.sistemafarmacia.model.Pedido;
import com.projeto.sistemafarmacia.model.Produto;
import com.projeto.sistemafarmacia.model.itemPedido;
import com.projeto.sistemafarmacia.util.FormatCadastrarExibir;
import com.projeto.sistemafarmacia.util.reportUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class HistoricoDeVendasController implements Initializable {

	private List<Pedido> listaDePedidos = new ArrayList<Pedido>();
	private ObservableList<Pedido> observableListPedidos = FXCollections.observableArrayList();
	private List<Produto> listItemPedidoProdutos = new ArrayList<Produto>();
	private ObservableList<Produto> observableListItemPedidoProdutos = FXCollections.observableArrayList();
	private DAOHistoricoDeVendas daoHistoricoDeVendas = new DAOHistoricoDeVendas();
	private Pedido pedidoSelecionado = new Pedido();
	
	private List<String> listBoxFiltro = new ArrayList<String>();
	private ObservableList<String> observalBoxFiltro;

	@FXML
	private JFXTextField txtClientePedido;

	@FXML
	private JFXTextField txtData;

	@FXML
	private JFXTextField txtId;
	
	@FXML
    private JFXTextField txtPagamento;

	@FXML
	private JFXTextField txtQuantPedido;

	@FXML
	private JFXTextField txtUsuarioPedido;

	@FXML
	private JFXTextField txtValPedido;
	
	@FXML
    private JFXTextField txtCpfCliente;

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
    private JFXComboBox<String> boxFiltro;

	@FXML
	private JFXButton btnSair;
	
	@FXML
	private JFXButton btnImprimirRelatorio;

	@FXML
	private Button btnBuscarPedido;

	@FXML
	private TextField txtBuscarPedido;
	
	@FXML
    private DatePicker dataPickerDataFinal;

    @FXML
    private DatePicker dataPickerDataInicial;
	
	@FXML
    void actionImprimirRealtorio(ActionEvent event) {
		if(!listaDePedidos.isEmpty() && listaDePedidos.size() > 1) {
			try {
				InputStream caminhoralatorio = this.getClass().getClassLoader().getResourceAsStream("relatorios/rel-vendas.jasper");
			new reportUtil().imprimiRelatorio(listaDePedidos, caminhoralatorio);
			}catch(JRException e) {
				JOptionPane.showMessageDialog(null, "Erro ao imprimir Relatorio, Causa: "+e.getMessage(), "Erro!", 1);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Relatorio Vazio!","ERRO!",1);
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		montarColunas();
		carregarBoxFiltro();

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
		try {
		String filtro = "";
		
		if(boxFiltro.getSelectionModel().getSelectedItem().equalsIgnoreCase("CLIENTE")) {
			filtro="cliente.nome";
		}else if(boxFiltro.getSelectionModel().getSelectedItem().equalsIgnoreCase("USUARIO")) {
			filtro = "usuario.nome";
		}
		
		String [] dataIntervalo = {dataPickerDataInicial.getValue().toString(), dataPickerDataFinal.getValue().toString()};
		String search = txtBuscarPedido.getText();

		listaDePedidos = daoHistoricoDeVendas.BuscarPedidos(dataIntervalo, filtro,search );
		AtualizarTblPedidos();
		
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "O INTERVALO DE DATA INFORMADO N??O ?? V??LIDO!", "ERRO AO BUSCAR PEDIDOS!",1);
			e.printStackTrace();
		}
		
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
		try {
			txtCpfCliente.setText(new FormatCadastrarExibir().cpfToExbir((pedidoSelecionado.getCliente().getCpf())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtUsuarioPedido.setText(pedidoSelecionado.getUsuario().getNome());
		txtData.setText(pedidoSelecionado.getData());
		txtId.setText(Integer.toString(pedidoSelecionado.getIdPedido()));
		txtQuantPedido.setText(Integer.toString(pedidoSelecionado.getQuantidadeTotal()));
		txtValPedido.setText(Double.toString(pedidoSelecionado.getPrecoTotal()));
		if(pedidoSelecionado.getPagamento() == 1) {
			txtPagamento.setText("CREDITO");
		}else {
			txtPagamento.setText("DEBITO");
		}
	}
	
	public void carregarBoxFiltro() {
		
		listBoxFiltro.add("CLIENTE");
		listBoxFiltro.add("USUARIO");
		
		observalBoxFiltro = FXCollections.observableArrayList(listBoxFiltro);
		
		boxFiltro.getItems().setAll(observalBoxFiltro);
		boxFiltro.getSelectionModel().selectFirst();
	}
	
}
