package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJPA {

	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setBanco("Banco do Brasil");
		conta.setTitular("Júlio César");

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		em.persist(conta);

		//Conta conta2 = em.find(Conta.class, 6);

		// altera conta
		// conta2.setBanco("Banco do Brasil");

		// em.remove(conta2);

		/*
		 * find() ou getReference() - Retorna um objeto pelo id tornando-o
		 * "managed"
		 * 
		 * ex.: Conta conta = em.find(Conta.class, 1);
		 * 
		 * persist() - Grava um novo objeto (transient) no banco de dados e o
		 * torna "managed"
		 * 
		 * ex.: em.persist(conta);
		 * 
		 * merge() - Muda o estado de um objeto de "detached" para "managed",
		 * permitindo que alterações sejam realizadas no objeto
		 * 
		 * ex.: em.merge(conta);
		 * 
		 * remove() - Remove um objeto do manager da base, deixando-o no estado
		 * "removed"
		 * 
		 * ex.: em.remove(conta);
		 */
		
		/*Conta contaDetached = new Conta();
		contaDetached.setId(2);
		contaDetached.setBanco("Caixa");
		contaDetached.setTitular("Janaina");*/

		// torna a conta detached >>> managed
		//em.merge(contaDetached);

		em.getTransaction().commit();
		em.close();

	}

}
