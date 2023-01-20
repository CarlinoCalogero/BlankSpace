package it.univaq.disim.oop.blankspace.business.impl;

import java.util.HashMap;
import java.util.Map;

import it.univaq.disim.oop.blankspace.business.ServizioOrdine;
import it.univaq.disim.oop.blankspace.domain.Ordine;

public class RAMServizioOrdine implements ServizioOrdine {

	private Map<Integer, Ordine> ordini = new HashMap<>();

	private static int id = 0;

	@Override
	public Ordine creaOrdine(Ordine ordine) {
		if (ordini.containsValue(ordine))
			return null;
		ordine.setId(id);
		ordini.put(id++, ordine);
		return ordine;
	}

	@Override
	public void aggiornaOrdine(Ordine ordine) {
		ordini.put(ordine.getId(), ordine);
	}

	@Override
	public void cancellaOrdine(int ordineId) {
		ordini.remove(ordineId);
	}

}
