package it.univaq.disim.oop.blankspace.domain;

import java.io.File;

public class ProdottoRichiesto extends Prodotto {
	
	private File allegato;
	private Boolean medicinale;

	public ProdottoRichiesto(Integer id, String nome, String descrizione, Categoria categoria, Negozio negozio,
			Double prezzo, File allegato, Boolean medicinale) {
		super(id, nome, descrizione, categoria, negozio, prezzo);
		this.allegato = allegato;
		this.medicinale = medicinale;
	}

	public File getAllegato() {
		return allegato;
	}

	public void setAllegato(File allegato) {
		this.allegato = allegato;
	}

	public Boolean isMedicinale() {
		return medicinale;
	}

	//Trovare un titolo migliore per il metodo
	public void setMedicinale(Boolean medicinale) {
		this.medicinale = medicinale;
	}

	
}
