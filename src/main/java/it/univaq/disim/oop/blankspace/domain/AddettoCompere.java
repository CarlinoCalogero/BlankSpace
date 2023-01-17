package it.univaq.disim.oop.blankspace.domain;

import java.time.LocalDate;

public class AddettoCompere extends Persona {
	public AddettoCompere(String nome, String cognome, LocalDate dataNascita, String email, String telefono,
			String password) {
		super(nome, cognome, dataNascita, email, telefono, password);
	}
}
