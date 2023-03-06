package application;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	List<String> keyPressed = new ArrayList<String>();
	List<String> codePass = new ArrayList<String>();
	


	@Override
	public void start(Stage primaryStage) {
		codePass.add("SPACE");
		codePass.add("W");
		codePass.add("A");
		codePass.add("Y");
		codePass.add("A");
		codePass.add("N");
		try {
			BorderPane root = FXMLLoader.load(getClass().getResource("/interfaces/AccueilVisiteur.fxml"));
			Scene scene = new Scene(root);
		    scene.setOnKeyReleased(k -> {
		    	keyPressed.add(k.getCode().toString());
			  if(keyPressed.size() > codePass.size()) {
				  keyPressed.clear();
			  }else if(codePass.stream().allMatch(keyPressed::contains)){
          		FXMLLoader Loader = new FXMLLoader();
          		Loader.setLocation(getClass().getResource("/interfaces/MdpPage.fxml"));
          		try {
          			Loader.load();
          		} catch (IOException ex) {
          			ex.printStackTrace();
          		}
          		
          		
          		Parent p = Loader.getRoot();
          		Stage stage = new Stage();
          		stage.setScene(new Scene(p));
          		stage.show();
			  }
		    });
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
