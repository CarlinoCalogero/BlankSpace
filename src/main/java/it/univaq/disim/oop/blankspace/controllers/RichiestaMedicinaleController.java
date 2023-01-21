package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.domain.GestoreLuogoDiRitrovo;
import it.univaq.disim.oop.blankspace.domain.Ordine;
import it.univaq.disim.oop.blankspace.domain.Prodotto;
import it.univaq.disim.oop.blankspace.domain.ProdottoRichiesto;
import it.univaq.disim.oop.blankspace.domain.Utente;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import it.univaq.disim.oop.blankspace.viste.ViewDispacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class RichiestaMedicinaleController implements Initializable, DataInitalizable<WrapperInterVista<Utente, GestoreLuogoDiRitrovo, Ordine, Prodotto>> {

	private ViewDispacher dispatcher = ViewDispacher.getInstance();
	private Utente utente;
	private GestoreLuogoDiRitrovo glr;
	private Ordine ordine;

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
	private TextArea noteAggiuntiveTextArea;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		yesCheckBox.setSelected(true);
	}

	public void logoutAction() {
		dispatcher.renderVista("LogIn", null);
	}

	public void richiestaProdottoAction() {
		ProdottoRichiesto prodotto = new ProdottoRichiesto();
		prodotto.setDescrizione(noteAggiuntiveTextArea.getText());
		prodotto.setMedicinale(true);
		// TODO add to Order
	}

	public void annullaRichiestaProdottoAction() {
		dispatcher.renderVista("Ordine", new WrapperInterVista<Utente,GestoreLuogoDiRitrovo,Ordine,Prodotto>(this.utente,this.glr,ordine,null));
	}

	public void initializeData(WrapperInterVista<Utente, GestoreLuogoDiRitrovo, Ordine, Prodotto> wrapper) {
		this.utente = wrapper.getDato1();
		this.glr = wrapper.getDato2();
		this.ordine = wrapper.getDato3();
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

		noteAggiuntiveTextArea.setVisible(false);
	}

	public void noIsSelectedAction() {

		if (noCheckBox.isSelected()) {
			yesCheckBox.setSelected(false);
			dispatcher.renderVista("RichiestaProdotto", new WrapperInterVista<Utente,GestoreLuogoDiRitrovo, Ordine, Prodotto>(utente, glr, ordine, null));
		} else {// if user clicks on already selected CheckBox
			yesCheckBox.setSelected(true);
			yesIsSelectedAction();
			return;
		}

		noteAggiuntiveTextArea.setVisible(true);

	}

}
