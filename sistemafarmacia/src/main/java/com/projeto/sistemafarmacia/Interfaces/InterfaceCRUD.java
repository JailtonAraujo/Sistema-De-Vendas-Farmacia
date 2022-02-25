package com.projeto.sistemafarmacia.Interfaces;

import java.io.IOException;
import java.util.List;

public interface InterfaceCRUD <E> {

	public E ObterModelo();
	
	public void ShowView(String Resource, String Title) throws IOException;
	
	public void LimparCampos();
	
	public boolean ValidarCampo(String dados[]);
	
	public void atualizarTabela();
	
	public void setarCompos();
	
	//public void CloseView(String Resource);
	
}
