package it.univaq.disim.oop.blankspace.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Ordine implements Comparable<Ordine> {

	// mancano i metodi per aggiungere prodotti nell'ordine ma li inserisco dopo
	private Map<Integer, Prodotto> insiemeProdotti = new HashMap<>();

	private Integer id;
	private Date dataOrdinazione;
	private Double totaleSpeso;
	private StatoOrdine stato;

	public Ordine(Integer id, Date dataOrdinazione, Double totaleSpeso, StatoOrdine stato) {
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

	public Date getDataOrdinazione() {
		return dataOrdinazione;
	}

	public void setDataOrdinazione(Date dataOrdinazione) {
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
		// TODO Auto-generated method stub
		return super.equals(obj);
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
}
