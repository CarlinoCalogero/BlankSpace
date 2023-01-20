package it.univaq.disim.oop.blankspace.business.impl;

import java.util.HashMap;
import java.util.Map;

import it.univaq.disim.oop.blankspace.business.ProdottiService;
import it.univaq.disim.oop.blankspace.domain.Prodotto;

public class RAMProdottiService implements ProdottiService {

	private Map<Integer, Prodotto> prodotti = new HashMap<>();

	private static int id = 0;

	@Override
	public boolean aggiungiProdotto(Prodotto prodotto) {
		prodotto.setId(id);
		return prodotti.put(id++, prodotto) == null;
	}

	@Override
	public boolean rimuoviProdotto(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
