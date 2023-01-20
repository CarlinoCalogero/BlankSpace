package it.univaq.disim.oop.blankspace.business;

import it.univaq.disim.oop.blankspace.domain.ProdottoConQuantità;

public interface ServizioOrdine {

	public boolean creaOrdine();
	
	public boolean aggiungiProdottoConQuantità(ProdottoConQuantità pcq);
}
