package it.univaq.disim.oop.blankspace.domain;

import java.util.Map;
import java.util.TreeMap;

public class PacchettoProdotti {

	private Map<Integer, Prodotto> insiemeProdotti = new TreeMap<>();

	private Integer id;
	private String nome;

	public PacchettoProdotti(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Map<Integer, Prodotto> getInsiemeProdotti() {
		return insiemeProdotti;
	}

	public void setInsiemeProdotti(Map<Integer, Prodotto> insiemeProdotti) {
		this.insiemeProdotti = insiemeProdotti;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
