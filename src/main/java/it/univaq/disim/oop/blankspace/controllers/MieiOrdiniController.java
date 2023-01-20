package it.univaq.disim.oop.blankspace.controllers;

import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.business.BusinessFactory;
import it.univaq.disim.oop.blankspace.business.ServizioOrdine;
import it.univaq.disim.oop.blankspace.domain.GestoreLuogoDiRitrovo;
import it.univaq.disim.oop.blankspace.domain.Ordine;
import it.univaq.disim.oop.blankspace.domain.Persona;
import it.univaq.disim.oop.blankspace.domain.Prodotto;
import it.univaq.disim.oop.blankspace.domain.StatoOrdine;
import it.univaq.disim.oop.blankspace.domain.Utente;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import it.univaq.disim.oop.blankspace.viste.ViewDispacher;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MieiOrdiniController
		implements Initializable, DataInitalizable<WrapperInterVista<Utente, GestoreLuogoDiRitrovo, Ordine, Prodotto>> {

	private Utente utente;
	private GestoreLuogoDiRitrovo glr;
	private ViewDispacher dispatcher = ViewDispacher.getInstance();
	private BusinessFactory factory = BusinessFactory.getImplementation();
	private ServizioOrdine servizioOrdine = factory.getServizioOrdine();

	@FXML
	private Label dataEtichetta; // XD

	@FXML
	private Button esciBottone;

	@FXML
	private Button filtraBottone;

	@FXML
	private Button indietroBottone;

	@FXML
	private TableView<Ordine> ordiniTabella;

	@FXML
	private TableColumn<Ordine, Button> annullaColonna;

	@FXML
	private TableColumn<Ordine, LocalDate> dataColonna;

	@FXML
	private TableColumn<Ordine, Button> dettagliColonna;

	@FXML
	private TableColumn<Ordine, String> indirizzoColonna;

	@FXML
	private TableColumn<Ordine, Button> modificaColonna;

	@FXML
	private TableColumn<Ordine, Integer> nOrdineColonna;

	@FXML
	private TableColumn<Ordine, StatoOrdine> statoColonna;

	@FXML
	private TableColumn<Ordine, String> totaleColonna;

	private TextField barraDiRicerca;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nOrdineColonna.setCellValueFactory(new PropertyValueFactory<Ordine, Integer>("id"));
		statoColonna.setCellValueFactory(new PropertyValueFactory<Ordine, StatoOrdine>("stato"));
		totaleColonna.setCellValueFactory((CellDataFeatures<Ordine, String> param) -> {

			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.FLOOR);
			String totale = df.format(param.getValue().getTotaleSpeso()) + "€";
			return new SimpleObjectProperty<String>(totale);
		});

		indirizzoColonna.setCellValueFactory((CellDataFeatures<Ordine, String> param) -> {
			String residenza = utente.getResidenza();
			return new SimpleObjectProperty<String>(residenza);
		});

		dataColonna.setCellValueFactory(new PropertyValueFactory<Ordine, LocalDate>("dataOrdinazione"));
		modificaColonna.setCellValueFactory((CellDataFeatures<Ordine, Button> param) -> {
			final Button button = new Button("Modifica");
			return new SimpleObjectProperty<Button>(button);
		});
		dettagliColonna.setCellValueFactory((CellDataFeatures<Ordine, Button> param) -> {
			final Button button = new Button("Dettagli");
			return new SimpleObjectProperty<Button>(button);
		});
		annullaColonna.setCellValueFactory((CellDataFeatures<Ordine, Button> param) -> {
			final Button button = new Button("Annulla");
			button.setOnAction(e -> {
				servizioOrdine.cancellaOrdine(param.getValue().getId());
			});
			return new SimpleObjectProperty<Button>(button);
		});
	}

	@Override
	public void initializeData(WrapperInterVista<Utente, GestoreLuogoDiRitrovo, Ordine, Prodotto> wrapper) {
		ObservableList<Ordine> ordiniData;

		if (wrapper.getDato2() != null) { // sono un gestore di luogo di ritrovo
			
			this.glr = wrapper.getDato2();
			ordiniData = FXCollections.observableArrayList(servizioOrdine.getOrdiniFromGLR(glr).values());
		} else { //sono un utente
			this.utente = wrapper.getDato1();
			ordiniData = FXCollections.observableArrayList(servizioOrdine.getOrdiniFromUtente(utente).values());
		}
		
		ordiniTabella.setItems((ObservableList<Ordine>) ordiniData);
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
