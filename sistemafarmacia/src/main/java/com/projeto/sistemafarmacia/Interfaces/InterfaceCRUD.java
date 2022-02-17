package com.projeto.sistemafarmacia.Interfaces;

import java.io.IOException;

public interface InterfaceCRUD <E> {

	public E ObterModelo();
	
	public void ShowView(String Resource, String Title) throws IOException;
	
	public void LimparCampos();
	
	
	//public void CloseView(String Resource);
	
}
