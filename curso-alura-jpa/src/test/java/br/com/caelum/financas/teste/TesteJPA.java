package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJPA {

	public static void main(String[] args) {
		
		Conta conta = new Conta();
		conta.setBanco("Banco do Brasil");
		conta.setTitular("Júlio César 2");
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		//em.persist(conta);
		
		Conta conta2 = em.find(Conta.class, 6);
		
		// altera conta
		//conta2.setBanco("Banco do Brasil");
		
		//em.remove(conta2);
		
		Conta contaDetached = new Conta();
		contaDetached.setId(2);
		contaDetached.setBanco("Caixa");
		contaDetached.setTitular("Janaina");
		
		// torna a conta detached >>> managed
		em.merge(contaDetached);
		
		em.getTransaction().commit();
		em.close();

	}

}
