package br.com.k19.padraocriacao.factorymethod;

public class EmissorCreator {
	
	public Emissor create(EmissorEnum tipoEmissor){
		if (tipoEmissor == EmissorEnum.SMS){
			return new EmissorSMS();
		} else if (tipoEmissor == EmissorEnum.EMAIL){
			return new EmissorEmail();
		} else if (tipoEmissor == EmissorEnum.JMS){
			return new EmissorJMS();
		} else {
			throw new IllegalArgumentException("Tipo do emissor não encontrado");
		}
	}

}
