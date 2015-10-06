package br.com.casadocodigo.loja.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class GenericDAO<T> {

	@PersistenceContext
	public EntityManager manager;

	public void adiciona(T object) {
		manager.persist(object);
	}

	public void altera(T object) {
		manager.merge(object);
	}

	public void remove(T object) {
		manager.remove(object);
	}

}