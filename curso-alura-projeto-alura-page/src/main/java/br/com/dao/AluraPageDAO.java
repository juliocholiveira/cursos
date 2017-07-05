package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.model.Curso;
import br.com.model.Trilha;

public class AluraPageDAO extends GenericDAO {

	public AluraPageDAO(EntityManager manager) {
		super(manager);
	}

	public List<Trilha> getTrilhas() {
		return manager.createQuery(
				"select t from Trilha t where t.capturado = false")
				.getResultList();
	}

	public List<Curso> getCursos() {
		return manager.createQuery(
				"select c from Curso c where c.capturado = false")
				.getResultList();
	}

}
