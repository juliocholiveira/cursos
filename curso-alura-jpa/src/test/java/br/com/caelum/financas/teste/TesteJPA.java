package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;


public class TesteJPA {

	public static void main(String[] args) {
		
		Long inicio = System.currentTimeMillis();
		
		Conta conta = new Conta();
		
		conta.setAgencia("3853");
		conta.setBanco("Júlio César");
		conta.setNumero("123456");
		
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("financas-mysql");
		//EntityManager em = emf.createEntityManager();
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		em.persist(conta);
		em.getTransaction().commit();
		
		em.close();
		
		Long fim = System.currentTimeMillis();
		
		System.out.println("Demorou: " + ((fim-inicio)/1000) + " s");
	}
}
