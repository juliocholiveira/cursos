package br.com.k19.padraocriacao.abstractfactory;

public class ComunicadorFactory {

	private Bandeira bandeira;
	private EmissorCreator emissorCreator = new EmissorCreator();
	private ReceptorCreator receptorCreator = new ReceptorCreator();
	
	
	public ComunicadorFactory(Bandeira bandeira){
		this.bandeira = bandeira;
	}

	public Emissor createEmissor() {
		return emissorCreator.create(bandeira);
	}

	public Receptor createReceptor() {
		return receptorCreator.create(bandeira);
	}

}
