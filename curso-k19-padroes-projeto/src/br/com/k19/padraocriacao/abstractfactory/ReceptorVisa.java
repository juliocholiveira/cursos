package br.com.k19.padraocriacao.abstractfactory;

public class ReceptorVisa implements Receptor {

	public String recebe() {
		System.out.println("Recebendo a mensagem da Visa...");
		return "Mensagem Visa";
	}
}
