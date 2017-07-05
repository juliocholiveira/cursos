package br.com.dao;

import javax.persistence.EntityManager;

public class GenericDAO {

	public EntityManager manager;

	public GenericDAO(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(Object obj){
		manager.getTransaction().begin();
		manager.persist(obj);
		manager.getTransaction().commit();
		System.out.println(obj);
	}
}
