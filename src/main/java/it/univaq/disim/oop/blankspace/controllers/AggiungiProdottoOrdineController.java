package it.univaq.disim.oop.blankspace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.blankspace.domain.Ordine;
import it.univaq.disim.oop.blankspace.viste.DataInitalizable;
import it.univaq.disim.oop.blankspace.viste.ViewDispacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AggiungiProdottoOrdineController implements Initializable, DataInitalizable<Ordine>{
	
	private ViewDispacher dispatcher = ViewDispacher.getInstance();

	@FXML
	private Button aggiungiProdottoBottone;
	
	@FXML
	private Button esciBottone;
	
	@FXML
	private Label dataEtichetta;
	
	@FXML
	private Label negozioEtichetta;
	
	@FXML
	private Label prodottoEtichetta;
	
	@FXML
	private TextField quantitàCampo;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void initializeData(Ordine ordine) {
		
	}
	
	@FXML
	private void esci(ActionEvent event) {}
	
	@FXML
	private void aggiungiProdottoAllOrdine(ActionEvent event) {
		
	}

}
