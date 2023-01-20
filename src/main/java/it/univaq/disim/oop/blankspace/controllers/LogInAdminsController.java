package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.business.BusinessFactory;
import it.univaq.disim.oop.blankspace.business.ServizioUtente;
import it.univaq.disim.oop.blankspace.domain.GestoreSistema;
import it.univaq.disim.oop.blankspace.domain.Persona;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import it.univaq.disim.oop.blankspace.viste.ViewDispacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInAdminsController implements Initializable,DataInitalizable<Persona>{
	private ViewDispacher dispatcher = ViewDispacher.getInstance();
	private ServizioUtente servizioUtente = BusinessFactory.getImplementation().getServizioUtente();
	@FXML
	private TextField nickname;
	@FXML
	private PasswordField password;
	@FXML
	private Label errore;
	@FXML
	private Button accedi;

	
	public void logIn() {
		GestoreSistema logged = servizioUtente.getGestoreSistema(nickname.getText(), password.getText());
		if(logged != null) {
			dispatcher.renderVista("HomeAdmins", logged);
		} else {
			errore.setText("Email o Password errata");
			errore.setVisible(true);
		}
	}
	public void indietro() {
		dispatcher.renderVista("LogIn", null);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		accedi.disableProperty().bind(nickname.textProperty().isEmpty().or(password.textProperty().isEmpty()));
	}
}
