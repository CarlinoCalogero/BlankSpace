package it.univaq.disim.oop.blankspace.business;

import it.univaq.disim.oop.blankspace.domain.Utente;

public interface ServizioUtente {
	public boolean registraUtente(Utente u);
	public Utente getUtente(String email, String password);
}
