package it.univaq.disim.oop.blankspace.controllers;

import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.business.BusinessFactory;
import it.univaq.disim.oop.blankspace.business.ServizioOrdine;
import it.univaq.disim.oop.blankspace.business.ServizioUtente;
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

public class MieiOrdiniGLRController implements Initializable, DataInitalizable<GestoreLuogoDiRitrovo> {

	private BusinessFactory factory = BusinessFactory.getImplementation();
	private ViewDispacher dispatcher = ViewDispacher.getInstance();
	private ServizioOrdine servizioOrdine = factory.getServizioOrdine();
	private ServizioUtente servizioUtenti = factory.getServizioUtente();

	private GestoreLuogoDiRitrovo glr;
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
	private TableColumn<Ordine, String> utenteColonna;
	@FXML
	private TableColumn<Ordine, StatoOrdine> statoColonna;
	@FXML
	private TableColumn<Ordine, String> totaleColonna;
	@FXML
	private TableColumn<Ordine, LocalDate> dataColonna;

	@FXML
	private TableColumn<Ordine, Integer> nOrdineColonna;
	@FXML
	private TableColumn<Ordine, Button> modificaColonna;
	@FXML
	private TableColumn<Ordine, Button> annullaColonna;

	private TextField barraDiRicerca;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nOrdineColonna.setCellValueFactory(new PropertyValueFactory<Ordine, Integer>("id"));

		utenteColonna.setCellValueFactory((CellDataFeatures<Ordine, String> param) -> {
			return new SimpleObjectProperty<String>(
					param.getValue().getUtente().getNome() + " " + param.getValue().getUtente().getCognome());
		});
		dataColonna.setCellValueFactory(new PropertyValueFactory<Ordine, LocalDate>("dataOrdinazione"));
		statoColonna.setCellValueFactory(new PropertyValueFactory<Ordine, StatoOrdine>("stato"));
		totaleColonna.setCellValueFactory((CellDataFeatures<Ordine, String> param) -> {
			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.FLOOR);
			String totale = df.format(param.getValue().getTotaleSpeso()) + "€";
			return new SimpleObjectProperty<String>(totale);
		});
		modificaColonna.setCellValueFactory((CellDataFeatures<Ordine, Button> param) -> {
			final Button button = new Button("Modifica");
			button.setOnAction((ActionEvent e)->{
				dispatcher.renderVista("Ordine", new WrapperInterVista<Utente,GestoreLuogoDiRitrovo, Ordine, Prodotto>(param.getValue().getUtente(),this.glr, param.getValue(), null));
			});
			return new SimpleObjectProperty<Button>(button);
		});
		annullaColonna.setCellValueFactory((CellDataFeatures<Ordine, Button> param) -> {
			final Button button = new Button("Annulla");
			button.setOnAction(e -> {
				param.getValue().getUtente().getOrdini().remove(param.getValue());
				this.glr.getOrdini().remove(param.getValue());
				servizioOrdine.cancellaOrdine(param.getValue().getId());
			});
			return new SimpleObjectProperty<Button>(button);
		});
	}

	@Override
	public void initializeData(GestoreLuogoDiRitrovo glr) {
		this.glr = glr;
		ObservableList<Ordine> ordiniData = FXCollections.observableArrayList(glr.getOrdini());
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
		dispatcher.renderVista("HomeGLR", glr);
	}

}
