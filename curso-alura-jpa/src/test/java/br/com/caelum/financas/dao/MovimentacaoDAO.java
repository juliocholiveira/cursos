package br.com.caelum.financas.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;

public class MovimentacaoDAO {
	
	private EntityManager manager;

	public MovimentacaoDAO(EntityManager manager) {
		this.manager = manager;
	}
	
	public Double mediaDaContaPeloTipo(Conta conta, TipoMovimentacao tipo){
		
		String qryStr = "select avg(m.valor) from Movimentacao m where m.conta = :pConta and m.tipoMovimentacao = :pTipoMovimentacao";
		
		TypedQuery<Double> typedQueryDouble = manager.createQuery(qryStr, Double.class);
		typedQueryDouble.setParameter("pConta", conta);
		typedQueryDouble.setParameter("pTipoMovimentacao", tipo);
		
		return typedQueryDouble.getSingleResult();
		
	}

}
