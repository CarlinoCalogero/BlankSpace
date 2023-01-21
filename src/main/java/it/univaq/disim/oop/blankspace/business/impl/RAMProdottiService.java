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
		prodotti.put(id++, new Prodotto("Mozzarella", "Confezione da 3 mozzarelle Santa Lucia", Categoria.LATTICINO, Negozio.CARREFOUR, 2.79));
		prodotti.put(id++, new Prodotto("Salame", "Salame Milano Carrefour", Categoria.SALUME, Negozio.CARREFOUR, 2.00));
		prodotti.put(id++, new Prodotto("Vino Rosso", "Vino Montepulciano DOC Riserva Spinelli, bottiglia da 750ml", Categoria.ALCOLICO, Negozio.CONAD, 5.99));
		prodotti.put(id++, new Prodotto("Fettine di Pollo", "Petto di pollo a fette da 350g", Categoria.CARNE, Negozio.EUROSPIN, 5.70));
		prodotti.put(id++, new Prodotto("Shampo", "Shampo Garnier Ultra Dolce all'olio d'argan e di camelia per capelli secchi", Categoria.CURA_PER_LA_PERSONA, Negozio.LIDL, 3.99));
		prodotti.put(id++, new Prodotto("Insalata", "Insalata in busta da 125g", Categoria.FRUTTA_E_VERDURA, Negozio.COOP, 1.68));
		prodotti.put(id++, new Prodotto("Merluzzo", "Merluzzo Nordico Impanato 2x100g", Categoria.PESCE, Negozio.CARREFOUR, 3.89));
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
