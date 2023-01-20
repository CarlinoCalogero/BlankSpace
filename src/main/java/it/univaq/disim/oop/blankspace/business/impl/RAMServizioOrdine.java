package it.univaq.disim.oop.blankspace.business.impl;

import java.util.HashMap;
import java.util.Map;

import it.univaq.disim.oop.blankspace.business.ServizioOrdine;
import it.univaq.disim.oop.blankspace.domain.GestoreLuogoDiRitrovo;
import it.univaq.disim.oop.blankspace.domain.Ordine;
import it.univaq.disim.oop.blankspace.domain.PacchettoProdotti;
import it.univaq.disim.oop.blankspace.domain.Prodotto;
import it.univaq.disim.oop.blankspace.domain.ProdottoConQuantità;
import it.univaq.disim.oop.blankspace.domain.Utente;

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

	@Override
	public Map<Integer, Ordine> getOrdini() {
		return ordini;
	}

	@Override
	public Map<Integer, Ordine> getOrdiniFromUtente(Utente utente) {
		Map<Integer, Ordine> ordiniUtente = new HashMap<>();

		for (Ordine ordine : utente.getOrdini())
			ordiniUtente.put(ordine.getId(), ordine);

		return ordiniUtente;
	}

	@Override
	public Map<Integer, Ordine> getOrdiniFromGLR(GestoreLuogoDiRitrovo glr) {
		Map<Integer, Ordine> ordiniGlr = new HashMap<>();

		for (Ordine ordine : glr.getOrdini())
			ordiniGlr.put(ordine.getId(), ordine);

		return ordiniGlr;
	}
}
