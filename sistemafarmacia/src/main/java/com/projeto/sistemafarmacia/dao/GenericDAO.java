package com.projeto.sistemafarmacia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.projeto.sistemafarmacia.HibernateUtil;
import com.projeto.sistemafarmacia.model.Cliente;
import com.projeto.sistemafarmacia.model.Usuario;

public class GenericDAO <E>{
	
	private EntityManager entityManager = null;
	private EntityTransaction transaction = null;
	private static String UsuarioLogin = null;
	
	public GenericDAO() {
		this.entityManager = HibernateUtil.getEntityManager();
	}

	//private EntityManager entityManager = HibernateUtil.getEntityManager();/*ABRINDO CONEXÃO NA INICIALIZAÇÃO DO SISTEMA PARA GANHAR DESEMPENHO NA MANUZEIO*/
	
	public boolean Salvar(E entidade) {
		
		try {
		
		EntityManager entityManager = HibernateUtil.getEntityManager();
		EntityTransaction transition = entityManager.getTransaction();
		
		transition.begin();
		
		entityManager.persist(entidade);
		
		transition.commit();
		
		entityManager.close();
		
		return true;
		
		} catch(Exception e){
			e.printStackTrace();
			return false;
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
		
		this.UsuarioLogin = logado.getUserName();
		
		transition.commit();
		
		entityManager.close();
		
		return logado;
		
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	public void Deletar(E entidade, int id) {
		
		try {
			
			this.entityManager = HibernateUtil.getEntityManager();
			this.transaction = entityManager.getTransaction();
			
			transaction.begin();
			
			
			entityManager.createNativeQuery("delete from "+entidade.getClass().getSimpleName().toLowerCase()+" where ID = "+id+"").executeUpdate();
			
			entityManager.createNativeQuery("delete from contato where Pessoa_fk = "+id+"").executeUpdate();
			
			transaction.commit();
			
			entityManager.close();
			
		}catch(Exception ex) {
			ex.printStackTrace();
			
			try {
				transaction.rollback();
			}catch(Exception e) {
				ex.printStackTrace();
			}
		}
	}
	
	public List<E> buscarUsuario (Class<E> entidade, String busca){
		
		try {
			
			this.entityManager = HibernateUtil.getEntityManager();
			this.transaction = entityManager.getTransaction();
			
			transaction.begin();
			
			System.out.println(entidade.getName());
			
			List<E> lista =  entityManager.createQuery("from "+entidade.getSimpleName()+" where Nome like '"+busca+"%'").getResultList();
			
			transaction.commit();
			
			entityManager.close();
			
			return  lista;
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	public String getUsuarioLogin() {
		return this.UsuarioLogin;
	}
	
	
}
