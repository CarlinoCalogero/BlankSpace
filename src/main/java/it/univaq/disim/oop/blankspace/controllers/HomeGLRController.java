package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.domain.GestoreLuogoDiRitrovo;
import it.univaq.disim.oop.blankspace.domain.Utente;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import it.univaq.disim.oop.blankspace.viste.ViewDispacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

public class HomeGLRController implements Initializable, DataInitalizable<GestoreLuogoDiRitrovo>{
	
	private GestoreLuogoDiRitrovo utente;
	private ViewDispacher dispatcher = ViewDispacher.getInstance();

	@FXML
	private ImageView datiPersonali;
	
	@FXML
	private ImageView creaOrdine;
	
	@FXML
	private ImageView visualizzaOrdini;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void initializeData(GestoreLuogoDiRitrovo utente) {
		this.utente = utente;
	}

	@FXML
	private void accediAreaPersonale() {
		System.out.println("Accesso: Area personale");
	}
	
	@FXML
	private void accediAreaOrdini() {
		System.out.println("Accesso: Area ordine");
	}
	
	@FXML
	private void selezionaUtente() {
		dispatcher.renderVista("CercaUtentePerOrdine", this.utente);
	}
	@FXML
	private void logout() {
		dispatcher.renderVista("LogIn", null);
	}
}
