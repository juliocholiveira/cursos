package br.com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String link;
	private boolean capturado;
	@ManyToOne
	private Trilha trilha;
	
	public Curso() {
	}

	public Curso(String nome, String link, Trilha trilha) {
		super();
		this.nome = nome;
		this.link = link;
		this.trilha = trilha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isCapturado() {
		return capturado;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}

	public Trilha getTrilha() {
		return trilha;
	}

	public void setTrilha(Trilha trilha) {
		this.trilha = trilha;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nome=" + nome + ", link=" + link
				+ ", capturado=" + capturado + ", trilha=" + trilha + "]";
	}

}
