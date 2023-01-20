package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.business.BusinessFactory;
import it.univaq.disim.oop.blankspace.business.ServizioUtente;
import it.univaq.disim.oop.blankspace.domain.PacchettoProdotti;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		nomeTableColumn.setCellValueFactory(new PropertyValueFactory<PacchettoProdotti, String>("nome"));

		apriButtonTableColumn.setCellValueFactory((CellDataFeatures<PacchettoProdotti, Button> param) -> {
			final Button button = new Button("Apri");
			button.setOnAction((ActionEvent e) -> {

			});

			return new SimpleObjectProperty<Button>(button);
		});

		eliminaButtonTableColumn.setCellValueFactory((CellDataFeatures<PacchettoProdotti, Button> param) -> {
			final Button button = new Button("Elimina");
			button.setOnAction((ActionEvent e) -> {
				this.utente.removePacchettoProdotti(param.getValue());
				servizioUtente.aggiornaUtente(this.utente);
				dispatcher.renderVista("MieiPacchettiProdotto",
						new WrapperInterVista<Utente, Object, Object, Object>(this.utente, null, null, null));
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
		dispatcher.renderVista("Home", utente);
	}

}
