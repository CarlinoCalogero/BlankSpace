package it.univaq.disim.oop.blankspace.business;

import it.univaq.disim.oop.blankspace.domain.Ordine;

public interface ServizioOrdine {

	public Ordine creaOrdine(Ordine ordine);

	public void cancellaOrdine(int ordineId);

	public void aggiornaOrdine(Ordine ordine);

}
