package it.univaq.disim.oop.blankspace;

import it.univaq.disim.oop.blankspace.viste.ViewDispacher;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWorld extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			ViewDispacher.getInstance().loadLogIn(stage);
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}

}
