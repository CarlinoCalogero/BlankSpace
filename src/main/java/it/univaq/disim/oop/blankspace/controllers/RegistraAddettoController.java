package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.business.BusinessFactory;
import it.univaq.disim.oop.blankspace.business.ServizioUtente;
import it.univaq.disim.oop.blankspace.domain.AddettoCompere;
import it.univaq.disim.oop.blankspace.domain.GestoreSistema;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import it.univaq.disim.oop.blankspace.viste.ViewDispacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistraAddettoController implements Initializable, DataInitalizable<GestoreSistema> {
	private ViewDispacher dispacher = ViewDispacher.getInstance();
	private GestoreSistema admin;
	private ServizioUtente servizioUtente = BusinessFactory.getImplementation().getServizioUtente();
	@FXML
	private TextField nome, cognome, email, telefono;
	@FXML
	private PasswordField password,confPassword;
	@FXML
	private DatePicker data;
	@FXML
	private Button registra;

	public void registraUtente() {
		if (!checkPassword())
			return;
		AddettoCompere newUtente = new AddettoCompere(this.nome.getText(), this.cognome.getText(), data.getValue(), email.getText(),
				telefono.getText(), password.getText());
		if(servizioUtente.registraAddettoCompere(newUtente))
			dispacher.renderVista("HomeAdmins", this.admin);
	}

	public void annullaRegistrazione() {
		dispacher.renderVista("HomeAdmins", this.admin);
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
						cognome.textProperty().isEmpty().or(email.textProperty()
								.isEmpty().or(telefono.textProperty().isEmpty().or(data.valueProperty().isNull())))));
	}
	@Override
	public void initializeData(GestoreSistema gs) {
		this.admin = gs;
	}
	
}
