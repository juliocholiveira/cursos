package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsultaFuncoes {

	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		
		Conta conta = em.find(Conta.class, 2);
		
		String qry = "select m from Movimentacao m where m.conta = :pConta and m.tipoMovimentacao = :pTipoMovimentacao";
		
		Query query = em.createQuery(qry);
		query.setParameter("pConta", conta);
		query.setParameter("pTipoMovimentacao", TipoMovimentacao.ENTRADA);
		
		List<Movimentacao> movimentacoes = query.getResultList();
		
		for (Movimentacao m : movimentacoes){
			System.out.println(m.getValor());
		}
		
		System.out.println("--------- Consulta de agregação ----------");
		
		qry = "select sum(m.valor) from Movimentacao m where m.conta = :pConta and m.tipoMovimentacao = :pTipoMovimentacao";
		
		// TypedQuery faz o cast automático
		TypedQuery<BigDecimal> typedQuery = em.createQuery(qry, BigDecimal.class);
		typedQuery.setParameter("pConta", conta);
		typedQuery.setParameter("pTipoMovimentacao", TipoMovimentacao.ENTRADA);
		
		//BigDecimal resultado = (BigDecimal) query.getSingleResult();
		BigDecimal resultado = typedQuery.getSingleResult();
		
		System.out.println("Valor total: " + resultado);
		
		qry = "select avg(m.valor) from Movimentacao m where m.conta = :pConta and m.tipoMovimentacao = :pTipoMovimentacao";
		
		// TypedQuery faz o cast automático
		TypedQuery<Double> typedQueryDouble = em.createQuery(qry, Double.class);
		typedQueryDouble.setParameter("pConta", conta);
		typedQueryDouble.setParameter("pTipoMovimentacao", TipoMovimentacao.ENTRADA);
		
		//BigDecimal resultado = (BigDecimal) query.getSingleResult();
		Double resultadoDouble = typedQueryDouble.getSingleResult();
		
		System.out.println("Média do Valor total: " + resultadoDouble);
		
	}
}
