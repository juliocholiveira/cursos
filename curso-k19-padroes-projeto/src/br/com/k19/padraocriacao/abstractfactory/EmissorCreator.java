package br.com.k19.padraocriacao.abstractfactory;

public class EmissorCreator {
	
	public Emissor create(Bandeira bandeira){
		if (bandeira == Bandeira.VISA){
			return new EmissorVisa();
		} else if (bandeira == Bandeira.MASTERCARD){
			return new EmissorMastercard();
		} else {
			throw new IllegalArgumentException("Bandeira inválida");
		}
	}
}
