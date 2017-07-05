package br.com.caelum.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Carteira {

	@Id
	private CarteiraPK id;
	private String gerente;

	public CarteiraPK getId() {
		return id;
	}

	public void setId(CarteiraPK id) {
		this.id = id;
	}

	public String getGerente() {
		return gerente;
	}

	public void setGerente(String gerente) {
		this.gerente = gerente;
	}

}
