package br.com.k19.padraocriacao.factorymethod;

public class EmissorEmail implements Emissor{

	public void envia(String mensagem) {
		System.out.println("Enviando por Email a mensagem: ");
		System.out.println(mensagem);
	}
}
