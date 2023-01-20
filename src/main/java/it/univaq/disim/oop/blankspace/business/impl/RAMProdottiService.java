package it.univaq.disim.oop.blankspace.business.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.univaq.disim.oop.blankspace.business.ProdottiService;
import it.univaq.disim.oop.blankspace.domain.Categoria;
import it.univaq.disim.oop.blankspace.domain.Negozio;
import it.univaq.disim.oop.blankspace.domain.Prodotto;

public class RAMProdottiService implements ProdottiService {

	private Map<Integer, Prodotto> prodotti = new HashMap<>();

	private static int id = 0;

	public RAMProdottiService() {
		prodotti.put(id++, new Prodotto("mozzarella", "niente", Categoria.LATTICINO, Negozio.COOP, 2.35));
	}

	@Override
	public boolean aggiungiProdotto(Prodotto prodotto) {
		if (prodotti.containsValue(prodotto))
			return false;
		prodotti.put(id++, prodotto);
		return true;
	}

	@Override
	public boolean rimuoviProdotto(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Prodotto> getAllProdotti() {
		return new HashSet<>(prodotti.values());
	}

}
