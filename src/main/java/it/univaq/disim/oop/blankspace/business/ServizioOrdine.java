package it.univaq.disim.oop.blankspace.business;

import java.util.Map;

import it.univaq.disim.oop.blankspace.domain.GestoreLuogoDiRitrovo;
import it.univaq.disim.oop.blankspace.domain.Ordine;
import it.univaq.disim.oop.blankspace.domain.PacchettoProdotti;
import it.univaq.disim.oop.blankspace.domain.Utente;

public interface ServizioOrdine {

	public Ordine creaOrdine(Ordine ordine);

	public void cancellaOrdine(int ordineId);

	public void aggiornaOrdine(Ordine ordine);
	
	public void creaOrdineFromPacchetto(Ordine ordine, PacchettoProdotti pacchetto);
	
	public Map<Integer,Ordine> getOrdiniFromUtente(Utente utente);
	
	public Map<Integer,Ordine> getOrdiniFromGLR(GestoreLuogoDiRitrovo glr);
	
	public Map<Integer,Ordine> getOrdini();

}
