package br.com.k19.padraocriacao.abstractfactory;

public class EmissorVisa implements Emissor {

	public void envia(String mensagem) {
		System.out.println("Enviando mensagem a Visa: ");
		System.out.println(mensagem);
	}
}
