package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;

import Class.employer;
import application.apiRequest;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AccueilVisiteurInfo implements Initializable{

		private Parent fxml;
	  @FXML
	    private BorderPane borderPanevisiteurInfo;

	    @FXML
	    private Button boutonEmployer;

	    @FXML
	    private Button boutonService;

	    @FXML
	    private Button boutonSites;

	    @FXML
	    private ImageView closeInfo;
	    
	    @FXML
	    private TableView<employer> tableEmployer;

	    @FXML
	    private TableColumn<employer, String> tableEmployerEmail;

	    @FXML
	    private TableColumn<employer, String> tableEmployerFixe;

	    @FXML
	    private TableColumn<employer, String> tableEmployerNom;

	    @FXML
	    private TableColumn<employer, String> tableEmployerPortable;

	    @FXML
	    private TableColumn<employer, String> tableEmployerPrenom;

	    @FXML
	    private TableColumn<employer, String> tableEmployerService;

	    @FXML
	    private TableColumn<employer, String> tableEmployerSite;

	    
	    @FXML
	    private Text textEmail;

	    @FXML
	    private Text textFixe;

	    @FXML
	    private Text textNom;

	    @FXML
	    private Text textPortable;

	    @FXML
	    private Text textPrenom;

	    @FXML
	    private Text textService;

	    @FXML
	    private Text textSite;
	    
	    
	    @FXML
	    void boutonEmployerClick(MouseEvent event) {

	    }

	    @FXML
	    void boutonServiceClick(MouseEvent event) {

	    }

	    @FXML
	    void boutonSitesClick(MouseEvent event) {

	    }
	    
	    @FXML
	    void closeInfoClick(MouseEvent event) {
	    	try {
	            fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilVisiteur.fxml"));
	            borderPanevisiteurInfo.getChildren().removeAll();
	            borderPanevisiteurInfo.getChildren().setAll(fxml);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	    }
	    
		public static ObservableList<employer> getDataEmployer(){
			JSONArray employes = apiRequest.employerGet();
			ObservableList<employer> list = FXCollections.observableArrayList();
	
	
		for(int i = 0; i < employes.length(); i++) {
			JSONArray employerSite = apiRequest.siteGetById(employes.getJSONObject(i).getInt("employerSite"));
			JSONArray employerService = apiRequest.serviceGetById(employes.getJSONObject(i).getInt("employerService"));
			list.add(new employer(employes.getJSONObject(i).getInt("IdEmployer"), employes.getJSONObject(i).getString("nom"),
					employes.getJSONObject(i).getString("prenom"), employes.getJSONObject(i).getString("fixe"),
					employes.getJSONObject(i).getString("portable"),employes.getJSONObject(i).getString("email"),
					employerService.getJSONObject(0).getString("nomService"), employerSite.getJSONObject(0).getString("ville")
					));
		}
		
		return list;
			
		}
		
		public static ObservableList<employer> getDataEmployerID(int id){
			JSONArray employes = apiRequest.employerGetById(id);
			ObservableList<employer> list = FXCollections.observableArrayList();
	
	
		for(int i = 0; i < employes.length(); i++) {
			JSONArray employerSite = apiRequest.siteGetById(employes.getJSONObject(i).getInt("employerSite"));
			JSONArray employerService = apiRequest.serviceGetById(employes.getJSONObject(i).getInt("employerService"));
			list.add(new employer(employes.getJSONObject(i).getInt("IdEmployer"), employes.getJSONObject(i).getString("nom"),
					employes.getJSONObject(i).getString("prenom"), employes.getJSONObject(i).getString("fixe"),
					employes.getJSONObject(i).getString("portable"),employes.getJSONObject(i).getString("email"),
					employerService.getJSONObject(0).getString("nomService"), employerSite.getJSONObject(0).getString("ville")
					));
		}
		
		return list;
			
		}
		
		public void createEmployerInfo(int id) {
			System.out.println(id);
			JSONArray employes = apiRequest.employerGetById(id);
			JSONArray employerSite = apiRequest.siteGetById(employes.getJSONObject(0).getInt("employerSite"));
			JSONArray employerService = apiRequest.serviceGetById(employes.getJSONObject(0).getInt("employerService"));
			System.out.println(employes.getJSONObject(0).getString("nom"));
			 Platform.runLater(() -> {				 
				 textNom.setText(employes.getJSONObject(0).getString("nom"));
				 textPrenom.setText(employes.getJSONObject(0).getString("prenom"));
				 textFixe.setText(employes.getJSONObject(0).getString("fixe"));
				 textPortable.setText(employes.getJSONObject(0).getString("portable"));
				 textEmail.setText(employes.getJSONObject(0).getString("email"));
				 textService.setText(employerService.getJSONObject(0).getString("nomService"));
				 textSite.setText(employerSite.getJSONObject(0).getString("ville"));
			 });
		}
		
		
		public void createTableau() {
			tableEmployerNom.setCellValueFactory(new PropertyValueFactory<employer,String>("nom"));
			tableEmployerPrenom.setCellValueFactory(new PropertyValueFactory<employer,String>("prenom"));
			tableEmployerFixe.setCellValueFactory(new PropertyValueFactory<employer,String>("fixe"));
			tableEmployerPortable.setCellValueFactory(new PropertyValueFactory<employer,String>("portable"));
			tableEmployerEmail.setCellValueFactory(new PropertyValueFactory<employer,String>("email"));
			tableEmployerService.setCellValueFactory(new PropertyValueFactory<employer,String>("employerService"));
			tableEmployerSite.setCellValueFactory(new PropertyValueFactory<employer,String>("employerSite"));
			
			tableEmployer.setItems(getDataEmployer());
			tableEmployer.setOnMouseClicked(new EventHandler<MouseEvent>(){
				
	            @Override
	            public void handle(MouseEvent event) {
	            	//open only on double click
	            	if(event.getClickCount() == 2) {            		
	            		createEmployerInfo(tableEmployer.getSelectionModel().getSelectedItem().getIdEmployer());
	            	
	            	}
	            }   
	        });
		}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		JSONArray employes = apiRequest.employerGet();
//		System.out.println(employes);
		createTableau();
		FXMLLoader Loader = new FXMLLoader();
		Loader.setLocation(getClass().getResource("/interfaces/AccueilVisiteur.fxml"));
		try {
			Loader.load();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	
		Loader.getController();
		
		Parent p = Loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(p));
		stage.close();
	}
	
	public void setData(int id) {
		createEmployerInfo(id);
	}

}
