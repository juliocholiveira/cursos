package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteContaMovimentacoes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		Conta c = em.find(Conta.class, 1);
		
		for (Movimentacao m : c.getMovimentacoes()) {
			System.out.println(m.getTipoMovimentacao());
		}

	}

}
