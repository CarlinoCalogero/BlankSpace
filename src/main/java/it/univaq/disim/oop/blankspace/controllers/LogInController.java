package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.business.BusinessFactory;
import it.univaq.disim.oop.blankspace.business.ServizioUtente;
import it.univaq.disim.oop.blankspace.domain.GestoreLuogoDiRitrovo;
import it.univaq.disim.oop.blankspace.domain.Persona;
import it.univaq.disim.oop.blankspace.domain.Utente;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import it.univaq.disim.oop.blankspace.viste.ViewDispacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LogInController implements Initializable,DataInitalizable<Persona>{
	private ViewDispacher dispatcher = ViewDispacher.getInstance();
	private ServizioUtente servizioUtente = BusinessFactory.getImplementation().getServizioUtente();
	@FXML
	private TextField email, password;
	@FXML
	private Label errore;
	@FXML
	private Button accedi;

	
	public void logIn() {
		Persona logged = servizioUtente.getUtente(email.getText(), password.getText());
		String vista = "";
		if(logged == null) {
			this.errore.setVisible(true);
			this.errore.setText("Email o Password Errati");
			return;
		}
		if(logged instanceof Utente)
			vista = "Home";
		if(logged instanceof GestoreLuogoDiRitrovo)
			vista = "HomeGLR";
		dispatcher.renderVista(vista, logged);
	}
	public void logInAdmin() {
		dispatcher.renderVista("LogInAdmins", null);
	}
	public void loadRegistrazione() {
		dispatcher.renderVista("Registrazione", null);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		accedi.disableProperty().bind(email.textProperty().isEmpty().or(password.textProperty().isEmpty()));
	}
}
