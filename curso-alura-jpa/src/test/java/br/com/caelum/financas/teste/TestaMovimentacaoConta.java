package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TestaMovimentacaoConta {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		/*Conta conta = em.find(Conta.class, 1);
		
		System.out.println("Total de movimentações: " + conta.getMovimentacoes().size());*/
		
		/*Query query = em.createQuery("from Conta c join fetch c.movimentacoes");
		
		List<Conta> contas = query.getResultList();
		
		for (Conta conta2 : contas) {
			System.out.println("Total de movimentações: " + conta2.getMovimentacoes().size());
		}*/
		
		Conta c = new Conta();
		c = em.find(Conta.class, 2);
		
		Query query = em.createQuery("select m from Movimentacao m where m.conta = :pConta");
		query.setParameter("pConta", c);
		
		List<Movimentacao> lista = query.getResultList();
		
		for (Movimentacao movimentacao : lista) {
			System.out.println(movimentacao.getDescricao());
		}
		
		System.out.println("Fim");
				
		
	}

}
