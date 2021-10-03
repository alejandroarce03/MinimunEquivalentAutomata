package ui;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


	@Override
	public void start(Stage stage)   {
		try {	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Automaton");
			stage.show();
			stage.sizeToScene();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {	
		launch(args);
	}

}
