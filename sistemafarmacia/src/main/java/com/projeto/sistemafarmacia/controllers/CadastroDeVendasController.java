package com.projeto.sistemafarmacia.controllers;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
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
import com.projeto.sistemafarmacia.dao.DAOLancamentoVenda;
import com.projeto.sistemafarmacia.dao.DAOLogin;
import com.projeto.sistemafarmacia.dao.DAOProduto;
import com.projeto.sistemafarmacia.model.Cliente;
import com.projeto.sistemafarmacia.model.Pedido;
import com.projeto.sistemafarmacia.model.Produto;
import com.projeto.sistemafarmacia.model.Usuario;
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

@SuppressWarnings({ "rawtypes", "unchecked" })
public class CadastroDeVendasController implements Initializable, InterfaceCRUD<Produto>{

	private DAOProduto daoProduto = new DAOProduto();
	private DAOCliente daoCliente = new DAOCliente();
	private DAOLancamentoVenda daoLancamentoVenda = new DAOLancamentoVenda();
	private List<Produto> listaDeProdutos;
	private List<Cliente> listClientes;
	private ObservableList<Produto> observableListProduto = FXCollections.observableArrayList();
	private ObservableList<String> observableListClientes = FXCollections.observableArrayList();
	private ObservableList<Produto> observableListItensPedido = FXCollections.observableArrayList();
	private Produto objetoSelecionado = new Produto();
	private Produto itemSelecionado = new Produto();
	private Cliente clienteSelected = new Cliente();
	private FormatCadastrarExibir format = new FormatCadastrarExibir();
	private List<Produto> listaItemsPedidoProduto = new ArrayList<Produto>();//LISTA CRIADA EM TEMPO DE EXECUÇÃO PARA EXIBIR NA LISTA DE COMPRA OS PRODUTOS SELECIONADOS//
	private List<itemPedido> listaItemsPedido = new ArrayList<itemPedido>();
	
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

    @Override
	public void initialize(URL location, ResourceBundle resources) {
		montarCulunas();
		checkCredito.setSelected(true);
	}
    
    @FXML
    void eventBuscarProduto(ActionEvent event) {
    	this.atualizarTabela();
    	
    }

    @FXML
    void eventSair(ActionEvent event) {
    	if(listaItemsPedido.size() > 0) {
    		JOptionPane.showMessageDialog(null, "Você tem um pedido em aberto, feche-o primeiro para poder sair!","ATENÇÃO!",0);
    	}else {
    	Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
    	}
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
    	if(!checkDebito.isSelected()&&!checkCredito.isSelected()) {
    		checkCredito.setSelected(true);
    	}
    }

    @FXML
    void eventCheckDebito(MouseEvent event) {
    	if(checkCredito.isSelected()) {
    		checkCredito.setSelected(false);
    	}
    	if(!checkDebito.isSelected()&&!checkCredito.isSelected()) {
    		checkCredito.setSelected(true);
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
    		
    		Produto produtoRetornarEstoque = new Produto();
    		int estoqueAtual = 0;
    		/*COMO AS LISTAS DE ItensPedidoProduto e ItensPedido ESTÃO SINCRONIZADAS O MESMO INDEX USADO PARA REMOVER DE ItensPedidoProduto PODE SER USADO PARA REMOVER DE itensPedido*/
    		listaItemsPedidoProduto.remove(tblItemPedido.getSelectionModel().getSelectedIndex());
    		listaItemsPedido.remove(tblItemPedido.getSelectionModel().getSelectedIndex());
    		produtoRetornarEstoque = tblItemPedido.getItems().get(tblItemPedido.getSelectionModel().getSelectedIndex());
    		estoqueAtual = daoLancamentoVenda.verificarEstoque(produtoRetornarEstoque.getIdTabela());
    		produtoRetornarEstoque.setEstoque((estoqueAtual+produtoRetornarEstoque.getEstoque()));
    		daoLancamentoVenda.atualizarEstoque(produtoRetornarEstoque);//DEVOLVENDO O PRODUTO RETIRADO DA LISTA DE COMPRAS AO ESTOQUE.//
        	atualizarTblItens();
        	atualizarTabela();
        	LimparCampos();
    		
    	}else {
    		JOptionPane.showMessageDialog(null, "Nenhum Produto Selecionado!");
    	}
    	
    }
    
    @FXML
    void eventFecharPedido(ActionEvent event) {
    	int opc=0;
    	
    	if(listaItemsPedido.isEmpty() || listaItemsPedido.size() < 1) {
    		JOptionPane.showMessageDialog(null, "A LISTA DE ITENS ESTÁ VAZIA!","ATENÇÃO",0);
    	}
    	
    	else if(montarPedido()==null) {
    		JOptionPane.showMessageDialog(null, "É OBRIGATORIO INFORMAR O CLIENTE PARA COMPRAS NO DÉBITO!", "ATENÇÃO!",1);
    	
    	}else {
    		
    		opc = JOptionPane.showConfirmDialog(null, "TEM CERTEZA QUE DESEJA FECHAR O PEDIDO ATUAL?", "ATENÇÃO", 0);
    		if(opc==0) {    			
    			if(daoLancamentoVenda.salvarPedido(montarPedido())) {
    				JOptionPane.showMessageDialog(null, "PEDIDO SALVO COM SUCESSO!", "ATENÇÃO!",1);
    				limparTbls();
    				LimparCampos();
    				
    			}else {
    				JOptionPane.showMessageDialog(null, "ERRO AO SALVAR PEDIDO!", "ATENÇÃO!",0);
    			}
    		}
		}
    
    }
    
    @FXML
    void eventCancelarCompra(ActionEvent event) {
    	int opc = JOptionPane.showConfirmDialog(null, "TEM CERTEZA QUE DESEJA CANCELAR O PEDIDO ATURAL?", "ATENÇÃO!", 0);
    	if(opc==0) {
    		int estoqueAtual = 0;
    		for (itemPedido itemPedido : listaItemsPedido) {
				Produto produtoDevolverAoEstoque = new Produto();
				produtoDevolverAoEstoque = itemPedido.getProduto();
				estoqueAtual = daoLancamentoVenda.verificarEstoque(produtoDevolverAoEstoque.getIdTabela());
				produtoDevolverAoEstoque.setEstoque((estoqueAtual + produtoDevolverAoEstoque.getEstoque()));
				daoLancamentoVenda.atualizarEstoque(produtoDevolverAoEstoque);
			}
    		
    		Stage stage = (Stage) btnSair.getScene().getWindow();
    		stage.close();
    	}
    }
    
    @FXML
    void eventAdicionar(ActionEvent event) throws NumberFormatException, HeadlessException, SQLException {
    	if(objetoSelecionado != null && objetoSelecionado.getIdTabela() > 0) {
    		if(Integer.parseInt(txtQuantidade.getText()) > 0) {
    			itemPedido itemPedido = new itemPedido();
    			Produto produtoComEstoqueAtualizado = new Produto();
    			itemSelecionado.setEstoque(Integer.parseInt(txtQuantidade.getText()));
    			
    			int quantidadeProdutoEstoque = daoLancamentoVenda.verificarEstoque(objetoSelecionado.getIdTabela());
    			
    			if(quantidadeProdutoEstoque < Integer.parseInt(txtQuantidade.getText())) {
    				JOptionPane.showMessageDialog(null, "Produto Sem Estoque Disponivel!","ATENÇÂO",1);
    			}
    			
    			else {
    				listaItemsPedidoProduto.add(itemSelecionado);//ADICIONADO PRODUTO SELECIONADO A LISTA (ATUAL) DO TIPO produto PARA MOSTRAR PARA O USUARIO//
    				itemPedido = new itemPedido(0,Integer.parseInt(txtQuantidade.getText()), itemSelecionado);//CRIANDO O ITEM DO PEDIDO APARTIR DO PRODUTO SELECIONADO//
        			listaItemsPedido.add(itemPedido);//ADICIONANDO O MESMO PRODUTO PARA A LISTA (ATUAL) DO TIPO itenPedido PARA DEPOIS SER ADICIONADA AO PEDIDO E SALVA EM BANCO//
        			produtoComEstoqueAtualizado.setIdTabela(itemSelecionado.getIdTabela());
        			produtoComEstoqueAtualizado.setEstoque((quantidadeProdutoEstoque-itemSelecionado.getEstoque()));
        			daoLancamentoVenda.atualizarEstoque(produtoComEstoqueAtualizado);//DANDO BAIXA DO PRODUTO NO ESTOQUE//
        			atualizarTblItens();
        			atualizarTabela();
        			LimparCampos();
        			
    			}
    			
    			if(quantidadeProdutoEstoque < 20) {
    				JOptionPane.showMessageDialog(null, "Produto Com Baixa Quantidade em Estoque!","ALERTA!",1);
    			}
    		}else {
    			JOptionPane.showMessageDialog(null, "Informe a Quantidade do Produto","ATENÇÃO!",0);
    		}
    	}else {
    		JOptionPane.showMessageDialog(null, "Nenhum Produto foi selecionado!","ATENÇÃO!",0);
    	}
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
		for (Produto produto : listaItemsPedidoProduto) {
			quantTotal += produto.getEstoque();
			precoTotal += (produto.getPreco() * produto.getEstoque());
			observableListItensPedido.add(produto);
		}
		
		txtPrecoTotal.setText(Double.toString(precoTotal));
		txtTotalItens.setText(Integer.toString(quantTotal));
		
		tblItemPedido.getItems().setAll(observableListItensPedido);
		tblItemPedido.getSelectionModel().selectFirst();
		
	}
	
	public Pedido montarPedido() {
		
		if(listaItemsPedido.isEmpty()|| listaItemsPedido.size() < 1) {
			return null;
		}else {
			
		Pedido pedido;
		Usuario userLogado;
    	LocalDate dataAtual = LocalDate.now();
    	double precoTotal = Double.valueOf(txtPrecoTotal.getText());
    	int quantidadeTotal = Integer.valueOf(txtTotalItens.getText());
    	userLogado = new DAOLogin().getUserLogado();
    	int pagamento = 0;
    	if(checkCredito.isSelected()) {//1 CORRESPONSE AO PAGAMENTO COMO CREDITO E 2 COMO DÉDITO//
    		pagamento = 1;
    	}else {
    		pagamento = 2;
    	}
    	
    	if(checkDebito.isSelected() && (boxCliente.getSelectionModel().isEmpty()||boxCliente.getSelectionModel() == null)) {
    		/*O USUARIO TENTOU FECHAR UM PEDIDO DO DEBITO SEM INFORMAR UM CLIENTE*/
			return null;
		}else {
			/*QUANDO O USUARIO FECHA UM PEDIDO E NO DÉBITO E INFORMA CORRETAMENTO O CLIENTE*/
			pedido = new Pedido(dataAtual.toString(), precoTotal, quantidadeTotal, pagamento, clienteSelected,userLogado, listaItemsPedido );
			return pedido;
			}
		}
    	
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
		txtCodigoDoProduto.setText("");
		txtEstoque.setText("");
		txtNome.setText("");
		txtPreco.setText("");
		txtQuantidade.setText("0");
	}
	
	public void limparTbls() {
		listaItemsPedido.clear();
		listaItemsPedidoProduto.clear();
		observableListItensPedido.clear();
		atualizarTblItens();
	}

	@Override
	public boolean ValidarCampo(String[] dados) {
		// TODO Auto-generated method stub
		return false;
	}

}
