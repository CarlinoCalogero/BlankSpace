package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.business.BusinessFactory;
import it.univaq.disim.oop.blankspace.business.ProdottiService;
import it.univaq.disim.oop.blankspace.domain.Categoria;
import it.univaq.disim.oop.blankspace.domain.GestoreSistema;
import it.univaq.disim.oop.blankspace.domain.Negozio;
import it.univaq.disim.oop.blankspace.domain.Prodotto;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import it.univaq.disim.oop.blankspace.viste.ViewDispacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AggiungiProdottoAlSistemaController implements Initializable, DataInitalizable<GestoreSistema> {

	private ViewDispacher dispatcher = ViewDispacher.getInstance();
	private GestoreSistema admin;

	private ProdottiService prodottiService = BusinessFactory.getImplementation().getProdottiService();

	@FXML
	private Button logoutButton;

	@FXML
	private TextField nomeProdottoTextField;

	@FXML
	private TextArea descrizioneProdottoTextArea;

	@FXML
	private Button annullaButton;

	@FXML
	private Button aggiungiProdottoButton;

	@FXML
	private ComboBox<Categoria> categoriaComboBox;

	@FXML
	private ComboBox<Negozio> negozioComboBox;

	@FXML
	private TextField prezzoTextField;

	public void logoutAction() {
		dispatcher.renderVista("LogIn", null);
	}

	public void aggiungiProdottoAction() {
		Prodotto prodotto = new Prodotto();
		prodotto.setCategoria(categoriaComboBox.getValue());
		prodotto.setDescrizione(descrizioneProdottoTextArea.getText());
		prodotto.setNegozio(negozioComboBox.getValue());
		prodotto.setNome(nomeProdottoTextField.getText());
		// price
		prodottiService.aggiungiProdotto(prodotto);

	}

	public void annullaAgguntaProdottoAction() {
		dispatcher.renderVista("HomeAdmins", this.admin);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		categoriaComboBox.getItems().addAll(Categoria.values());
		negozioComboBox.getItems().addAll(Negozio.values());
	}

	public void initializeData(GestoreSistema admin) {
		this.admin = admin;
	}

}
