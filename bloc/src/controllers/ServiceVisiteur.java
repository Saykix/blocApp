package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;

import Class.service;
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

public class ServiceVisiteur implements Initializable{
	
		private Parent fxml;

		  @FXML
		    private BorderPane borderPaneService;

		    @FXML
		    private Button boutonEmployer;

		    @FXML
		    private Button boutonService;

		    @FXML
		    private Button boutonSites;

		    @FXML
		    private TableView<service> tableService;

		    @FXML
		    private TableColumn<service, String> tableServiceService;



	    @FXML
	    void boutonEmployerClick(MouseEvent event) {
	    	if(MdpPage.admin) {
	    		try {
	    			fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilAdmin.fxml"));
	    			borderPaneService.getChildren().removeAll();
	    			borderPaneService.getChildren().setAll(fxml);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		} 
	    	}else {	    		
	    		try {
	    			fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilVisiteur.fxml"));
	    			borderPaneService.getChildren().removeAll();
	    			borderPaneService.getChildren().setAll(fxml);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		} 
	    	}
	    }

	    @FXML
	    void boutonServiceClick(MouseEvent event) {

	    }

	    @FXML
	    void boutonSitesClick(MouseEvent event) {
	    	if(MdpPage.admin) {
	    		try {
	    			fxml = FXMLLoader.load(getClass().getResource("/interfaces/SiteAdmin.fxml"));
	    			borderPaneService.getChildren().removeAll();
	    			borderPaneService.getChildren().setAll(fxml);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		} 
	    	}else {	    		
	    		try {
	    			fxml = FXMLLoader.load(getClass().getResource("/interfaces/SiteVisiteur.fxml"));
	    			borderPaneService.getChildren().removeAll();
	    			borderPaneService.getChildren().setAll(fxml);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		} 
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
		
		public void createTableau() {
			tableServiceService.setCellValueFactory(new PropertyValueFactory<service,String>("nomService"));
			
			tableService.setItems(getDataService());
				// open crud popup
			tableService.setOnMouseClicked(new EventHandler<MouseEvent>(){
			
	            @Override
	            public void handle(MouseEvent event) {
	            	
	            	//open only on double click
	            	if(event.getClickCount() == 2) {   
	            		boolean admin =MdpPage.admin;

			       		if(admin) {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/ServiceAdminInfo.fxml"));
							Parent newContent;
							try {
								newContent = loader.load();
								ServiceAdminInfo controllerPage = loader.getController();
								controllerPage.setData(tableService.getSelectionModel().getSelectedItem().getIdService());
								
								Scene currentScene = borderPaneService.getScene();
	
								currentScene.setRoot(newContent);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			       		}else {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/ServiceVisiteurInfo.fxml"));
							Parent newContent;
							try {
								newContent = loader.load();
								ServiceVisiteurInfo controllerPage = loader.getController();
								controllerPage.setData(tableService.getSelectionModel().getSelectedItem().getIdService());
								
								Scene currentScene = borderPaneService.getScene();
	
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
