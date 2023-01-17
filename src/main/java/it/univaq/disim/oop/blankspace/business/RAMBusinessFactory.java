package it.univaq.disim.oop.blankspace.business;

import it.univaq.disim.oop.blankspace.business.impl.RAMServizioUtente;

public class RAMBusinessFactory extends BusinessFactory{
	private ServizioUtente servizioUtente = new RAMServizioUtente();

	@Override
	public ServizioUtente getServizioUtente() {
		return servizioUtente;
	}
	
	
}
