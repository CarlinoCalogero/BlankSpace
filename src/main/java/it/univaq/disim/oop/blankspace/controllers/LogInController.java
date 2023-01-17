package it.univaq.disim.oop.blankspace.controllers;

import it.univaq.disim.oop.blankspace.domain.Persona;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import it.univaq.disim.oop.blankspace.viste.ViewDispacher;

public class LogInController implements DataInitalizable<Persona>{
	private ViewDispacher dispacher = ViewDispacher.getInstance();
	
	public void loadRegistrazione() {
		dispacher.renderVista("Registrazione", null);
	}
}
