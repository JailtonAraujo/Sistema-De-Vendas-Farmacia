package com.projeto.sistemafarmacia.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.projeto.sistemafarmacia.Interfaces.InterfaceCRUD;
import com.projeto.sistemafarmacia.dao.DAOProduto;
import com.projeto.sistemafarmacia.model.Produto;
import com.projeto.sistemafarmacia.util.TextFieldFormatter;

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

public class ProdutoController implements InterfaceCRUD<Produto>, Initializable{
	
	private DAOProduto daoProduto = new DAOProduto();
	List<Produto> listaDeProdutos = null;
	ObservableList<Produto> observableListProdutos = FXCollections.observableArrayList();
	
		@FXML
	    private JFXButton btnEditar;

	    @FXML
	    private JFXButton btnEditar1;

	    @FXML
	    private JFXButton btnExcluir;

	    @FXML
	    private JFXButton btnSair;

	    @FXML
	    private JFXButton btnSalvar;

	    @FXML
	    private TableView<Produto> tblProduto;
	    
	    @FXML
	    private TableColumn<Produto, Long> columnIdTableProduto;
	    
	    @FXML
	    private TableColumn<Produto, String> columnNomeTableProduto;
	    
	    @FXML
	    private TableColumn<Produto, String> columnDescricaoTableProduto;
	    
	    @FXML
	    private TableColumn<Produto, Integer> columnEstoqueTableProduto;
	    
	    @FXML
	    private TableColumn<Produto, Double> columnPrecoTableProduto;

	    @FXML
	    private TextField txtBusca;

	    @FXML
	    private JFXTextField txtDescricao;

	    @FXML
	    private JFXTextField txtEstoque;
	    
	    @FXML
	    private JFXTextField txtIdTable;

	    @FXML
	    private JFXTextField txtId;

	    @FXML
	    private JFXTextField txtNome;

	    @FXML
	    private JFXTextField txtPreco;
	    
	    @FXML
	    private Button btnBusca;
	    
	    @FXML
	    void clickTableProduto(MouseEvent event) {
	    	setarCompos();
	    }
	    
	    @FXML
	    void actionBuscarProduto(ActionEvent event) {
	    	listaDeProdutos = daoProduto.buscarProdutosPorNome(txtBusca.getText());
	    	atualizarTabela();
	    }

	    @FXML
	    void releasedTxtBusca(KeyEvent event) {
	    	if(event.getCode() == KeyCode.ENTER) {
	    		listaDeProdutos = daoProduto.buscarProdutosPorNome(txtBusca.getText());
		    	atualizarTabela();
	    	}
	    }
	    

	    @FXML
	    void eventReleaseEstoque(KeyEvent event) {
	    	TextFieldFormatter formatter = new TextFieldFormatter();
	    	formatter.setMask("##########");
	    	formatter.setCaracteresValidos("0123456789");
	    	formatter.setTf(txtEstoque);
	    	formatter.formatter();
	    }

	    @FXML
	    void eventReleaseId(KeyEvent event) {
	    	TextFieldFormatter formatter = new TextFieldFormatter();
	    	formatter.setMask("###################");
	    	formatter.setCaracteresValidos("0123456789");
	    	formatter.setTf(txtId);
	    	formatter.formatter();
	    }

	    @FXML
	    void eventReleasePreco(KeyEvent event) {
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

	    @FXML
	    void eventSalvar(ActionEvent event) {
	    	if(ObterModelo() == null) {
	    		JOptionPane.showMessageDialog(null, "Dados inválidos, confira-os!", "ERRO!", 0);
	    	}else {
	    	if(daoProduto.Insert(ObterModelo())) {
	    		JOptionPane.showMessageDialog(null, "Produto Cadastrado Com Sucesso!");
	    		this.LimparCampos();
	    	}else {}		
	    	}
	    }
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.MontarColunas();
		
	}

	@Override
	public Produto ObterModelo() {
		String id = txtId.getText().trim();
		String nome = txtNome.getText();
		String descricao = txtDescricao.getText();
		String estoque = txtEstoque.getText().trim();
		String preco = txtPreco.getText().trim();
		
		Produto produto;
		
		String dados[] = {id, nome, descricao, estoque, preco};
		
		if(ValidarCampo(dados)) {
			produto = new Produto(Long.valueOf(id), nome, descricao, Integer.valueOf(estoque), Double.valueOf(preco));
			
			return produto;
		}else {
			return null;
		}
		
		
	}

	@Override
	public void ShowView(String Resource, String Title) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void LimparCampos() {
		 txtDescricao.setText("");
	     txtEstoque.setText("");
	     txtId.setText("");
	     txtNome.setText("");
	     txtPreco.setText("");
		
	}

	@Override
	public boolean ValidarCampo(String[] dados) {
		
		boolean validado = true;
		for(int i = 0; i < dados.length; i++) {
			if(dados[i].trim() == "" || dados[i] == null || dados.toString().isEmpty()) {
				validado = false;
				break;
			}
		}
		return validado;
	}

	@Override
	public void atualizarTabela() {
		observableListProdutos.clear();
		for (Produto produto : listaDeProdutos) {
			observableListProdutos.add(produto);
		}
		
		tblProduto.getItems().setAll(observableListProdutos);
		tblProduto.getSelectionModel().selectFirst();
	}

	@Override
	public void setarCompos() {
		Produto produto = new Produto();
		produto = tblProduto.getSelectionModel().getSelectedItem();
		
		txtId.setText(Long.toString(produto.getId()));
		txtIdTable.setText(Integer.toString(produto.getIdTabela()));
		txtEstoque.setText(Integer.toString(produto.getEstoque()));
		txtNome.setText(produto.getNome());
		txtDescricao.setText(produto.getDescricao());
		txtPreco.setText(Double.toString(produto.getPreco()));
	}
	
	public void MontarColunas() {
		columnDescricaoTableProduto.setCellValueFactory(new PropertyValueFactory("descricao"));
		columnEstoqueTableProduto.setCellValueFactory(new PropertyValueFactory("estoque"));
		columnIdTableProduto.setCellValueFactory(new PropertyValueFactory("id"));
		columnNomeTableProduto.setCellValueFactory(new PropertyValueFactory("nome"));
		columnPrecoTableProduto.setCellValueFactory(new PropertyValueFactory("preco"));
	}

}
