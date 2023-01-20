package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.domain.GestoreSistema;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import it.univaq.disim.oop.blankspace.viste.ViewDispacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class HomeAdminsController implements Initializable, DataInitalizable<GestoreSistema> {

	private GestoreSistema admin;
	private ViewDispacher dispatcher = ViewDispacher.getInstance();

	@FXML
	private Button logoutButton;

	@FXML
	private ImageView addAddetto;

	@FXML
	private ImageView addGLR;

	@FXML
	private ImageView addProdotto;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void initializeData(GestoreSistema utente) {
		this.admin = utente;
	}

	@FXML
	private void addAddetto() {
		System.out.println("Aggiungi Addetto...");
	}

	@FXML
	private void addGestoreLuogoDiRitrovo() {
		System.out.println("Aggiungi GLR...");
	}

	@FXML
	private void addProdotto() {
		dispatcher.renderVista("AggiungiProdottoAlSistema", admin);
	}

	@FXML
	private void logoutAction() {
		dispatcher.renderVista("LogIn", null);
	}

}
