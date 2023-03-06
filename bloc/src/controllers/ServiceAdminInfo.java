package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;

import Class.service;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ServiceAdminInfo implements Initializable{

		private Parent fxml;
		@FXML
	    private BorderPane borderPaneServiceInfo;

	    @FXML
	    private Button boutonEmployer;

	    @FXML
	    private Button boutonService;

	    @FXML
	    private Button boutonSites;
	    
	    @FXML
	    private Button boutonSupprimer;

	    @FXML
	    private Button BoutonModifer;
	    
	    @FXML
	    private Button boutonAjouterService;
	    
	    @FXML
	    private ImageView closeInfo;

	    @FXML
	    private TableView<service> tableService;

	    @FXML
	    private TableColumn<service, String> tableServiceService;

	    int id;
	    @FXML
	    private TextField nomServiceText;
	    
	    @FXML
	    void boutonEmployerClick(MouseEvent event) {
	    	try {
	            fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilAdmin.fxml"));
	            borderPaneServiceInfo.getChildren().removeAll();
	            borderPaneServiceInfo.getChildren().setAll(fxml);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	    }

	    
	    @FXML
	    void boutonServiceClick(MouseEvent event) {

	    }

	    @FXML
	    void boutonSitesClick(MouseEvent event) {
	    	try {
	            fxml = FXMLLoader.load(getClass().getResource("/interfaces/SiteAdmin.fxml"));
	            borderPaneServiceInfo.getChildren().removeAll();
	            borderPaneServiceInfo.getChildren().setAll(fxml);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	    }
	    
	    @FXML
	    void closeInfoClick(MouseEvent event) {
	    	try {
	            fxml = FXMLLoader.load(getClass().getResource("/interfaces/ServiceAdmin.fxml"));
	            borderPaneServiceInfo.getChildren().removeAll();
	            borderPaneServiceInfo.getChildren().setAll(fxml);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	    }
	    
	    @FXML
	    void boutonSupprimerClick(MouseEvent event) {
	    	apiRequest.serviceDelete(id);
	    	try {
	            fxml = FXMLLoader.load(getClass().getResource("/interfaces/ServiceAdmin.fxml"));
	            borderPaneServiceInfo.getChildren().removeAll();
	            borderPaneServiceInfo.getChildren().setAll(fxml);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	    }
	    @FXML
	    void BoutonModiferClick(MouseEvent event) {
	    	try {
				apiRequest.servicePut(id, nomServiceText.getText());
				createTableau();
				createServiceInfo(id);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    @FXML
	    void boutonAjouterServiceClick(MouseEvent event) {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/ServiceAdminAjouter.fxml"));
			Parent newContent;
			try {
				newContent = loader.load();
				
				
				Scene currentScene = borderPaneServiceInfo.getScene();

				currentScene.setRoot(newContent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
		public static ObservableList<service> getDataService(){
			JSONArray services = apiRequest.serviceGet();
			ObservableList<service> list = FXCollections.observableArrayList();
	
		
			for(int i = 0; i < services.length(); i++) {
				list.add(new service(services.getJSONObject(i).getInt("IdService"), services.getJSONObject(i).getString("nomService")));
			}
		
		return list;
			
		}
		
		public void createServiceInfo(int id) {
			JSONArray Site = apiRequest.serviceGetById(id);
			 Platform.runLater(() -> {				 
				 nomServiceText.setText(Site.getJSONObject(0).getString("nomService"));
			 });
		}
		
		
		public void createTableau() {
			tableServiceService.setCellValueFactory(new PropertyValueFactory<service,String>("nomService"));
			
			tableService.setItems(getDataService());
				// open crud popup
			tableService.setOnMouseClicked(new EventHandler<MouseEvent>(){
				
	            @Override
	            public void handle(MouseEvent event) {
	            	//open only on double click
	            	if(event.getClickCount() == 2) {            		
	            		createServiceInfo(tableService.getSelectionModel().getSelectedItem().getIdService());
	            	
	            	}
	            }   
	        });
		}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		createTableau();
	}
	
	public void setData(int id) {
		this.id=id;
		createServiceInfo(id);
	}

}
