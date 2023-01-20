package it.univaq.disim.oop.blankspace.domain;

public class ProdottoConQuantità implements Comparable<ProdottoConQuantità>{

	private Integer id;
	private Prodotto prodotto;
	private String quantità;

	public ProdottoConQuantità(Prodotto prodotto, String quantità) {
		this.prodotto = prodotto;
		this.quantità = quantità;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public String getQuantità() {
		return quantità;
	}

	public void setQuantità(String quantità) {
		this.quantità = quantità;
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
	public int compareTo(ProdottoConQuantità o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
