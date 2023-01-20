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
import it.univaq.disim.oop.blankspace.domain.ProdottoConQuantità;
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

public class OrdineController implements Initializable, DataInitalizable<WrapperInterVista<Utente,GestoreLuogoDiRitrovo, Ordine,Prodotto>> {

	private ViewDispacher dispatcher = ViewDispacher.getInstance();
	private Utente utente;
	private GestoreLuogoDiRitrovo gestore;
	private Ordine ordine;

	private ServizioOrdine servizioOrdine = BusinessFactory.getImplementation().getServizioOrdine();
	private ProdottiService prodottiService = BusinessFactory.getImplementation().getProdottiService();

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
	private TableColumn<ProdottoConQuantità, String> prodottoColonna;

	@FXML
	private TableColumn<ProdottoConQuantità, Categoria> categoriaColonna;

	@FXML
	private TableColumn<ProdottoConQuantità, Button> eliminaColonna;

	@FXML
	private TableColumn<ProdottoConQuantità, Negozio> negozioColonna;

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
		catalogoTabella.setRowFactory(tv -> {
			TableRow<Prodotto> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (row.getItem() == null)
					return;
				// Passo alla vista di aggiunta di un nuovo prodotto la persona loggata,
				// l'ordine e il prodotto da aggiungere.
				dispatcher.renderVista("AggiuntaProdottiOrdine",
						new WrapperInterVista<Utente, GestoreLuogoDiRitrovo, Ordine, Prodotto>(this.utente, this.gestore,
								this.ordine, row.getItem()));

			});
			return row;
		});

		prodottoColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantità, String> param) -> {
			if (param.getValue().getProdotto() instanceof ProdottoRichiesto) {
				ProdottoRichiesto pr = (ProdottoRichiesto) param.getValue().getProdotto();
				return new SimpleObjectProperty<String>("Si");
			} else
				return new SimpleObjectProperty<String>("No");
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
			// TODO: settare la funzione del bottone
			// button.setOnAction();
			return new SimpleObjectProperty<Button>(button);
		});
		
		richiestaColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantità, String> param) -> {
			if(param.getValue().getProdotto() instanceof ProdottoRichiesto) return new SimpleObjectProperty<String>("Si");
			return new SimpleObjectProperty<String>("No");
		});
		categoriaColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantità, Categoria> param) -> {
			return new SimpleObjectProperty<Categoria>(param.getValue().getProdotto().getCategoria());
		});
		eliminaColonna.setCellValueFactory((CellDataFeatures<ProdottoConQuantità, Button> param) -> {
			final Button button = new Button("Elimina");
			// TODO: settare la funzione del bottone
			// button.setOnAction();
			return new SimpleObjectProperty<Button>(button);
		});

		/* Seconda tabella */
		negozioCatalogoColonna.setCellValueFactory(new PropertyValueFactory<Prodotto, Negozio>("negozio"));
		prodottoCatalogoColonna.setCellValueFactory(new PropertyValueFactory<Prodotto, String>("nome"));

	}

	@Override
	public void initializeData(WrapperInterVista<Utente,GestoreLuogoDiRitrovo,Ordine,Prodotto> wrapper) {
		this.utente = wrapper.getDato1();

		// let's check if we have just added a ProdottoRichiesto
		if (wrapper.getDato3() == null) {// we are creating a new order
			Ordine ordine = new Ordine();
			ordine.setStato(StatoOrdine.ATTIVO);
			ordine.setDataOrdinazione(LocalDate.now());
			this.ordine = servizioOrdine.creaOrdine(ordine);
			if(wrapper.getDato2() != null) { //Sono entrato come gestore del luogo di ritrovo
				wrapper.getDato1().getOrdini().add(ordine); //Aggiungo l'ordine anche al gestore del luogo di ritrovo
			}
			wrapper.getDato1().getOrdini().add(this.ordine);
		} else { // we have just added a ProdottoRichiesto
			this.ordine = wrapper.getDato3();
		}

		try {
			ObservableList<Prodotto> prodottiData = FXCollections.observableArrayList(prodottiService.getAllProdotti());
			catalogoTabella.setItems((ObservableList<Prodotto>) prodottiData);

			ObservableList<ProdottoConQuantità> prodottiOrdinatiData = FXCollections
					.observableArrayList(this.ordine.getListProdotti());
			ordineTabella.setItems((ObservableList<ProdottoConQuantità>) prodottiOrdinatiData);
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
		dispatcher.renderVista("LogIn", null);
	}

	@FXML
	private void annullaOrdine(ActionEvent event) {
		servizioOrdine.cancellaOrdine(this.ordine.getId());
		if(this.gestore != null)
			dispatcher.renderVista("HomeGLR", this.gestore);
		else
			dispatcher.renderVista("Home", this.utente);
	}

	@FXML
	private void confermaOrdine(ActionEvent event) {
		System.out.println("Confermando ordine");
	}

	@FXML
	private void richiediProdotto(ActionEvent event) {
		dispatcher.renderVista("RichiestaProdotto", new WrapperInterVista<Utente,GestoreLuogoDiRitrovo ,Ordine,Prodotto>(utente,gestore,ordine,null));
	}
}
