package br.com.k19.padraocriacao.abstractfactory;

public class ReceptorCreator {

	public Receptor create(Bandeira bandeira){
		if (bandeira == Bandeira.VISA){
			return new ReceptorVisa();
		} else if (bandeira == Bandeira.MASTERCARD){
			return new ReceptorMastercard();
		} else {
			throw new IllegalArgumentException("Bandeira inválida");
		}
	}
}
