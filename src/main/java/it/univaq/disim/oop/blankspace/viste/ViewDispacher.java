package it.univaq.disim.oop.blankspace.viste;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViewDispacher {
	private final String PERCORSO = "/GUI/";
	private final String ESTENSIONE = ".fxml";
	private Stage stage;
	private BorderPane pane;
	private Scene scene;
	private static ViewDispacher dispacher = new ViewDispacher();
	
	private ViewDispacher() {}
	
	public static ViewDispacher getInstance() {
		return dispacher;
	}
	
	public void loadLogIn(Stage s) {
		try {		
			this.stage = s;
			this.stage.setTitle("Progetto Spesa: BlankSpace");
			this.stage.setResizable(false);
			Parent vista = caricaVista("LogIn").getView();
			this.pane = (BorderPane)vista;
			this.scene = new Scene(vista);
			this.stage.setScene(this.scene);
			this.stage.show();
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	public<T> void renderVista(String nomeVista,T dato){
		try {
			View<T> view = caricaVista(nomeVista);
			DataInitalizable<T> controller = view.getController();
			controller.initializeData(dato);
			Parent	p = view.getView();
			this.pane.setCenter(p);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	private <T> View<T> caricaVista(String nomeVista) {
		try {
			FXMLLoader loader= new FXMLLoader(getClass().getResource(PERCORSO+nomeVista+ESTENSIONE));
			View<T> vista = new View<T>(loader.load(), loader.getController());
			return vista;
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}
}
