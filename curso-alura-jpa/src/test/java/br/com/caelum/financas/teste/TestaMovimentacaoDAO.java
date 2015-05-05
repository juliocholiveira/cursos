package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDAO;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TestaMovimentacaoDAO {

	public static void main(String[] args) {
		
		Conta conta = new Conta();
		conta.setId(1);
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		MovimentacaoDAO dao = new MovimentacaoDAO(em);
		
		System.out.println(dao.mediaDaContaPeloTipo(conta, TipoMovimentacao.SAIDA));
		
		//System.out.println(dao.mediaDaContaPeloTipoComNamedQuery(conta, TipoMovimentacao.SAIDA));

		System.out.println(dao.qtdeMovimentacaoPorConta(conta));

	}

}
