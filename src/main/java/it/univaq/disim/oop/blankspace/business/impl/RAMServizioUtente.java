package it.univaq.disim.oop.blankspace.business.impl;

import java.util.HashSet;

import it.univaq.disim.oop.blankspace.business.ServizioUtente;
import it.univaq.disim.oop.blankspace.domain.Utente;

public class RAMServizioUtente implements ServizioUtente{
	
	private HashSet<Utente> utenti = new HashSet<Utente>();

	@Override
	public boolean registraUtente(Utente u) {
		return utenti.add(u);
	}

	@Override
	public Utente getUtente(String email, String password) {
		for(Utente u: utenti)
			if(u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password))
				return u;
		return null;
	}

}
