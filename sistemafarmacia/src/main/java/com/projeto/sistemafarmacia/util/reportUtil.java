package com.projeto.sistemafarmacia.util;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings({"unchecked","rawtypes", "serial"})
public class reportUtil implements Serializable{
	
	public void imprimiRelatorio(List listaDeDados, InputStream pathRelatorio) throws JRException {
		
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaDeDados);
	
		
		JasperPrint impressoraJasper = JasperFillManager.fillReport(pathRelatorio, new HashedMap(), dataSource);
		
		JasperViewer.viewReport(impressoraJasper,false);
		
		
	}

}
