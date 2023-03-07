package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;

import Class.site;
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

public class SiteVisiteur implements Initializable{
	
		private Parent fxml;

		  @FXML
		    private BorderPane borderPaneSite;

		    @FXML
		    private Button boutonEmployer;

		    @FXML
		    private Button boutonService;

		    @FXML
		    private Button boutonSites;

		    @FXML
		    private TableView<site> tableSite;

		    @FXML
		    private TableColumn<site, String> tableSiteAdresse;

		    @FXML
		    private TableColumn<site, String> tableSiteCodePostal;

		    @FXML
		    private TableColumn<site, String> tableSiteVille;


	    @FXML
	    void boutonEmployerClick(MouseEvent event) {
	    	if(MdpPage.admin) {
		    	try {
		            fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilAdmin.fxml"));
		            borderPaneSite.getChildren().removeAll();
		            borderPaneSite.getChildren().setAll(fxml);
		        } catch (IOException e) {
		            e.printStackTrace();
		        } 
	    	}else {	    		
	    		try {
	    			fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilVisiteur.fxml"));
	    			borderPaneSite.getChildren().removeAll();
	    			borderPaneSite.getChildren().setAll(fxml);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		} 
	    	}
	    }

	    @FXML
	    void boutonServiceClick(MouseEvent event) {
	    	if(MdpPage.admin) {
	    		try {
	    			fxml = FXMLLoader.load(getClass().getResource("/interfaces/ServiceAdmin.fxml"));
	    			borderPaneSite.getChildren().removeAll();
	    			borderPaneSite.getChildren().setAll(fxml);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		} 

	    	}else {	    		
	    		try {
	    			fxml = FXMLLoader.load(getClass().getResource("/interfaces/ServiceVisiteur.fxml"));
	    			borderPaneSite.getChildren().removeAll();
	    			borderPaneSite.getChildren().setAll(fxml);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		} 
	    	}
	    }

	    @FXML
	    void boutonSitesClick(MouseEvent event) {

	    }
	    
		public static ObservableList<site> getDataEmployer(){
			JSONArray sites = apiRequest.siteGet();
			ObservableList<site> list = FXCollections.observableArrayList();
	
	
		for(int i = 0; i < sites.length(); i++) {
			list.add(new site(sites.getJSONObject(i).getInt("IdSite"), sites.getJSONObject(i).getString("ville"),
					sites.getJSONObject(i).getString("adresse"), sites.getJSONObject(i).getString("codePostal")
					));
		}
		
		return list;
			
		}
		
		public void createTableau() {
			tableSiteVille.setCellValueFactory(new PropertyValueFactory<site,String>("ville"));
			tableSiteAdresse.setCellValueFactory(new PropertyValueFactory<site,String>("adresse"));
			tableSiteCodePostal.setCellValueFactory(new PropertyValueFactory<site,String>("codePostal"));
			
			tableSite.setItems(getDataEmployer());
				// open crud popup
			tableSite.setOnMouseClicked(new EventHandler<MouseEvent>(){
			
	            @Override
	            public void handle(MouseEvent event) {
	            	//open only on double click
	            	if(event.getClickCount() == 2) {   
	            		if(MdpPage.admin) {
	            			FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/SiteAdminInfo.fxml"));
	            			Parent newContent;
	            			try {
	            				newContent = loader.load();
	            				// Obtenir le contrôleur associé à la vue
	            				SiteVisiteurInfo controller = loader.getController();
	            				controller.setData(tableSite.getSelectionModel().getSelectedItem().getIdSite());
	            				
	            				// Remplacer le contenu de la scène actuelle par le nouveau contenu
	            				// Obtenir la scène actuelle
	            				Scene currentScene = tableSite.getScene();
	            				
	            				// Remplacer la racine de la scène actuelle par le nouveau contenu
	            				currentScene.setRoot(newContent);
	            			} catch (IOException e) {
	            				// TODO Auto-generated catch block
	            				e.printStackTrace();
	            			}

	            		}else {	            			
	            			FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/SiteVisiteurInfo.fxml"));
	            			Parent newContent;
	            			try {
	            				newContent = loader.load();
	            				// Obtenir le contrôleur associé à la vue
	            				SiteVisiteurInfo controller = loader.getController();
	            				controller.setData(tableSite.getSelectionModel().getSelectedItem().getIdSite());
	            				
	            				// Remplacer le contenu de la scène actuelle par le nouveau contenu
	            				// Obtenir la scène actuelle
	            				Scene currentScene = tableSite.getScene();
	            				
	            				// Remplacer la racine de la scène actuelle par le nouveau contenu
	            				currentScene.setRoot(newContent);
	            			} catch (IOException e) {
	            				// TODO Auto-generated catch block
	            				e.printStackTrace();
	            			}
	            		}
//	            			

	            	}
	        	}         
			});
		}
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		createTableau();
	}

}
