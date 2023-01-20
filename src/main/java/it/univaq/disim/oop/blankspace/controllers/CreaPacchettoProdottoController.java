package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.business.BusinessFactory;
import it.univaq.disim.oop.blankspace.business.ProdottiService;
import it.univaq.disim.oop.blankspace.domain.Categoria;
import it.univaq.disim.oop.blankspace.domain.Negozio;
import it.univaq.disim.oop.blankspace.domain.PacchettoProdotti;
import it.univaq.disim.oop.blankspace.domain.Persona;
import it.univaq.disim.oop.blankspace.domain.Prodotto;
import it.univaq.disim.oop.blankspace.domain.ProdottoRichiesto;
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

public class CreaPacchettoProdottoController
		implements Initializable, DataInitalizable<WrapperInterVista<Persona, PacchettoProdotti, Object, Object>> {

	private ViewDispacher dispatcher = ViewDispacher.getInstance();
	private Persona persona;
	private PacchettoProdotti pacchettoProdotti;

	private ProdottiService prodottiService = BusinessFactory.getImplementation().getProdottiService();

	@FXML
	private TextField nomePacchettoTextField;

	@FXML
	private Button logoutButton;

	@FXML
	private Button cancelButton;

	@FXML
	private Button creaPacchettoButton;

	@FXML
	private Button filtraBottone;

	@FXML
	private Button richiediBottone;

	@FXML
	private Label dataEtichetta;

	@FXML
	private TableView<Prodotto> prodottiTabella;

	@FXML
	private TableColumn<Prodotto, String> prodottoColonna;

	@FXML
	private TableColumn<Prodotto, Categoria> categoriaColonna;

	@FXML
	private TableColumn<Prodotto, Button> eliminaColonna;

	@FXML
	private TableColumn<Prodotto, Negozio> negozioColonna;

	@FXML
	private TableColumn<Prodotto, Button> pdfColonna;

	@FXML
	private TableColumn<Prodotto, String> richiestaColonna;

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

		creaPacchettoButton.disableProperty().bind(nomePacchettoTextField.textProperty().isEmpty());

		catalogoTabella.setRowFactory(tv -> {
			TableRow<Prodotto> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (row.getItem() == null)
					return;
				this.pacchettoProdotti.aggiungiProdottoAlPacchetto(row.getItem());
				dispatcher.renderVista("CreaPacchettoProdotto",
						new WrapperInterVista<Persona, PacchettoProdotti, Object, Object>(persona,
								this.pacchettoProdotti, null, null));

			});
			return row;
		});

		prodottoColonna.setCellValueFactory((CellDataFeatures<Prodotto, String> param) -> {
			if (param.getValue() instanceof ProdottoRichiesto) {
				ProdottoRichiesto pr = (ProdottoRichiesto) param.getValue();
				return new SimpleObjectProperty<String>("Si");
			} else
				return new SimpleObjectProperty<String>("No");
		});
		negozioColonna.setCellValueFactory((CellDataFeatures<Prodotto, Negozio> param) -> {
			return new SimpleObjectProperty<Negozio>(param.getValue().getNegozio());
		});
		prodottoColonna.setCellValueFactory((CellDataFeatures<Prodotto, String> param) -> {
			return new SimpleObjectProperty<String>(param.getValue().getNome());
		});

		pdfColonna.setCellValueFactory((CellDataFeatures<Prodotto, Button> param) -> {
			final Button button = new Button("Pdf");
			// TODO: settare la funzione del bottone
			// button.setOnAction();
			return new SimpleObjectProperty<Button>(button);
		});
		categoriaColonna.setCellValueFactory((CellDataFeatures<Prodotto, Categoria> param) -> {
			return new SimpleObjectProperty<Categoria>(param.getValue().getCategoria());
		});
		eliminaColonna.setCellValueFactory((CellDataFeatures<Prodotto, Button> param) -> {
			final Button button = new Button("Elimina");
			button.setOnAction((ActionEvent e) -> {
				this.pacchettoProdotti.rimuoviProdottoAlPacchetto(param.getValue());
				dispatcher.renderVista("CreaPacchettoProdotto",
						new WrapperInterVista<Persona, PacchettoProdotti, Object, Object>(persona,
								this.pacchettoProdotti, null, null));
			});

			return new SimpleObjectProperty<Button>(button);
		});

		/* Seconda tabella */
		negozioCatalogoColonna.setCellValueFactory(new PropertyValueFactory<Prodotto, Negozio>("negozio"));
		prodottoCatalogoColonna.setCellValueFactory(new PropertyValueFactory<Prodotto, String>("nome"));

	}

	@Override
	public void initializeData(WrapperInterVista<Persona, PacchettoProdotti, Object, Object> wrapper) {
		this.persona = wrapper.getDato1();

		// check if we have just added a Prodotto
		if (wrapper.getDato2() == null) { // we have not added a Prodotto
			this.pacchettoProdotti = new PacchettoProdotti();
		} else { // we have just addedd a prodotto
			this.pacchettoProdotti = wrapper.getDato2();
		}

		try {
			ObservableList<Prodotto> prodottiData = FXCollections.observableArrayList(prodottiService.getAllProdotti());
			catalogoTabella.setItems((ObservableList<Prodotto>) prodottiData);

			ObservableList<Prodotto> prodottiOrdinatiData = FXCollections
					.observableArrayList(this.pacchettoProdotti.getInsiemeProdotti());
			prodottiTabella.setItems((ObservableList<Prodotto>) prodottiOrdinatiData);
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

	public void logoutAction(ActionEvent event) {
		dispatcher.renderVista("LogIn", null);
	}

	public void creaPacchettoAction() {

	}

	public void cancelAction() {
		dispatcher.renderVista("AreaPacchetti", persona);
	}

}
