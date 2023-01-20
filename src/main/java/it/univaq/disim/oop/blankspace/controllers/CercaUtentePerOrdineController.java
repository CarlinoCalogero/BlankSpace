package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.business.BusinessFactory;
import it.univaq.disim.oop.blankspace.business.ServizioUtente;
import it.univaq.disim.oop.blankspace.domain.GestoreLuogoDiRitrovo;
import it.univaq.disim.oop.blankspace.domain.Utente;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import it.univaq.disim.oop.blankspace.viste.ViewDispacher;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CercaUtentePerOrdineController implements Initializable, DataInitalizable<GestoreLuogoDiRitrovo> {

	private GestoreLuogoDiRitrovo utente;
	private ServizioUtente servizioUtente = BusinessFactory.getImplementation().getServizioUtente();
	private ViewDispacher dispatcher = ViewDispacher.getInstance();

	@FXML
	private Label dataEtichetta; // XD

	@FXML
	private Button esciBottone;

	@FXML
	private Button filtraBottone;

	@FXML
	private Button indietroBottone;

	@FXML
	private TableView<Utente> utentiTabella;
	@FXML
	private TableColumn<Utente, Button> seleziona;
	@FXML
	private TableColumn<Utente, String> nomeColonna;
	@FXML
	private TableColumn<Utente, String> cognomeColonna;
	@FXML
	private TableColumn<Utente, String> emailColonna;
	@FXML
	private TableColumn<Utente, String> cellulareColonna;
	@FXML
	private TableColumn<Utente, LocalDate> dataColonna;

	private TextField barraDiRicerca;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// seleziona.cellFactoryProperty().setValue(new PropertyValueFactory<Utente,
		// T>(null));
		nomeColonna.setCellValueFactory(new PropertyValueFactory<Utente, String>("nome"));
		cognomeColonna.setCellValueFactory(new PropertyValueFactory<Utente, String>("cognome"));
		emailColonna.setCellValueFactory(new PropertyValueFactory<Utente, String>("email"));
		cellulareColonna.setCellValueFactory(new PropertyValueFactory<Utente, String>("telefono"));
		dataColonna.setCellValueFactory(new PropertyValueFactory<Utente, LocalDate>("dataNascita"));
		seleziona.setCellValueFactory((CellDataFeatures<Utente, Button> param) -> {
			final Button dettagliButton = new Button("Seleziona");
			dettagliButton.setOnAction((ActionEvent e) -> {
				try {
					dispatcher.renderVista("Home", param.getValue());
					System.out.println("Selezionato: " + param.getValue().toString());
				} catch (Exception e1) {
					e1.printStackTrace();
					System.exit(0);
				}
			});
			return new SimpleObjectProperty<Button>(dettagliButton);
		});
		ObservableList<Utente> utenti = FXCollections.observableArrayList(servizioUtente.getAllUtenti().values());
		this.utentiTabella.setItems(utenti);
	}

	@Override
	public void initializeData(GestoreLuogoDiRitrovo utente) {
		this.utente = utente;
	}

	@FXML
	private void cerca() {
		System.out.println("Sto cercando: " + barraDiRicerca.getText());
	}

	@FXML
	private void esci() {
		System.out.println("Facendo il logout...");
		dispatcher.renderVista("LogIn", null);
	}

	@FXML
	private void filtra() {
		System.out.println("Filtrando...");
	}

	@FXML
	private void indietro() {
		dispatcher.renderVista("HomeGLR", utente);
	}

}
