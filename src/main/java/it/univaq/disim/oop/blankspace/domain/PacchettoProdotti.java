package it.univaq.disim.oop.blankspace.domain;

import java.util.HashSet;
import java.util.Set;

public class PacchettoProdotti implements Comparable<PacchettoProdotti> {

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
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public int compareTo(PacchettoProdotti o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
