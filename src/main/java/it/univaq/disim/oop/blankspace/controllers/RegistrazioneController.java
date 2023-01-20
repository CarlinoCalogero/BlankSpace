package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.business.BusinessFactory;
import it.univaq.disim.oop.blankspace.business.ServizioUtente;
import it.univaq.disim.oop.blankspace.domain.Persona;
import it.univaq.disim.oop.blankspace.domain.Utente;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import it.univaq.disim.oop.blankspace.viste.ViewDispacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegistrazioneController implements Initializable, DataInitalizable<Persona> {
	private ViewDispacher dispacher = ViewDispacher.getInstance();
	private ServizioUtente servizioUtente = BusinessFactory.getImplementation().getServizioUtente();
	@FXML
	private TextField nome, cognome, residenza, email, password, confPassword, telefono;
	@FXML
	private DatePicker data;
	@FXML
	private Button registra;
	@FXML
	private Label errore;

	public void registraUtente() {
		if (!checkPassword())
			return;
		Utente newUtente = new Utente(this.nome.getText(), this.cognome.getText(), data.getValue(), email.getText(),
				telefono.getText(), password.getText(), residenza.getText());
		if(servizioUtente.registraUtente(newUtente))
			dispacher.renderVista("LogIn", null);
		else
			this.errore.setVisible(true);
	}

	public void annullaRegistrazione() {
		dispacher.renderVista("LogIn", null);
	}

	private boolean checkPassword() {
		if (password.getText().isEmpty() || password.getText().isEmpty())
			return false;
		return password.getText().equals(confPassword.getText());

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		registra.disableProperty().bind(password.textProperty().isEmpty().or(confPassword.textProperty().isEmpty()));
		registra.disableProperty()
				.bind(nome.textProperty().isEmpty().or(
						cognome.textProperty().isEmpty().or(residenza.textProperty().isEmpty().or(email.textProperty()
								.isEmpty().or(telefono.textProperty().isEmpty().or(data.valueProperty().isNull()))))));
	}
}
