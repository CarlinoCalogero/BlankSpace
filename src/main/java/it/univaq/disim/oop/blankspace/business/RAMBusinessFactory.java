package it.univaq.disim.oop.blankspace.business;

import it.univaq.disim.oop.blankspace.business.impl.RAMProdottiService;
import it.univaq.disim.oop.blankspace.business.impl.RAMServizioOrdine;
import it.univaq.disim.oop.blankspace.business.impl.RAMServizioUtente;

public class RAMBusinessFactory extends BusinessFactory {
	private ServizioUtente servizioUtente = new RAMServizioUtente();
	private ProdottiService prodottiService = new RAMProdottiService();
	private ServizioOrdine servizioOrdine = new RAMServizioOrdine();

	@Override
	public ServizioUtente getServizioUtente() {
		return servizioUtente;
	}

	@Override
	public ProdottiService getProdottiService() {
		return prodottiService;
	}

	@Override
	public ServizioOrdine getServizioOrdine() {
		return servizioOrdine;
	}

}
