package it.univaq.disim.oop.blankspace.business;

import it.univaq.disim.oop.blankspace.domain.Prodotto;

public interface ProdottiService {
	
	public boolean aggiungiProdotto(Prodotto prodotto);
	public boolean rimuoviProdotto(Integer id);

}
