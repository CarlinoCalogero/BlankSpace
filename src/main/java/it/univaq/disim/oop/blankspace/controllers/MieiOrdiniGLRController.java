package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.business.BusinessFactory;
import it.univaq.disim.oop.blankspace.business.ServizioOrdine;
import it.univaq.disim.oop.blankspace.business.ServizioUtente;
import it.univaq.disim.oop.blankspace.domain.GestoreLuogoDiRitrovo;
import it.univaq.disim.oop.blankspace.domain.Ordine;
import it.univaq.disim.oop.blankspace.domain.Prodotto;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
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
	private TableColumn<Ordine, Integer> nOrdineColonna;

	private TextField barraDiRicerca;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nOrdineColonna.setCellValueFactory(new PropertyValueFactory<Ordine, Integer>("id"));

		utenteColonna.setCellValueFactory((CellDataFeatures<Ordine, String> param) -> {

			for (Utente utente : servizioUtenti.getAllUtenti().values()) {
				if (glr.getOrdini().containsAll(utente.getOrdini()))
					return new SimpleObjectProperty<String>(utente.getEmail());
			}

			return null;
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