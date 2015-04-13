package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteEstadosJPA {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		/*
		 * find() ou getReference() - Retorna um objeto pelo id tornando-o "managed"
		 * 
		 * 		ex.: Conta conta = em.find(Conta.class, 1);
		 * 
		 * persist() - Grava um novo objeto (transient) no banco de dados e o torna "managed"
		 * 
		 * 		ex.: em.persist(conta);
		 * 
		 * merge() - Muda o estado de um objeto de "detached" para "managed", permitindo que alterações
		 * 			 sejam realizadas no objeto
		 * 
		 * 		ex.: em.merge(conta);
		 *  
		 * remove() - Remove um objeto do manager da base, deixando-o no estado "removed"
		 *  
		 * 		ex.: em.remove(conta);
		 * */
		Conta conta = em.find(Conta.class, 1);
		
		System.out.println(conta.getBanco() + " - " + conta.getTitular());
		
		conta.setTitular("Júlio");
		
		em.getTransaction().commit();
		em.close();
	}

}
