package br.com.k19.padraocriacao.abstractfactory;

public interface ComunicadorFactoryInterface {
	
	Emissor createEmissor();
	Receptor createReceptor();

}
