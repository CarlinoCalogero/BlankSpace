package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.business.BusinessFactory;
import it.univaq.disim.oop.blankspace.business.ProdottiService;
import it.univaq.disim.oop.blankspace.business.ServizioOrdine;
import it.univaq.disim.oop.blankspace.domain.Categoria;
import it.univaq.disim.oop.blankspace.domain.GestoreLuogoDiRitrovo;
import it.univaq.disim.oop.blankspace.domain.Negozio;
import it.univaq.disim.oop.blankspace.domain.Ordine;
import it.univaq.disim.oop.blankspace.domain.Prodotto;
import it.univaq.disim.oop.blankspace.domain.ProdottoConQuantita;
import it.univaq.disim.oop.blankspace.domain.ProdottoRichiesto;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrdineController
		implements Initializable, DataInitalizable<WrapperInterVista<Utente, GestoreLuogoDiRitrovo, Ordine, Prodotto>> {

	private ViewDispacher dispatcher = ViewDispacher.getInstance();
	private Utente utente;
	private GestoreLuogoDiRitrovo gestore;
	private Ordine ordine;

	private ServizioOrdine servizioOrdine = BusinessFactory.getImplementation().getServizioOrdine();
	private ProdottiService prodottiService = BusinessFactory.getImplementation().getProdottiService();
	private ObservableList<ProdottoConQuantita> prodottiInOrdine = FXCollections.emptyObservableList();

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
	private Label dataEtichetta, errore;

	@FXML
	private TableView<ProdottoConQuantita> ordineTabella;

	@FXML
	private TableColumn<ProdottoConQuantita, String> prodottoColonna;

	@FXML
	private TableColumn<ProdottoConQuantita, Categoria> categoriaColonna;

	@FXML
	private TableColumn<ProdottoConQuantita, Button> eliminaColonna;

	@FXML
	private TableColumn<ProdottoConQuantita, Negozio> negozioColonna;

	@FXML
	private TableColumn<ProdottoConQuantita, Button> pdfColonna;

	@FXML
	private TableColumn<ProdottoConQuantita, String> quantitàColonna;

	@FXML
	private TableColumn<ProdottoConQuantita, String> richiestaColonna;

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
		catalogoTabella.setRowFactory(tv -> {
			TableRow<Prodotto> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (row.getItem() == null)
					return;
				// Passo alla vista di aggiunta di un nuovo prodotto la persona loggata,
				// l'ordine e il prodotto da aggiungere.
				dispatcher.renderVista("AggiuntaProdottiOrdine",
						new WrapperInterVista<Utente, GestoreLuogoDiRitrovo, Ordine, Prodotto>(this.utente,
								this.gestore, this.ordine, row.getItem()));

			});
			return row;
		});
		prodottoColonna.setStyle("-fx-alignment: CENTER;");
		prodottoColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantita, String> param) -> {
			if (param.getValue().getProdotto() instanceof ProdottoRichiesto) {
				return new SimpleObjectProperty<String>("Si");
			} else
				return new SimpleObjectProperty<String>("No");
		});
		quantitàColonna.setStyle("-fx-alignment: CENTER;");
		quantitàColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantita, String> param) -> {
			return new SimpleObjectProperty<String>(param.getValue().getQuantità());
		});
		negozioColonna.setStyle("-fx-alignment: CENTER;");
		negozioColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantita, Negozio> param) -> {
			return new SimpleObjectProperty<Negozio>(param.getValue().getProdotto().getNegozio());
		});
		prodottoColonna.setStyle("-fx-alignment: CENTER;");
		prodottoColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantita, String> param) -> {
			return new SimpleObjectProperty<String>(param.getValue().getProdotto().getNome());
		});
		pdfColonna.setStyle("-fx-alignment: CENTER;");
		pdfColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantita, Button> param) -> {
			final Button button = new Button("Pdf");
			button.setStyle("-fx-background-color:#bacad7; -fx-background-radius: 15px; -fx-text-fill: #5f6569; -fx-font-weight: bold;");
			// TODO: settare la funzione del bottone
			// button.setOnAction();
			return new SimpleObjectProperty<Button>(button);
		});
		richiestaColonna.setStyle("-fx-alignment: CENTER;");
		richiestaColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantita, String> param) -> {
			if (param.getValue().getProdotto() instanceof ProdottoRichiesto)
				return new SimpleObjectProperty<String>("Si");
			return new SimpleObjectProperty<String>("No");
		});
		categoriaColonna.setStyle("-fx-alignment: CENTER;");
		categoriaColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantita, Categoria> param) -> {
			return new SimpleObjectProperty<Categoria>(param.getValue().getProdotto().getCategoria());
		});
		eliminaColonna.setStyle("-fx-alignment: CENTER;");
		eliminaColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantita, Button> param) -> {
			final Button button = new Button("Elimina");
			button.setStyle("-fx-background-color: red; -fx-background-radius: 15px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
			button.setOnAction((ActionEvent e)->{
				this.ordine.rimuoviProdottoRichiesto(param.getValue());
				this.servizioOrdine.aggiornaOrdine(ordine);
				prodottiInOrdine = FXCollections
						.observableArrayList(this.ordine.getListProdotti());
				this.ordineTabella.setItems(prodottiInOrdine);
			});
			//System.out.println("Ciao"+this.ordine.getListProdotti());
			return new SimpleObjectProperty<Button>(button);
		});

		/* Seconda tabella */
		negozioCatalogoColonna.setStyle("-fx-alignment: CENTER;");
		negozioCatalogoColonna.setCellValueFactory(new PropertyValueFactory<Prodotto, Negozio>("negozio"));
		prodottoCatalogoColonna.setStyle("-fx-alignment: CENTER;");
		prodottoCatalogoColonna.setCellValueFactory(new PropertyValueFactory<Prodotto, String>("nome"));

	}

	@Override
	public void initializeData(WrapperInterVista<Utente, GestoreLuogoDiRitrovo, Ordine, Prodotto> wrapper) {
		this.utente = wrapper.getDato1();
		this.gestore = wrapper.getDato2();
		// let's check if we have just added a ProdottoRichiesto
		if (wrapper.getDato3() == null) {// we are creating a new order
			Ordine ordine = new Ordine();
			ordine.setStato(StatoOrdine.ATTIVO);
			ordine.setDataOrdinazione(LocalDate.now());
			ordine.setUtente(this.utente);
			this.ordine = servizioOrdine.creaOrdine(ordine);
		} else { // we have just added a ProdottoRichiesto
			this.ordine = wrapper.getDato3();
		}

		try {
			ObservableList<Prodotto> prodottiData = FXCollections.observableArrayList(prodottiService.getAllProdotti());
			catalogoTabella.setItems((ObservableList<Prodotto>) prodottiData);

			this.prodottiInOrdine = FXCollections
					.observableArrayList(this.ordine.getListProdotti());
			ordineTabella.setItems(prodottiInOrdine);
		} catch (Exception e) {
			// dispatcher.renderError(e);
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
		servizioOrdine.cancellaOrdine(this.ordine.getId());
		dispatcher.renderVista("LogIn", null);
	}

	@FXML
	private void annullaOrdine(ActionEvent event) {
		servizioOrdine.cancellaOrdine(this.ordine.getId());
		if (this.gestore != null) {
			dispatcher.renderVista("HomeGLR", this.gestore);
		} else
			dispatcher.renderVista("Home", this.utente);
	}

	@FXML
	private void confermaOrdine(ActionEvent event) {
		if(this.prodottiInOrdine.isEmpty()) {
			this.errore.setVisible(true);
			return;
		}
		this.utente.getOrdini().add(this.ordine);
		if (this.gestore != null) {
			this.gestore.getOrdini().add(ordine);
			dispatcher.renderVista("HomeGLR", this.gestore);
		} else
			dispatcher.renderVista("Home", this.utente);
	}

	@FXML
	private void richiediProdotto(ActionEvent event) {
		dispatcher.renderVista("RichiestaProdotto",
				new WrapperInterVista<Utente, GestoreLuogoDiRitrovo, Ordine, Prodotto>(utente, gestore, ordine, null));
	}
}
