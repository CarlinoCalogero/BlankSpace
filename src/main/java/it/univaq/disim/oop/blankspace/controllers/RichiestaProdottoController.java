package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.business.BusinessFactory;
import it.univaq.disim.oop.blankspace.business.ServizioOrdine;
import it.univaq.disim.oop.blankspace.domain.Negozio;
import it.univaq.disim.oop.blankspace.domain.Ordine;
import it.univaq.disim.oop.blankspace.domain.Persona;
import it.univaq.disim.oop.blankspace.domain.ProdottoConQuantità;
import it.univaq.disim.oop.blankspace.domain.ProdottoRichiesto;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import it.univaq.disim.oop.blankspace.viste.ViewDispacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RichiestaProdottoController
		implements Initializable, DataInitalizable<WrapperInterVista<Persona, Ordine>> {

	private ViewDispacher dispatcher = ViewDispacher.getInstance();
	private Persona persona;
	private Ordine ordine;

	private ServizioOrdine servizioOrdine = BusinessFactory.getImplementation().getServizioOrdine();

	@FXML
	private Button richiediProdottoButton;

	@FXML
	private Button annullaButton;

	@FXML
	private Button logoutButton;

	@FXML
	private CheckBox yesCheckBox;

	@FXML
	private CheckBox noCheckBox;

	@FXML
	private TextField nomeProdottoTextField;

	@FXML
	private TextField quantitaTextField;

	@FXML
	private ComboBox<Negozio> negozioComboBox;

	@FXML
	private TextArea noteAggiuntiveTextArea;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		negozioComboBox.getItems().addAll(Negozio.values());
		negozioComboBox.setValue(Negozio.CONAD);
		noCheckBox.setSelected(true);

		richiediProdottoButton.disableProperty()
				.bind(nomeProdottoTextField.textProperty().isEmpty().or(quantitaTextField.textProperty().isEmpty()));
	}

	public void logoutAction() {
		dispatcher.renderVista("LogIn", null);
	}

	public void richiestaProdottoAction() {
		ProdottoRichiesto prodotto = new ProdottoRichiesto();
		prodotto.setDescrizione(noteAggiuntiveTextArea.getText());
		prodotto.setMedicinale(false);
		prodotto.setNegozio(negozioComboBox.getValue());
		prodotto.setNome(nomeProdottoTextField.getText());
		this.ordine.aggiungiProdottoRichiesto(new ProdottoConQuantità(prodotto, quantitaTextField.getText()));
		servizioOrdine.aggiornaOrdine(ordine);
		dispatcher.renderVista("Ordine", new WrapperInterVista<Persona, Ordine>(persona, ordine));
	}

	public void annullaRichiestaProdottoAction() {
		dispatcher.renderVista("Ordine", new WrapperInterVista<Persona, Ordine>(persona, ordine));
	}

	public void initializeData(WrapperInterVista<Persona, Ordine> wrapper) {
		this.persona = wrapper.getDato1();
		this.ordine = wrapper.getDato2();
	}

	public void yesIsSelectedAction() {

		if (yesCheckBox.isSelected()) {
			noCheckBox.setSelected(false);
			dispatcher.renderVista("RichiestaMedicinale", persona);
		} else {// if user clicks on already selected CheckBox
			noCheckBox.setSelected(true);
			noIsSelectedAction();
			return;
		}

	}

	public void noIsSelectedAction() {

		if (noCheckBox.isSelected()) {
			yesCheckBox.setSelected(false);
		} else {// if user clicks on already selected CheckBox
			yesCheckBox.setSelected(true);
			yesIsSelectedAction();
			return;
		}

	}

}
