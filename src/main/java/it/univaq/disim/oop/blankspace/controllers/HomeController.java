package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.domain.GestoreLuogoDiRitrovo;
import it.univaq.disim.oop.blankspace.domain.Ordine;
import it.univaq.disim.oop.blankspace.domain.Prodotto;
import it.univaq.disim.oop.blankspace.domain.Utente;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import it.univaq.disim.oop.blankspace.viste.ViewDispacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class HomeController implements Initializable, DataInitalizable<Utente> {

	private Utente utente;
	private ViewDispacher dispatcher = ViewDispacher.getInstance();

	@FXML
	private Button esciBottone;

	@FXML
	private ImageView datiPersonali;

	@FXML
	private ImageView pacchetti;

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
		dispatcher.renderVista("Ordine",
				new WrapperInterVista<Utente, GestoreLuogoDiRitrovo, Ordine, Prodotto>(utente, null, null, null));
	}

	@FXML
	private void accediAreaMieiOrdini() {
		dispatcher.renderVista("MieiOrdini",
				new WrapperInterVista<Utente, GestoreLuogoDiRitrovo, Ordine, Prodotto>(utente, null, null, null));
	}

	@FXML
	private void esci() {
		dispatcher.renderVista("LogIn", null);
	}

	@FXML
	private void accediAreaPacchetti() {
		dispatcher.renderVista("AreaPacchetti", utente);
	}
}
