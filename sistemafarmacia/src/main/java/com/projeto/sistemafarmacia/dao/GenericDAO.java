package com.projeto.sistemafarmacia.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import com.projeto.sistemafarmacia.HibernateUtil;
import com.projeto.sistemafarmacia.model.Usuario;

public class GenericDAO <E>{

	public void Salvar(Usuario entidade) {
		
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
	
	
}
