package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.business.BusinessFactory;
import it.univaq.disim.oop.blankspace.business.ProdottiService;
import it.univaq.disim.oop.blankspace.domain.Categoria;
import it.univaq.disim.oop.blankspace.domain.GestoreSistema;
import it.univaq.disim.oop.blankspace.domain.Negozio;
import it.univaq.disim.oop.blankspace.domain.Prodotto;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import it.univaq.disim.oop.blankspace.viste.ViewDispacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AggiungiProdottoAlSistemaController implements Initializable, DataInitalizable<GestoreSistema> {

	private ViewDispacher dispatcher = ViewDispacher.getInstance();
	private GestoreSistema admin;

	private ProdottiService prodottiService = BusinessFactory.getImplementation().getProdottiService();

	@FXML
	private Button logoutButton;

	@FXML
	private TextField nomeProdottoTextField;

	@FXML
	private TextArea descrizioneProdottoTextArea;

	@FXML
	private Button annullaButton;

	@FXML
	private Button aggiungiProdottoButton;

	@FXML
	private ComboBox<Categoria> categoriaComboBox;

	@FXML
	private ComboBox<Negozio> negozioComboBox;

	@FXML
	private TextField prezzoTextField;

	@FXML
	private Label errorLabel;

	public void logoutAction() {
		dispatcher.renderVista("LogIn", null);
	}

	public void aggiungiProdottoAction() {

		try {// checks if prezzoTextField is a number
			Double.parseDouble(prezzoTextField.getText());
		} catch (NumberFormatException e) {
			errorLabel.setText("Errore! Inserisci un numero adeguanto nel campo \"Prezzo\"!");
			errorLabel.setVisible(true);
			return;
		}

		Prodotto prodotto = new Prodotto();
		prodotto.setCategoria(categoriaComboBox.getValue());
		prodotto.setDescrizione(descrizioneProdottoTextArea.getText());
		prodotto.setNegozio(negozioComboBox.getValue());
		prodotto.setNome(nomeProdottoTextField.getText());
		prodotto.setPrezzo(Double.valueOf(prezzoTextField.getText()));

		prodottiService.aggiungiProdotto(prodotto);
		dispatcher.renderVista("HomeAdmins", this.admin);

	}

	public void annullaAgguntaProdottoAction() {
		dispatcher.renderVista("HomeAdmins", this.admin);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		categoriaComboBox.getItems().addAll(Categoria.values());
		categoriaComboBox.setValue(Categoria.CARNE);
		negozioComboBox.getItems().addAll(Negozio.values());
		negozioComboBox.setValue(Negozio.CONAD);
		errorLabel.setVisible(false);
		aggiungiProdottoButton.disableProperty().bind(nomeProdottoTextField.textProperty().isEmpty()
				.or(descrizioneProdottoTextArea.textProperty().isEmpty().or(prezzoTextField.textProperty().isEmpty())));
	}

	public void initializeData(GestoreSistema admin) {
		this.admin = admin;
	}

}
