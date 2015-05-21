package br.com.k19.padraocriacao.abstractfactory;

public class ReceptorMastercard implements Receptor{

	public String recebe() {
		System.out.println("Recebendo a mensagem da Mastercard...");
		return "Mensagem Martercard";
	}

}
