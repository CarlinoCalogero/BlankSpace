package it.univaq.disim.oop.blankspace.business.impl;

import java.time.LocalDate;
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
		Persona ut1 = new Utente("Mikael", "Pikkirilli", LocalDate.now(), "mikael@pik.it", "0000000000", "mik", "void");
		Persona ut2 = new Utente("Amico Immaginario", "Mikki", LocalDate.now(), "mikki@pik.it", "0000000000", "mikki",
				"void");
		Persona glr1 = new GestoreLuogoDiRitrovo("Calogero", "Carlino", LocalDate.now(), "calogero@carlino.gov",
				"1111111111", "calcarl", "BlastBar", "void");
		Persona ut3 = new Utente("a", "a", LocalDate.now(), "a", "0000000000", "a", "void");
		utenti.put(id++, ut1);
		utenti.put(id++, ut2);
		utenti.put(id++, ut3);
		utenti.put(id++, glr1);
		admins.put(idAdmins++, gs);
	}

	@Override
	public boolean registraUtente(Utente u) {
		if (!this.utenti.containsValue(u))
			return this.utenti.put(id++, u) == null;
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

}
