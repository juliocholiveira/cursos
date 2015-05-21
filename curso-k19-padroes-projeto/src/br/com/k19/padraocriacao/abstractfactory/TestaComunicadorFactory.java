package br.com.k19.padraocriacao.abstractfactory;

public class TestaComunicadorFactory {

	public static void main(String[] args) {

		/*
		 * Objetivo: Encapsular a escolha das classes concretas a serem
		 * utilizadas na cria��o dos objetos de diversas fam�lias.
		 * 
		 * Exemplo pr�tico Estabelecimentos comerciais normalmente oferecem aos
		 * clientes diversas op��es de pagamento. Por exemplo, clientes podem
		 * efetuar pagamentos com dinheiro, cheque, cart�es de cr�dito ou
		 * d�bito, entre outros. Pagamentos com cart�es s�o realizados por meio
		 * de uma m�quina de cart�o, oferecida e instalada por empresas como
		 * Cielo e Redecard. Geralmente, essa m�quina � capaz de lidar com
		 * cart�es de diferentes bandeiras (como Visa eMastercard). Nosso
		 * objetivo � programar essas m�quinas, isto �, desenvolver uma
		 * aplica��o capaz de se comunicar comas diferentes bandeiras e
		 * registrar pagamentos. No momento do pagamento, a m�quina de cart�o
		 * deve enviar as informa��es relativas a transa��o (como valor e senha)
		 * para a bandeira correspondente ao cart�o utilizado. Al�m disso, a
		 * m�quina deve aguardar uma resposta de confirma��o ou recusa do
		 * pagamento.
		 */

		ComunicadorFactory comunicador = new ComunicadorFactory(
				Bandeira.MASTERCARD);

		Emissor emissor = comunicador.createEmissor();
		emissor.envia("Valor=100;Senha=123456");

		Receptor receptor = comunicador.createReceptor();
		System.out.println(receptor.recebe());
	}
}
