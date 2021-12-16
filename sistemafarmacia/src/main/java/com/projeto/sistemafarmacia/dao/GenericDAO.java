package com.projeto.sistemafarmacia.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import com.projeto.sistemafarmacia.HibernateUtil;
import com.projeto.sistemafarmacia.model.Usuario;

public class GenericDAO <E>{

	private EntityManager entityManager = HibernateUtil.getEntityManager();/*ABRINDO CONEXÃO NA INICIALIZAÇÃO DO SISTEMA PARA GANHAR DESEMPENHO NA MANUZEIO*/
	
	public void Salvar(E entidade) {
		
		try {
		
		EntityManager entityManager = HibernateUtil.getEntityManager();
		EntityTransaction transition = entityManager.getTransaction();
		
		transition.begin();
		
		entityManager.persist(entidade);
		
		transition.commit();
		
		entityManager.close();
		
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Usuario Logar(Usuario usuario) {
		
		try {
		EntityManager entityManager = HibernateUtil.getEntityManager();
		EntityTransaction transition = entityManager.getTransaction();
			
		Usuario logado = new Usuario();
		
		Object [] ResultSet = new Object[] {};
		
		transition.begin();
		
		ResultSet = (Object[]) entityManager.createNativeQuery("SELECT UserName, PassWord FROM "+Usuario.class.getSimpleName().toLowerCase()+" WHERE UserName = '"+usuario.getUserName()+"' AND PassWord = '"+usuario.getPassWord()+"' ").getSingleResult();
		
		logado.setUserName((String) ResultSet[0]);
		logado.setPassWord((String) ResultSet[1]);
		
		transition.commit();
		
		entityManager.close();
		
		return logado;
		
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	
}
