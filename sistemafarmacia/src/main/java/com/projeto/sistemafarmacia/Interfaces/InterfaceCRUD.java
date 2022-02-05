package com.projeto.sistemafarmacia.Interfaces;

import javafx.scene.image.Image;

public interface InterfaceCRUD <E> {

	public E ObterModelo();
	
	public void ShowView(String Resource, String Title, String msg);
	
	public void LimparCampos();
	
	
	//public void CloseView(String Resource);
	
}
