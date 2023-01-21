package it.univaq.disim.oop.blankspace.domain;

public class ProdottoConQuantita {

	private Prodotto prodotto;
	private String quantità;

	public ProdottoConQuantita(Prodotto prodotto, String quantità) {
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
		if (obj == null || !(obj instanceof ProdottoConQuantita))
			return false;
		ProdottoConQuantita pr = (ProdottoConQuantita) obj;
		return this.prodotto.equals(pr.prodotto) && this.quantità.equalsIgnoreCase(pr.quantità);
	}

	@Override
	public String toString() {
		return prodotto.toString() + ":" + quantità;
	}
}
