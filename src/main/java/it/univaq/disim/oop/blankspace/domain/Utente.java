package it.univaq.disim.oop.blankspace.domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Utente extends Persona {
	private String residenza;
	private ArrayList<PacchettoProdotti> pacchettiProdotti;

	public Utente(String nome, String cognome, LocalDate dataNascita, String email, String telefono, String password,
			String residenza) {
		super(nome, cognome, dataNascita, email, telefono, password);
		this.residenza = residenza;
		pacchettiProdotti = new ArrayList<PacchettoProdotti>();
	}

	public void addNewPacchetto(PacchettoProdotti p) {
		this.pacchettiProdotti.add(p);
	}

	public boolean removePacchettoProdotti(PacchettoProdotti p) {
		return this.pacchettiProdotti.remove(p);
	}

	@Override
	public String toString() {
		return super.toString() + "," + residenza;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Utente))
			return false;
		Utente p = (Utente) obj;
		return this.cognome.equalsIgnoreCase(p.cognome) && this.nome.equalsIgnoreCase(p.nome)
				&& this.dataNascita.equals(p.dataNascita) && this.email.equalsIgnoreCase(p.email)
				&& this.telefono.equals(p.telefono) && this.password.equals(p.password)
				&& this.residenza.equalsIgnoreCase(p.residenza);
	}
}
