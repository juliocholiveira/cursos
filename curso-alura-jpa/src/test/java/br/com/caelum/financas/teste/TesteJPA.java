package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.financas.modelo.Conta;

public class TesteJPA {

	public static void main(String[] args) {
		
		Conta conta = new Conta();
		
		conta.setAgencia("3853");
		conta.setBanco("BB");
		conta.setNumero("123456");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("financas-mysql");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(conta);
		em.getTransaction().commit();
		
		em.close();
	}

}
