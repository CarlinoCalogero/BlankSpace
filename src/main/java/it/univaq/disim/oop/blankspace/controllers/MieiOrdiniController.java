package it.univaq.disim.oop.blankspace.controllers;

import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Set;

import it.univaq.disim.oop.blankspace.business.BusinessFactory;
import it.univaq.disim.oop.blankspace.business.ServizioOrdine;
import it.univaq.disim.oop.blankspace.domain.GestoreLuogoDiRitrovo;
import it.univaq.disim.oop.blankspace.domain.Ordine;
import it.univaq.disim.oop.blankspace.domain.Prodotto;
import it.univaq.disim.oop.blankspace.domain.StatoOrdine;
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

		nOrdineColonna.setStyle("-fx-alignment: CENTER;");
		nOrdineColonna.setCellValueFactory(new PropertyValueFactory<Ordine, Integer>("id"));
		statoColonna.setCellValueFactory(new PropertyValueFactory<Ordine, StatoOrdine>("stato"));
		statoColonna.setStyle("-fx-alignment: CENTER;");
		totaleColonna.setStyle("-fx-alignment: CENTER;");
		totaleColonna.setCellValueFactory((CellDataFeatures<Ordine, String> param) -> {

			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.FLOOR);
			String totale = df.format(param.getValue().getTotaleSpeso()) + "???";
			return new SimpleObjectProperty<String>(totale);
		});
		indirizzoColonna.setStyle("-fx-alignment: CENTER;");
		indirizzoColonna.setCellValueFactory((CellDataFeatures<Ordine, String> param) -> {
			String residenza = utente.getResidenza();
			return new SimpleObjectProperty<String>(residenza);
		});
		dataColonna.setStyle("-fx-alignment: CENTER;");
		dataColonna.setCellValueFactory(new PropertyValueFactory<Ordine, LocalDate>("dataOrdinazione"));
		modificaColonna.setStyle("-fx-alignment: CENTER;");
		modificaColonna.setCellValueFactory((CellDataFeatures<Ordine, Button> param) -> {
			final Button button = new Button("Modifica");
			button.setStyle(
					"-fx-background-color:#bacad7; -fx-background-radius: 15px; -fx-text-fill: #5f6569; -fx-font-weight: bold;");
			button.setOnAction((ActionEvent e) -> {
				dispatcher.renderVista("Ordine", new WrapperInterVista<Utente, GestoreLuogoDiRitrovo, Ordine, Prodotto>(
						this.utente, this.glr, param.getValue(), null));
			});
			return new SimpleObjectProperty<Button>(button);
		});
		annullaColonna.setStyle("-fx-alignment: CENTER;");
		annullaColonna.setCellValueFactory((CellDataFeatures<Ordine, Button> param) -> {
			final Button button = new Button("Annulla");
			button.setStyle(
					"-fx-background-color: red; -fx-background-radius: 15px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
			button.setOnAction(e -> {
				servizioOrdine.cancellaOrdine(param.getValue().getId());
				Set<Ordine> ordini = utente.getOrdini();
				ordini.remove(param.getValue());
				utente.setOrdini(ordini);
				dispatcher.renderVista("Home", utente);
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
		} else { // sono un utente
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
		dispatcher.renderVista("LogIn", null);
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
