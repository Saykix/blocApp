package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;

import Class.employer;
import application.apiRequest;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class AccueilAdmin implements Initializable{
	private Parent fxml;

	  @FXML
	    private BorderPane borderPaneMain;

	    @FXML
	    private Button boutonAjouter;

	    @FXML
	    private Button boutonEmployer;

	    @FXML
	    private Button boutonService;

	    @FXML
	    private Button boutonSites;

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
	    void boutonAjouterClick(MouseEvent event) {
	    	try {
				fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilAdminAjouter.fxml"));
				borderPaneMain.getChildren().removeAll();
				borderPaneMain.getChildren().setAll(fxml);
			} catch (IOException e) {
				e.printStackTrace();
			} 
	    }
	    @FXML
	    void boutonEmployerClick(MouseEvent event) {
	    	try {
				fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilAdminAjouter.fxml"));
				borderPaneMain.getChildren().removeAll();
				borderPaneMain.getChildren().setAll(fxml);
			} catch (IOException e) {
				e.printStackTrace();
			} 
	    }

	    @FXML
	    void boutonServiceClick(MouseEvent event) {
	    	boolean admin = MdpPage.admin;

       		if(admin) {

				try {
					fxml = FXMLLoader.load(getClass().getResource("/interfaces/ServiceAdmin.fxml"));
					borderPaneMain.getChildren().removeAll();
					borderPaneMain.getChildren().setAll(fxml);
				} catch (IOException e) {
					e.printStackTrace();
				} 
				}else {
					
					try {
						fxml = FXMLLoader.load(getClass().getResource("/interfaces/ServiceVisiteur.fxml"));
						borderPaneMain.getChildren().removeAll();
						borderPaneMain.getChildren().setAll(fxml);
					} catch (IOException e) {
						e.printStackTrace();
					} 
				}
	    }

	    @FXML
	    void boutonSitesClick(MouseEvent event) {
	    	try {
	            fxml = FXMLLoader.load(getClass().getResource("/interfaces/SiteAdmin.fxml"));
	            borderPaneMain.getChildren().removeAll();
	            borderPaneMain.getChildren().setAll(fxml);
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
		
		public void createTableau() {
			tableEmployerNom.setCellValueFactory(new PropertyValueFactory<employer,String>("nom"));
			tableEmployerPrenom.setCellValueFactory(new PropertyValueFactory<employer,String>("prenom"));
			tableEmployerFixe.setCellValueFactory(new PropertyValueFactory<employer,String>("fixe"));
			tableEmployerPortable.setCellValueFactory(new PropertyValueFactory<employer,String>("portable"));
			tableEmployerEmail.setCellValueFactory(new PropertyValueFactory<employer,String>("email"));
			tableEmployerService.setCellValueFactory(new PropertyValueFactory<employer,String>("employerService"));
			tableEmployerSite.setCellValueFactory(new PropertyValueFactory<employer,String>("employerSite"));
			
			tableEmployer.setItems(getDataEmployer());
				// open crud popup
			tableEmployer.setOnMouseClicked(new EventHandler<MouseEvent>(){
			
	            @Override
	            public void handle(MouseEvent event) {
	            	//open only on double click
	            	if(event.getClickCount() == 2) {  
	            		boolean admin = MdpPage.admin;

			       		if(admin) {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/AccueilAdminInfo.fxml"));
							Parent newContent;
							try {
								newContent = loader.load();
								AccueilAdminInfo controllerPage = loader.getController();
								controllerPage.setData(tableEmployer.getSelectionModel().getSelectedItem().getIdEmployer());
								
								Scene currentScene = borderPaneMain.getScene();
	
								currentScene.setRoot(newContent);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			       		}else {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/AccueilVisiteurInfo.fxml"));
							Parent newContent;
							try {
								newContent = loader.load();
								AccueilVisiteurInfo controllerPage = loader.getController();
								controllerPage.setData(tableEmployer.getSelectionModel().getSelectedItem().getIdEmployer());
								
								Scene currentScene = borderPaneMain.getScene();
	
								currentScene.setRoot(newContent);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			       		}	

	            	}
	        	}         
			});
		}
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		createTableau();
	}

}
