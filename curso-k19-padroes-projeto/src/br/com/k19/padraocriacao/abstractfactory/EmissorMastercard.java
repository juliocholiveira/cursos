package br.com.k19.padraocriacao.abstractfactory;

public class EmissorMastercard implements Emissor {

	public void envia(String mensagem) {
		System.out.println("Enviando mensagem a Martercard: ");
		System.out.println(mensagem);
	}

}
