package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.domain.PacchettoProdotti;
import it.univaq.disim.oop.blankspace.domain.Persona;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import it.univaq.disim.oop.blankspace.viste.ViewDispacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class AreaPacchettiController implements Initializable, DataInitalizable<Persona> {

	private ViewDispacher dispatcher = ViewDispacher.getInstance();
	private Persona persona;

	@FXML
	private Button logoutButton;

	@FXML
	private ImageView creaPacchettoImageView;

	@FXML
	private ImageView iMieiPacchettiImageView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void logoutAction() {
		dispatcher.renderVista("LogIn", null);
	}

	public void initializeData(Persona persona) {
		this.persona = persona;
	}

	public void creaPacchettoAction() {
		dispatcher.renderVista("CreaPacchettoProdotto",
				new WrapperInterVista<Persona, PacchettoProdotti, Object, Object>(persona, null, null, null));
	}

	public void visualizzaIMieiPacchettiAction() {
		dispatcher.renderVista("MieiPacchettiProdotto",
				new WrapperInterVista<Persona, Object, Object, Object>(persona, null, null, null));
	}

}
