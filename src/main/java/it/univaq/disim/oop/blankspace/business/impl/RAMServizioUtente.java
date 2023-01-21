package it.univaq.disim.oop.blankspace.business.impl;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import it.univaq.disim.oop.blankspace.business.ServizioUtente;
import it.univaq.disim.oop.blankspace.domain.AddettoCompere;
import it.univaq.disim.oop.blankspace.domain.GestoreLuogoDiRitrovo;
import it.univaq.disim.oop.blankspace.domain.GestoreSistema;
import it.univaq.disim.oop.blankspace.domain.Persona;
import it.univaq.disim.oop.blankspace.domain.Utente;

public class RAMServizioUtente implements ServizioUtente {
	private HashMap<Integer, GestoreSistema> admins = new HashMap<Integer, GestoreSistema>();
	private HashMap<Integer, Persona> utenti = new HashMap<Integer, Persona>();
	private static int id = 0;
	private static int idAdmins = 0;

	public RAMServizioUtente() {
		GestoreSistema gs = new GestoreSistema("admin", "admin");
		Persona glr1 = new GestoreLuogoDiRitrovo("Calogero", "Carlino", LocalDate.now(), "calogero@carlino.gov","0861997453", "calcarl", "BlastBar", "Piazza Romana");
		Persona luca = new Utente(id, "Luca Francesco", "Macera", LocalDate.of(2002, Month.JULY, 29), "lucafrancesco.macera@student.univaq.it", "3414457881", "lucamf", "Via Monte dei Paschi");
		Persona calogero = new Utente(id, "Calogero", "Carlino", LocalDate.of(2001, Month.SEPTEMBER, 11), "calogero.carlino@student.univaq.it", "3326676810", "calo", "Via Venezia");
		Persona michael = new Utente(id, "Michael", "Piccirilli", LocalDate.of(2002, Month.OCTOBER, 18), "michael.piccirilli@student.univaq.it", "3384921992", "mik", "Via Giovanni di Vincenzo");
		utenti.put(id++, luca);
		utenti.put(id++, calogero);
		utenti.put(id++, michael);
		utenti.put(id++, glr1);
		admins.put(idAdmins++, gs);
	}

	@Override
	public boolean registraUtente(Utente u) {
		if (!this.utenti.containsValue(u)) {
			u.setId(id);
			return this.utenti.put(id++, u) == null;
		}
		return false;
	}

	@Override
	public Persona getUtente(String email, String password) {
		for (Persona u : utenti.values())
			if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password))
				return u;
		return null;
	}

	@Override
	public boolean registraGestoreLuogoRitrovo(GestoreLuogoDiRitrovo glr) {
		if (!this.utenti.containsValue(glr))
			return utenti.put(id++, glr) == null;
		return false;
	}

	@Override
	public boolean registraAddettoCompere(AddettoCompere addetto) {
		if (!this.utenti.containsValue(addetto))
			return utenti.put(id++, addetto) == null;
		return false;
	}

	@Override
	public Map<Integer, Utente> getAllUtenti() {
		HashMap<Integer, Utente> uts = new HashMap<Integer, Utente>();
		for (Map.Entry<Integer, Persona> entries : utenti.entrySet())
			if (entries.getValue() instanceof Utente)
				uts.put(entries.getKey(), (Utente) entries.getValue());
		return uts;
	}

	@Override
	public Map<Integer, GestoreLuogoDiRitrovo> getAllGestori() {
		HashMap<Integer, GestoreLuogoDiRitrovo> uts = new HashMap<Integer, GestoreLuogoDiRitrovo>();
		for (Map.Entry<Integer, Persona> entries : utenti.entrySet())
			if (entries.getValue() instanceof GestoreLuogoDiRitrovo)
				uts.put(entries.getKey(), (GestoreLuogoDiRitrovo) entries.getValue());
		return uts;
	}

	@Override
	public Map<Integer, AddettoCompere> getAllAddetti() {
		HashMap<Integer, AddettoCompere> uts = new HashMap<Integer, AddettoCompere>();
		for (Map.Entry<Integer, Persona> entries : utenti.entrySet())
			if (entries.getValue() instanceof GestoreLuogoDiRitrovo)
				uts.put(entries.getKey(), (AddettoCompere) entries.getValue());
		return uts;
	}

	@Override
	public GestoreSistema getGestoreSistema(String nickname, String password) {
		for (GestoreSistema gs : admins.values())
			if (gs.getNickname().equalsIgnoreCase(nickname) && gs.getPassword().equals(password))
				return gs;
		return null;
	}

	@Override
	public void aggiornaUtente(Utente utente) {
		utenti.put(utente.getId(), utente);
	}

}
