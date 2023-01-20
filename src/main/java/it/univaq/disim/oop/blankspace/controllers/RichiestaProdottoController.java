package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.domain.Negozio;
import it.univaq.disim.oop.blankspace.domain.Persona;
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

	@FXML
	private Button richiediProdottoButton;

	@FXML
	private Button annullaButton;

	@FXML
	private Button logoutButton;

	@FXML
	private Button ricettaMedicaButton;

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
		noCheckBox.setSelected(true);
		ricettaMedicaButton.setVisible(false);
	}

	public void logoutAction() {
		dispatcher.renderVista("LogIn", null);
	}

	public void richiestaProdottoAction() {
		// TODO goes back to the previous view
	}

	public void annullaRichiestaProdottoAction() {
		// TODO goes back to the previous view
	}

	public void yesIsSelectedAction() {

		if (yesCheckBox.isSelected()) {
			noCheckBox.setSelected(false);
		} else {// if user clicks on already selected CheckBox
			noCheckBox.setSelected(true);
			noIsSelectedAction();
			return;
		}

		ricettaMedicaButton.setVisible(true);

		nomeProdottoTextField.setVisible(false);
		quantitaTextField.setVisible(false);
		negozioComboBox.setVisible(false);
		noteAggiuntiveTextArea.setVisible(false);
	}

	public void noIsSelectedAction() {

		if (noCheckBox.isSelected()) {
			yesCheckBox.setSelected(false);
		} else {// if user clicks on already selected CheckBox
			yesCheckBox.setSelected(true);
			yesIsSelectedAction();
			return;
		}

		ricettaMedicaButton.setVisible(false);

		nomeProdottoTextField.setVisible(true);
		quantitaTextField.setVisible(true);
		negozioComboBox.setVisible(true);
		noteAggiuntiveTextArea.setVisible(true);

	}

}
