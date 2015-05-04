package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDAO;
import br.com.caelum.financas.util.JPAUtil;

public class TestaMovimentacaoDAO {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		MovimentacaoDAO dao = new MovimentacaoDAO(em);

	}

}
