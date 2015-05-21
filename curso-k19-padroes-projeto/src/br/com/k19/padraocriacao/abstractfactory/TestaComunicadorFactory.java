package br.com.k19.padraocriacao.abstractfactory;

public class TestaComunicadorFactory {

	public static void main(String[] args) {

		/*
		 * Objetivo: Encapsular a escolha das classes concretas a serem
		 * utilizadas na criação dos objetos de diversas famílias.
		 * 
		 * Exemplo prático Estabelecimentos comerciais normalmente oferecem aos
		 * clientes diversas opções de pagamento. Por exemplo, clientes podem
		 * efetuar pagamentos com dinheiro, cheque, cartões de crédito ou
		 * débito, entre outros. Pagamentos com cartões são realizados por meio
		 * de uma máquina de cartão, oferecida e instalada por empresas como
		 * Cielo e Redecard. Geralmente, essa máquina é capaz de lidar com
		 * cartões de diferentes bandeiras (como Visa eMastercard). Nosso
		 * objetivo é programar essas máquinas, isto é, desenvolver uma
		 * aplicação capaz de se comunicar comas diferentes bandeiras e
		 * registrar pagamentos. No momento do pagamento, a máquina de cartão
		 * deve enviar as informações relativas a transação (como valor e senha)
		 * para a bandeira correspondente ao cartão utilizado. Além disso, a
		 * máquina deve aguardar uma resposta de confirmação ou recusa do
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
