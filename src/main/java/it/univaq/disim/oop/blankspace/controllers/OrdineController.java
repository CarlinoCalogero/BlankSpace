package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.domain.Categoria;
import it.univaq.disim.oop.blankspace.domain.Negozio;
import it.univaq.disim.oop.blankspace.domain.Prodotto;
import it.univaq.disim.oop.blankspace.domain.ProdottoConQuantità;
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
	private TableView<ProdottoConQuantità> ordineTabella;
	
	@FXML
	private TableColumn<ProdottoConQuantità,String> prodottoColonna;
	
	@FXML
	private TableColumn<ProdottoConQuantità,Categoria> categoriaColonna;
	
	@FXML
	private TableColumn<ProdottoConQuantità, Button> eliminaColonna;
	
	@FXML
	private TableColumn<ProdottoConQuantità,Negozio> negozioColonna;
	
	@FXML
	private TableColumn<ProdottoConQuantità, Button> pdfColonna;
	
	@FXML
	private TableColumn<ProdottoConQuantità, String> quantitàColonna;
	
	@FXML
	private TableColumn<ProdottoConQuantità, String> richiestaColonna;
	
	@FXML
	private TableView<Prodotto> catalogoTabella;
	
	@FXML
	private TableColumn<Prodotto, String> prodottoCatalogoColonna;
	
	@FXML
	private TableColumn<Prodotto, Negozio> negozioCatalogoColonna;
	
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
		
		prodottoColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantità, String> param) -> {
			if(param.getValue().getProdotto() instanceof ProdottoRichiesto) {
				ProdottoRichiesto pr = (ProdottoRichiesto) param.getValue().getProdotto();
				return new SimpleObjectProperty<String>("Si");
			} else return new SimpleObjectProperty<String>("No");
		});
		quantitàColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantità, String> param) -> {
			return new SimpleObjectProperty<String>(param.getValue().getQuantità());
		});
		negozioColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantità, Negozio> param) -> {
			return new SimpleObjectProperty<Negozio>(param.getValue().getProdotto().getNegozio());
		});
		prodottoColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantità, String> param) -> {
			 return new SimpleObjectProperty<String>(param.getValue().getProdotto().getNome());
		});
		
		pdfColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantità, Button> param) -> {
			final Button button = new Button("Pdf");
			//TODO: settare la funzione del bottone
			//button.setOnAction();
			return new SimpleObjectProperty<Button>(button);
		});
		categoriaColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantità, Categoria> param) -> {
			return new SimpleObjectProperty<Categoria>(param.getValue().getProdotto().getCategoria());
		});
		eliminaColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantità, Button> param) -> {
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
			
			ObservableList<ProdottoConQuantità> prodottiOrdinatiData = FXCollections.observableArrayList(/*TODO: aggiungere il metodo che ti prende i prodotti da un ordine di un utente*/);
			ordineTabella.setItems((ObservableList<ProdottoConQuantità>) prodottiOrdinatiData);
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
