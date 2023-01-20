package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.domain.Negozio;
import it.univaq.disim.oop.blankspace.domain.Persona;
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

public class RichiestaProdottoController implements Initializable, DataInitalizable<Persona> {

	private ViewDispacher dispatcher = ViewDispacher.getInstance();
	private Persona persona;

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
		// TODO add to Order
	}

	public void annullaRichiestaProdottoAction() {
		dispatcher.renderVista("Ordine", persona);
	}

	public void initializeData(Persona persona) {
		this.persona = persona;
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
