package com.projeto.sistemafarmacia.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class FormatCadastrarExibir {

	
	public String cpfToExbir(Long cpf) throws ParseException {
		String newCpf = cpf.toString();
		
		if(newCpf.length() < 11) {
			while(newCpf.length() < 11) {
				newCpf = "0"+newCpf;
			}
		}
		
		MaskFormatter formatter = new MaskFormatter();
		formatter.setMask("###.###.###-##");
		formatter.setValueContainsLiteralCharacters(false);
		
		return formatter.valueToString(newCpf);
		
	}
	
	public Long cpfToCadastrar(String cpf) {
		
		String newCpf = cpf.replace(".", "");
		newCpf = newCpf.replace("-", "");
		
		long longCpf = Long.valueOf(newCpf);
		
		return longCpf;
	} 
	
	public String telefoneToExibir(long telefone) throws ParseException {
		MaskFormatter formatter = new MaskFormatter();
		formatter.setMask("(##)#####-####");
		formatter.setValueContainsLiteralCharacters(false);
		
		return formatter.valueToString(telefone);
	}
	
	
	public long telefoneToCadastrar(String telefone) {
		String  newFone = telefone.replace("(", "");
		newFone = newFone.replace(")", "");
		newFone = newFone.replace("-", "");
		
		long foneLong = Long.valueOf(newFone);
		
		return foneLong;
	}
}
