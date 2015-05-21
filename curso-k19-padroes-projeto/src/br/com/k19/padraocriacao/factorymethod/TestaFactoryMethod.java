package br.com.k19.padraocriacao.factorymethod;

public class TestaFactoryMethod {

	public static void main(String[] args) {

		/*
		 * Objetivo: Encapsular a escolha da classe concreta a ser utilizada na
		 * cria��o de objetos de um determinado tipo.
		 * 
		 * Exemplo pr�tico: Considere um sistema banc�rio que precisa enviar
		 * mensagens aos seus clientes. Por exemplo,ap�s a realiza��o de uma
		 * compra com cart�o de cr�dito, uma mensagem contendo informa��es sobre
		 * a compra pode ser enviada ao cliente. Se esse cliente for uma pessoa
		 * f�sica, poder� optar pelo recebimento da mensagem atrav�s de email ou
		 * SMS. Por outro lado, se for uma pessoa jur�dica, poder� tamb�m
		 * receber a mensagem atrav�s de JMS (Java Message Service).
		 */

		EmissorCreator ec = new EmissorCreator();
		Emissor emissor = ec.create(EmissorEnum.SMS);
		emissor.envia("Conta est� utilizando cheque especial");

	}

}
