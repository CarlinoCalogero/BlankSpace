package it.univaq.disim.oop.blankspace.business;

import java.util.Map;

import it.univaq.disim.oop.blankspace.domain.AddettoCompere;
import it.univaq.disim.oop.blankspace.domain.GestoreLuogoDiRitrovo;
import it.univaq.disim.oop.blankspace.domain.GestoreSistema;
import it.univaq.disim.oop.blankspace.domain.Persona;
import it.univaq.disim.oop.blankspace.domain.Utente;

public interface ServizioUtente {
	public boolean registraUtente(Utente u);
	public boolean registraGestoreLuogoRitrovo(GestoreLuogoDiRitrovo glr);
	public boolean registraAddettoCompere(AddettoCompere addetto);
	public Persona getUtente(String email, String password);
	public GestoreSistema getGestoreSistema(String nickname, String password);
	public Map<Integer, Utente> getAllUtenti();
	public Map<Integer, GestoreLuogoDiRitrovo> getAllGestori();
	public Map<Integer, AddettoCompere> getAllAddetti();
}
