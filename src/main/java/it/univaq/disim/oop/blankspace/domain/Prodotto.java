package it.univaq.disim.oop.blankspace.domain;

public class Prodotto {

	private Integer id;
	private String nome;
	private String descrizione;
	private Categoria categoria;
	private Negozio negozio;
	private Double prezzo;
	
	public Prodotto(Integer id, String nome, String descrizione, Categoria categoria, Negozio negozio, Double prezzo) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.negozio = negozio;
		this.prezzo = prezzo;
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
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Negozio getNegozio() {
		return negozio;
	}
	public void setNegozio(Negozio negozio) {
		this.negozio = negozio;
	}
	public Double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
}
