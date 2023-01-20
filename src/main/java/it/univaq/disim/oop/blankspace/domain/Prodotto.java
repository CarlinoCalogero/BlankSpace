package it.univaq.disim.oop.blankspace.domain;

public class Prodotto implements Comparable<Prodotto> {

	private Integer id;
	private String nome;
	private String descrizione;
	private Categoria categoria;
	private Negozio negozio;
	private Double prezzo = 0.0;

	public Prodotto() {

	}

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

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Prodotto))
			return false;
		Prodotto prod = (Prodotto) obj;
		return this.id == prod.id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public int compareTo(Prodotto o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
