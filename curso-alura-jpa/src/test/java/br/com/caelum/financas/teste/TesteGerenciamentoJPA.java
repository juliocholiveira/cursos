package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteGerenciamentoJPA {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		Conta conta = em.find(Conta.class, 1);
		
		// Commit antes da alteração
		em.getTransaction().commit();
		
		// Alteração da agência
		conta.setAgencia("3853");
		
		em.getTransaction().begin();
		// Passa o objeto conta para o estado managed
		em.merge(conta);
		em.getTransaction().commit();
		
		em.close();

	}

}
