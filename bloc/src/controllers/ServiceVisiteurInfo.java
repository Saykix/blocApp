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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class ServiceVisiteurInfo implements Initializable{

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
	    private ImageView closeInfo;

	    @FXML
	    private TableView<service> tableService;

	    @FXML
	    private TableColumn<service, String> tableServiceService;

	    @FXML
	    private Text textService;

	    
	    @FXML
	    void boutonEmployerClick(MouseEvent event) {
	    	if(MdpPage.admin) {
		    	try {
		            fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilAdmin.fxml"));
		            borderPaneServiceInfo.getChildren().removeAll();
		            borderPaneServiceInfo.getChildren().setAll(fxml);
		        } catch (IOException e) {
		            e.printStackTrace();
		        } 
	    	}else {	    		
	    		try {
	    			fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilVisiteur.fxml"));
	    			borderPaneServiceInfo.getChildren().removeAll();
	    			borderPaneServiceInfo.getChildren().setAll(fxml);
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
	    			borderPaneServiceInfo.getChildren().removeAll();
	    			borderPaneServiceInfo.getChildren().setAll(fxml);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}  		
	    	}else {	    		
	    		try {
	    			fxml = FXMLLoader.load(getClass().getResource("/interfaces/SiteVisiteur.fxml"));
	    			borderPaneServiceInfo.getChildren().removeAll();
	    			borderPaneServiceInfo.getChildren().setAll(fxml);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		} 
	    	}
	    }
	    
	    @FXML
	    void closeInfoClick(MouseEvent event) {
	    	if(MdpPage.admin) {
	    		try {
	    			fxml = FXMLLoader.load(getClass().getResource("/interfaces/ServiceAdmin.fxml"));
	    			borderPaneServiceInfo.getChildren().removeAll();
	    			borderPaneServiceInfo.getChildren().setAll(fxml);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		} 

	    	}else {	    		
	    		try {
	    			fxml = FXMLLoader.load(getClass().getResource("/interfaces/ServiceVisiteur.fxml"));
	    			borderPaneServiceInfo.getChildren().removeAll();
	    			borderPaneServiceInfo.getChildren().setAll(fxml);
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
		
		public void createServiceInfo(int id) {
			System.out.println(id);
			JSONArray Site = apiRequest.serviceGetById(id);
			 Platform.runLater(() -> {				 
				 textService.setText(Site.getJSONObject(0).getString("nomService"));
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
		createServiceInfo(id);
	}

}
