package com.projeto.sistemafarmacia.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.projeto.sistemafarmacia.Interfaces.InterfaceCRUD;
import com.projeto.sistemafarmacia.dao.DAOProduto;
import com.projeto.sistemafarmacia.model.Produto;
import com.projeto.sistemafarmacia.util.TextFieldFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ProdutoController implements InterfaceCRUD<Produto>, Initializable{

	
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
	    private TableView<?> tblUsuario;

	    @FXML
	    private TextField txtBusca;

	    @FXML
	    private JFXTextField txtDescricao;

	    @FXML
	    private JFXTextField txtEstoque;

	    @FXML
	    private JFXTextField txtId;

	    @FXML
	    private JFXTextField txtNome;

	    @FXML
	    private JFXTextField txtPreco;

	    private DAOProduto daoProduto = new DAOProduto();
	    

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
		// TODO Auto-generated method stub
		
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

}
