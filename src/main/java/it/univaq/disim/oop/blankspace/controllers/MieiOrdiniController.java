package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.domain.Ordine;
import it.univaq.disim.oop.blankspace.domain.StatoOrdine;
import it.univaq.disim.oop.blankspace.domain.Utente;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import it.univaq.disim.oop.blankspace.viste.ViewDispacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MieiOrdiniController implements Initializable, DataInitalizable<Utente>{
	
	private Utente utente;
	private ViewDispacher dispatcher = ViewDispacher.getInstance();
	
	@FXML
	private Label dataEtichetta; //XD
	
	@FXML
	private Button esciBottone;
	
	@FXML
	private Button filtraBottone;
	
	@FXML
	private Button indietroBottone;
	
	@FXML
	private TableView<Ordine> ordiniTabella;
	
	@FXML
	private TableColumn<Ordine,Button> annullaColonna;
	
	@FXML
	private TableColumn<Ordine,Button> dataColonna;
	
	@FXML
	private TableColumn<Ordine,Button> dettagliColonna;
	
	@FXML
	private TableColumn<Ordine,String> indirizzoColonna;
	
	@FXML
	private TableColumn<Ordine,Button> modificaColonna;
	
	@FXML
	private TableColumn<Ordine,Integer> nOrdineColonna;
	
	@FXML
	private TableColumn<Ordine,StatoOrdine> statoColonna;
	
	@FXML
	private TableColumn<Ordine,String> totaleColonna;
	
	private TextField barraDiRicerca;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@Override
	public void initializeData(Utente utente) {
		this.utente = utente;
	}
	
	@FXML
	private void cerca() {
		System.out.println("Sto cercando: " + barraDiRicerca.getText());
	}
	
	@FXML
	private void esci() {
		System.out.println("Facendo il logout...");
	}
	
	@FXML
	private void filtra() {
		System.out.println("Filtrando...");
	}
	
	@FXML
	private void indietro() {
		dispatcher.renderVista("Home", utente);
	}

}
