package br.com.caelum.financas.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Conta {

	@Id
	@GeneratedValue
	private Integer id;
	private String titular;
	private String banco;
	private String agencia;
	private String numero;
	
	/*
	 *  fetch=FetchType.LAZY - é o tipo de relacionamento padrão ele executa N+1 consultas,
	 *                         nesse caso podemos definir na jpql o relacionamento eager.
	 *                         Ex.: from Conta c join fetch c.movimentacoes
	 *                       
	 *  fetch=FetchType.EAGER - executa apenas uma consulta retornando todos os objetos 
	 *                          vinculados ao objeto
	 * */
	@OneToMany(mappedBy="conta", fetch=FetchType.LAZY)
	private List<Movimentacao> movimentacoes;
	
	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
