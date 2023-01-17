package it.univaq.disim.oop.blankspace.domain;

import java.time.LocalDate;

public class Utente extends Persona {
	private String residenza;

	public Utente(String nome, String cognome, LocalDate dataNascita, String email, String telefono, String password,
			String residenza) {
		super(nome, cognome, dataNascita, email, telefono, password);
		this.residenza = residenza;
	}
	
	@Override
	public String toString() {
		return super.toString()+","+residenza;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Utente)) 
			return false;
		Utente u = (Utente)obj;
		return super.equals(u) && this.residenza.equalsIgnoreCase(u.residenza);
	}
}
