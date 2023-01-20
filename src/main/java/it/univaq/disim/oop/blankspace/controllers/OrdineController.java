package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.domain.Categoria;
import it.univaq.disim.oop.blankspace.domain.Negozio;
import it.univaq.disim.oop.blankspace.domain.Prodotto;
import it.univaq.disim.oop.blankspace.domain.ProdottoRichiesto;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrdineController implements Initializable, DataInitalizable<Utente>{
	
	private ViewDispacher dispatcher = ViewDispacher.getInstance();
	private Utente utente;

	@FXML
	private Button annullaBottono;
	
	@FXML
	private Button confermaBottone;
	
	@FXML
	private Button esciBottone;
	
	@FXML
	private Button filtraBottone;
	
	@FXML
	private Button richiediBottone;
	
	@FXML
	private Label dataEtichetta;
	
	@FXML
	private TableView<Prodotto> ordineTabella;
	
	@FXML
	private TableColumn<Prodotto,String> prodottoColonna;
	
	@FXML
	private TableColumn<Prodotto,Categoria> categoriaColonna;
	
	@FXML
	private TableColumn<Prodotto, Button> eliminaColonna;
	
	@FXML
	private TableColumn<Prodotto, Negozio> negozioCatalogoColonna;
	
	@FXML
	private TableColumn<Prodotto,Negozio> negozioColonna;
	
	@FXML
	private TableColumn<Prodotto, Button> pdfColonna;
	
	@FXML
	private TableColumn<Prodotto, String> prodottoCatalogoColonna;
	
	@FXML
	private TableColumn<Prodotto, String> quantitàColonna;
	
	@FXML
	private TableColumn<Prodotto, String> richiestaColonna;
	
	@FXML
	private TableView<Prodotto> catalogoTabella;
	
	@FXML
	private TextField barraDiRicerca;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		catalogoTabella.setRowFactory( tv -> {
		    TableRow<Prodotto> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		    	if(row.getItem() == null) return; 
		       dispatcher.renderVista(null, null);
		        
		    });
		    return row;
		});
		
		prodottoColonna.setCellValueFactory(new PropertyValueFactory<Prodotto, String>("nome"));
		negozioColonna.setCellValueFactory(new PropertyValueFactory<Prodotto, Negozio>("negozio"));
		richiestaColonna.setCellValueFactory((CellDataFeatures<Prodotto, String> param) -> {
			if(param.getValue() instanceof ProdottoRichiesto) {
				ProdottoRichiesto pr = (ProdottoRichiesto) param.getValue();
				return new SimpleObjectProperty<String>("Si");
			} else return new SimpleObjectProperty<String>("No");
		});
		pdfColonna.setCellValueFactory((CellDataFeatures<Prodotto, Button> param) -> {
			final Button button = new Button("Pdf");
			//TODO: settare la funzione del bottone
			//button.setOnAction();
			return new SimpleObjectProperty<Button>(button);
		});
		categoriaColonna.setCellValueFactory(new PropertyValueFactory<Prodotto, Categoria>("categoria"));
		eliminaColonna.setCellValueFactory((CellDataFeatures<Prodotto, Button> param) -> {
			final Button button = new Button("Elimina");
			//TODO: settare la funzione del bottone
			//button.setOnAction();
			return new SimpleObjectProperty<Button>(button);
		});
		
		/*Seconda tabella*/
		negozioCatalogoColonna.setCellValueFactory(new PropertyValueFactory<Prodotto, Negozio>("negozio"));
		prodottoCatalogoColonna.setCellValueFactory(new PropertyValueFactory<Prodotto, String>("nome"));
		
	}
	
	@Override
	public void initializeData(Utente utente) {
		this.utente = utente;
		
		try {
			ObservableList<Prodotto> prodottiData = FXCollections.observableArrayList(/*TODO: aggiungere il metodo allProdotti*/);
			catalogoTabella.setItems((ObservableList<Prodotto>) prodottiData);
			
			ObservableList<Prodotto> prodottiOrdinatiData = FXCollections.observableArrayList(/*TODO: aggiungere il metodo che ti prende i prodotti da un ordine di un utente*/);
			ordineTabella.setItems((ObservableList<Prodotto>) prodottiOrdinatiData);
		} catch (Exception e) {
			//dispatcher.renderError(e);
		}
	}
	
	@FXML
	private void cerca() {
		System.out.println("Sto cercando...");
	}
	
	@FXML
	private void filtra(ActionEvent event) {
		System.out.println("Sto filtrando...");
	}
	
	@FXML
	private void esci(ActionEvent event) {
		dispatcher.renderVista("LogIn", null);
	}
	
	@FXML
	private void annullaOrdine(ActionEvent event) {
		System.out.println("Annullando l'ordine...");
	}
	
	@FXML
	private void confermaOrdine(ActionEvent event) {
		System.out.println("Confermando ordine");
	}

	@FXML
	private void richiediProdotto(ActionEvent event) {
		dispatcher.renderVista("RichiestaProdotto", utente);
	}
}
