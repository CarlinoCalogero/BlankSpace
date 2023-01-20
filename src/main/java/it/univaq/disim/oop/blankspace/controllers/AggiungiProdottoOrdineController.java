package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.domain.GestoreLuogoDiRitrovo;
import it.univaq.disim.oop.blankspace.domain.Ordine;
import it.univaq.disim.oop.blankspace.domain.Prodotto;
import it.univaq.disim.oop.blankspace.domain.ProdottoConQuantita;
import it.univaq.disim.oop.blankspace.domain.Utente;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import it.univaq.disim.oop.blankspace.viste.ViewDispacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AggiungiProdottoOrdineController implements Initializable, DataInitalizable<WrapperInterVista<Utente,GestoreLuogoDiRitrovo,Ordine,Prodotto>> {

	private ViewDispacher dispatcher = ViewDispacher.getInstance();
	private Utente utente;
	private GestoreLuogoDiRitrovo glr;
	private Ordine ordine;
	private Prodotto prodotto;

	@FXML
	private Button aggiungiBottone;

	@FXML
	private Button annullaBottone;

	@FXML
	private Label nomeProdotto;
	@FXML
	private Label categoria;
	@FXML
	private Label negozio;
	@FXML
	private TextArea descrizione;
	@FXML
	private TextField quantita;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.aggiungiBottone.disableProperty().bind(this.quantita.textProperty().isEmpty());

	}

	@Override
	public void initializeData(WrapperInterVista<Utente,GestoreLuogoDiRitrovo,Ordine,Prodotto> dati) {
		//Prendo la persona
		this.utente= dati.getDato1();
		//Prendo il glr
		this.glr=dati.getDato2();
		//Prendo l'ordine
		this.ordine = dati.getDato3();
		//Prendo il prodotto
		this.prodotto = dati.getDato4();
		this.descrizione.setText(prodotto.getDescrizione());
		this.nomeProdotto.setText(prodotto.getNome());
		this.negozio.setText(prodotto.getNegozio().toString());
		this.categoria.setText(prodotto.getCategoria().toString());
		
	}

	@FXML
	private void esci(ActionEvent event) {
		dispatcher.renderVista("LogIn",null);
	}
	@FXML
	private void annulla() {
		dispatcher.renderVista("Ordine",new WrapperInterVista<Utente,GestoreLuogoDiRitrovo,Ordine,Prodotto>(this.utente,this.glr,this.ordine,this.prodotto));
	}
	@FXML
	private void aggiungiProdottoAllOrdine(ActionEvent event) {
		this.ordine.aggiungiProdottoRichiesto(new ProdottoConQuantita(prodotto,this.quantita.getText()));
		this.ordine.setTotaleSpeso(this.ordine.getTotaleSpeso() + prodotto.getPrezzo()*Double.parseDouble(this.quantita.getText()));
		dispatcher.renderVista("Ordine",new WrapperInterVista<Utente,GestoreLuogoDiRitrovo,Ordine,Prodotto>(this.utente,this.glr,this.ordine,null));
	}

}
