package it.univaq.disim.oop.blankspace.domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Ordine implements Comparable<Ordine> {

	// mancano i metodi per aggiungere prodotti nell'ordine ma li inserisco dopo
	private Map<Integer, Prodotto> insiemeProdotti = new HashMap<>();

	private Integer id;
	private LocalDate dataOrdinazione;
	private Double totaleSpeso = 0.0;
	private StatoOrdine stato;
	private Set<ProdottoConQuantità> listProdotti = new HashSet<>();

	public Ordine() {

	}

	public Ordine(Integer id, LocalDate dataOrdinazione, Double totaleSpeso, StatoOrdine stato) {
		this.id = id;
		this.dataOrdinazione = dataOrdinazione;
		this.totaleSpeso = totaleSpeso;
		this.stato = stato;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataOrdinazione() {
		return dataOrdinazione;
	}

	public void setDataOrdinazione(LocalDate dataOrdinazione) {
		this.dataOrdinazione = dataOrdinazione;
	}

	public Double getTotaleSpeso() {
		return totaleSpeso;
	}

	public void setTotaleSpeso(Double totaleSpeso) {
		this.totaleSpeso = totaleSpeso;
	}

	public StatoOrdine getStato() {
		return stato;
	}

	public void setStato(StatoOrdine stato) {
		this.stato = stato;
	}

	public Map<Integer, Prodotto> getInsiemeProdotti() {
		return insiemeProdotti;
	}

	public void setInsiemeProdotti(Map<Integer, Prodotto> insiemeProdotti) {
		this.insiemeProdotti = insiemeProdotti;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null | !(obj instanceof Ordine))
			return false;
		return this.id == ((Ordine) obj).id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public int compareTo(Ordine o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Set<ProdottoConQuantità> getListProdotti() {
		return new HashSet<>(listProdotti);
	}

	public void setListProdotti(Set<ProdottoConQuantità> listProdotti) {
		this.listProdotti = listProdotti;
	}

	public boolean aggiungiProdottoRichiesto(ProdottoConQuantità prodotto) {
		if (listProdotti.contains(prodotto))
			return false;
		listProdotti.add(prodotto);
		return true;
	}
}
