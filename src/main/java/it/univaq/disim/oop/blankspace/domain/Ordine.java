package it.univaq.disim.oop.blankspace.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Ordine implements Comparable<Ordine> {

	private Integer id;
	private LocalDate dataOrdinazione;
	private Double totaleSpeso = 0.0;
	private StatoOrdine stato;
	private Set<ProdottoConQuantita> listProdotti = new HashSet<>();
	private Utente utente;

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

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
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

	@Override
	public boolean equals(Object obj) {
		if (obj == null | !(obj instanceof Ordine))
			return false;
		return this.id == ((Ordine) obj).id;
	}

	@Override
	public String toString() {
		return listProdotti.toString();
	}

	@Override
	public int compareTo(Ordine o) {
		return this.id.compareTo(o.id);
	}

	public Set<ProdottoConQuantita> getListProdotti() {
		return new HashSet<>(listProdotti);
	}

	public void setListProdotti(Set<ProdottoConQuantita> listProdotti) {
		this.listProdotti = listProdotti;
	}

	public boolean aggiungiProdottoRichiesto(ProdottoConQuantita prodotto) {
		if (listProdotti.contains(prodotto))
			return false;
		listProdotti.add(prodotto);
		return true;
	}

	public boolean rimuoviProdottoRichiesto(ProdottoConQuantita prodotto) {
		return this.listProdotti.remove(prodotto);
	}
}
