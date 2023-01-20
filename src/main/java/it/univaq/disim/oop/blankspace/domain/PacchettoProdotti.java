package it.univaq.disim.oop.blankspace.domain;

import java.util.HashSet;
import java.util.Set;

public class PacchettoProdotti {

	private Set<Prodotto> insiemeProdotti = new HashSet<>();
	private String nome;

	public PacchettoProdotti() {
	}

	public PacchettoProdotti(Integer id, String nome) {
		this.nome = nome;
	}

	public Set<Prodotto> getInsiemeProdotti() {
		return new HashSet<>(this.insiemeProdotti);
	}

	public void setInsiemeProdotti(Set<Prodotto> insiemeProdotti) {
		this.insiemeProdotti = insiemeProdotti;
	}

	public void aggiungiProdottoAlPacchetto(Prodotto prodotto) {
		this.insiemeProdotti.add(prodotto);
	}

	public void rimuoviProdottoAlPacchetto(Prodotto prodotto) {
		this.insiemeProdotti.remove(prodotto);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof PacchettoProdotti)) return false;
		PacchettoProdotti pp = (PacchettoProdotti)obj;
		return this.insiemeProdotti.equals(pp.getInsiemeProdotti()) && this.nome.equals(pp.getNome());
	}

	@Override
	public String toString() {
		return this.getInsiemeProdotti().toString();
	}
}
