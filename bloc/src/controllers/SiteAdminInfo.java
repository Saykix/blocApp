package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;

import Class.site;
import application.apiRequest;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class SiteAdminInfo implements Initializable{

		private Parent fxml;
		 @FXML
		    private Button BoutonModifier;

		    @FXML
		    private Button BoutonSupprimer;

		    @FXML
		    private TextField adresse;

		    @FXML
		    private BorderPane borderPaneSiteInfo;

		    @FXML
		    private Button boutonAjouterSite;

		    @FXML
		    private Button boutonEmployer;

		    @FXML
		    private Button boutonService;

		    @FXML
		    private Button boutonSites;

		    @FXML
		    private ImageView closeInfo;

		    @FXML
		    private TextField codePostal;
	    @FXML
	    private TableView<site> tableSite;

	    @FXML
	    private TableColumn<site, String> tableSiteAdresse;

	    @FXML
	    private TableColumn<site, String> tableSiteCodePostal;

	    @FXML
	    private TableColumn<site, String> tableSiteVille;


	    @FXML
	    private TextField ville;

	    int id;
	    
	    @FXML
	    void BoutonModifierClick(MouseEvent event) {
	    	try {
	    		site site = new site(ville.getText(),adresse.getText(),codePostal.getText());
				apiRequest.sitePut(id,site);
				createTableau();
				createSiteInfo(id);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void BoutonSupprimerClick(MouseEvent event) {
			apiRequest.siteDelete(id);
			createTableau();
	    }

	    @FXML
	    void boutonAjouterSiteClick(MouseEvent event) {
	    	try {
	            fxml = FXMLLoader.load(getClass().getResource("/interfaces/SiteAdminAjouter.fxml"));
	            borderPaneSiteInfo.getChildren().removeAll();
	            borderPaneSiteInfo.getChildren().setAll(fxml);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	    }

	    
	    @FXML
	    void boutonEmployerClick(MouseEvent event) {
	    	try {
	            fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilAdmin.fxml"));
	            borderPaneSiteInfo.getChildren().removeAll();
	            borderPaneSiteInfo.getChildren().setAll(fxml);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	    }

	    @FXML
	    void boutonServiceClick(MouseEvent event) {
	    	try {
	            fxml = FXMLLoader.load(getClass().getResource("/interfaces/ServiceAdmin.fxml"));
	            borderPaneSiteInfo.getChildren().removeAll();
	            borderPaneSiteInfo.getChildren().setAll(fxml);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	    }

	    @FXML
	    void boutonSitesClick(MouseEvent event) {

	    }
	    
	    @FXML
	    void closeInfoClick(MouseEvent event) {
	    	try {
	            fxml = FXMLLoader.load(getClass().getResource("/interfaces/SiteAdmin.fxml"));
	            borderPaneSiteInfo.getChildren().removeAll();
	            borderPaneSiteInfo.getChildren().setAll(fxml);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	    }
	    
		public static ObservableList<site> getDataSite(){
			JSONArray sites = apiRequest.siteGet();
			ObservableList<site> list = FXCollections.observableArrayList();
	
			for(int i = 0; i < sites.length(); i++) {
				list.add(new site(sites.getJSONObject(i).getInt("IdSite"), sites.getJSONObject(i).getString("ville"),
						sites.getJSONObject(i).getString("adresse"), sites.getJSONObject(i).getString("codePostal")
						));
			}
		
		return list;
			
		}
		
		public void createSiteInfo(int id) {
			JSONArray Site = apiRequest.siteGetById(id);
			 Platform.runLater(() -> {				 
				 ville.setText(Site.getJSONObject(0).getString("ville"));
				 adresse.setText(Site.getJSONObject(0).getString("adresse"));
				 codePostal.setText(Site.getJSONObject(0).getString("codePostal"));
			 });
		}
		
		
		public void createTableau() {
			tableSiteVille.setCellValueFactory(new PropertyValueFactory<site,String>("ville"));
			tableSiteAdresse.setCellValueFactory(new PropertyValueFactory<site,String>("adresse"));
			tableSiteCodePostal.setCellValueFactory(new PropertyValueFactory<site,String>("codePostal"));
			
			tableSite.setItems(getDataSite());
				// open crud popup
			tableSite.setOnMouseClicked(new EventHandler<MouseEvent>(){
				
	            @Override
	            public void handle(MouseEvent event) {
	            	//open only on double click
	            	if(event.getClickCount() == 2) {            		
	            		createSiteInfo(tableSite.getSelectionModel().getSelectedItem().getIdSite());
	            	
	            	}
	            }   
	        });
		}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		createTableau();
	}
	
	public void setData(int id) {
		this.id = id;
		createSiteInfo(id);
	}

}
