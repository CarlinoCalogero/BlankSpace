package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.business.BusinessFactory;
import it.univaq.disim.oop.blankspace.business.ServizioOrdine;
import it.univaq.disim.oop.blankspace.business.ServizioUtente;
import it.univaq.disim.oop.blankspace.domain.GestoreLuogoDiRitrovo;
import it.univaq.disim.oop.blankspace.domain.Ordine;
import it.univaq.disim.oop.blankspace.domain.PacchettoProdotti;
import it.univaq.disim.oop.blankspace.domain.Prodotto;
import it.univaq.disim.oop.blankspace.domain.ProdottoConQuantità;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MieiPacchettiProdottoController
		implements Initializable, DataInitalizable<WrapperInterVista<Utente, Object, Object, Object>> {

	private Utente utente;
	private ViewDispacher dispatcher = ViewDispacher.getInstance();
	private ServizioUtente servizioUtente = BusinessFactory.getImplementation().getServizioUtente();
	private ServizioOrdine servizioOrdine = BusinessFactory.getImplementation().getServizioOrdine();

	@FXML
	private Button logoutButton;

	@FXML
	private Button indietroButton;

	@FXML
	private TableView<PacchettoProdotti> pacchettiTableView;

	@FXML
	private TableColumn<PacchettoProdotti, Button> eliminaButtonTableColumn;

	@FXML
	private TableColumn<PacchettoProdotti, Button> apriButtonTableColumn;

	@FXML
	private TableColumn<PacchettoProdotti, String> nomeTableColumn;
	
	@FXML
	private TableColumn<PacchettoProdotti, Button> ordinaColonna;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		nomeTableColumn.setCellValueFactory(new PropertyValueFactory<PacchettoProdotti, String>("nome"));

		apriButtonTableColumn.setCellValueFactory((CellDataFeatures<PacchettoProdotti, Button> param) -> {
			final Button button = new Button("Apri");
			button.setOnAction((ActionEvent e) -> {
				dispatcher.renderVista("CreaPacchettoProdotto",
						new WrapperInterVista<Utente, PacchettoProdotti, Object, Object>(utente, param.getValue(), null,
								null));
			});

			return new SimpleObjectProperty<Button>(button);
		});

		eliminaButtonTableColumn.setCellValueFactory((CellDataFeatures<PacchettoProdotti, Button> param) -> {
			final Button button = new Button("Elimina");
			button.setOnAction((ActionEvent e) -> {
				this.utente.removePacchettoProdotti(param.getValue());
				servizioUtente.aggiornaUtente(this.utente);
				dispatcher.renderVista("MieiPacchettiProdotti",
						new WrapperInterVista<Utente, Object, Object, Object>(this.utente, null, null, null));
			});

			return new SimpleObjectProperty<Button>(button);
		});
		
		ordinaColonna.setCellValueFactory((CellDataFeatures<PacchettoProdotti, Button> param) -> {
			final Button button = new Button("Ordina");
			button.setOnAction((ActionEvent e) -> {
				Ordine ordine = servizioOrdine.creaOrdine(new Ordine()); 
				ordine.setDataOrdinazione(LocalDate.now());
				ordine.setStato(StatoOrdine.ATTIVO);
				ordine.setUtente(utente);
				for(Prodotto prodotto : param.getValue().getInsiemeProdotti()) {
					ordine.aggiungiProdottoRichiesto(new ProdottoConQuantità(prodotto, "1"));
					ordine.setTotaleSpeso(ordine.getTotaleSpeso() + prodotto.getPrezzo());
				}
				servizioOrdine.aggiornaOrdine(ordine);
				dispatcher.renderVista("Ordine", new WrapperInterVista<Utente, GestoreLuogoDiRitrovo,Ordine,Prodotto>(this.utente, null,ordine, null));
			});

			return new SimpleObjectProperty<Button>(button);
		});
	}

	@Override
	public void initializeData(WrapperInterVista<Utente, Object, Object, Object> wrapper) {
		this.utente = wrapper.getDato1();

		ObservableList<PacchettoProdotti> pacchettiProdottoData = FXCollections
				.observableArrayList(this.utente.getPacchettiProdotti());
		pacchettiTableView.setItems((ObservableList<PacchettoProdotti>) pacchettiProdottoData);
	}

	public void logoutAction(ActionEvent event) {
		dispatcher.renderVista("LogIn", null);
	}

	@FXML
	private void indietroAction() {
		dispatcher.renderVista("AreaPacchetti", utente);
	}

}
