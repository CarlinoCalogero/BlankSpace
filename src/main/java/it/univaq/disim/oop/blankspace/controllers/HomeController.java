package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.domain.Utente;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

public class HomeController implements Initializable, DataInitalizable<Utente>{
	
	private Utente utente;

	@FXML
	private ImageView datiPersonali;
	
	@FXML
	private ImageView ordina;
	
	@FXML
	private ImageView iMieiOrdini;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void initializeData(Utente utente) {
		this.utente = utente;
	}

	@FXML
	private void accediAreaPersonale() {
		System.out.println("Accesso: Area personale");
	}
	
	@FXML
	private void accediAreaOrdine() {
		System.out.println("Accesso: Area ordine");
	}
	
	@FXML
	private void accediAreaMieiOrdini() {
		System.out.println("Accesso: Area i miei ordini");
	}

}
