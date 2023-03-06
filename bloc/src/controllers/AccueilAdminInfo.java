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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class AccueilAdminInfo implements Initializable{

		private Parent fxml;

	    @FXML
	    private Button BoutonModifier;

	    @FXML
	    private Button BoutonSupprimer;

	    @FXML
	    private BorderPane borderPaneAdminInfo;

	    @FXML
	    private Button boutonAjouterEmployer;

	    @FXML
	    private Button boutonEmployer;

	    @FXML
	    private Button boutonService;

	    @FXML
	    private Button boutonSites;

	    @FXML
	    private ImageView closeInfo;

	    @FXML
	    private TextField email;

	    @FXML
	    private TextField fixe;

	    @FXML
	    private TextField nom;

	    @FXML
	    private TextField portable;

	    @FXML
	    private TextField prenom;

	    @FXML
	    private TextField service;

	    @FXML
	    private TextField site;

	    
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

	    int id;
	    @FXML
	    void BoutonModifierClick(MouseEvent event) {
	    	JSONArray Service = apiRequest.serviceGetByName(service.getText());
	    	JSONArray Site = apiRequest.siteGetByVille(site.getText());
	    	System.out.println(Service.getJSONObject(0).getInt("IdService"));
	    	System.out.println(Site.getJSONObject(0).getInt("IdSite"));
	    	employer employer = new employer(nom.getText(),prenom.getText(),fixe.getText(),portable.getText(),email.getText(),Service.getJSONObject(0).getInt("IdService"), Site.getJSONObject(0).getInt("IdSite"));
	    	try {
				apiRequest.employerPut(id, employer);
				createTableau();
				createEmployerInfo(id);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void BoutonSupprimerClick(MouseEvent event) {
	    	apiRequest.employerDelete(id);
	    	try {
	            fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilAdmin.fxml"));
	            borderPaneAdminInfo.getChildren().removeAll();
	            borderPaneAdminInfo.getChildren().setAll(fxml);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	    }

	    @FXML
	    void boutonAjouterEmployerClick(MouseEvent event) {
	    	try {
				fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilAdminAjouter.fxml"));
				borderPaneAdminInfo.getChildren().removeAll();
				borderPaneAdminInfo.getChildren().setAll(fxml);
			} catch (IOException e) {
				e.printStackTrace();
			} 
	    }

	    @FXML
	    void boutonEmployerClick(MouseEvent event) {
	    	try {
				fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilAdminAjouter.fxml"));
				borderPaneAdminInfo.getChildren().removeAll();
				borderPaneAdminInfo.getChildren().setAll(fxml);
			} catch (IOException e) {
				e.printStackTrace();
			} 
	    }

	    @FXML
	    void boutonServiceClick(MouseEvent event) {
	    	try {
	            fxml = FXMLLoader.load(getClass().getResource("/interfaces/ServiceAdmin.fxml"));
	            borderPaneAdminInfo.getChildren().removeAll();
	            borderPaneAdminInfo.getChildren().setAll(fxml);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	    }

	    @FXML
	    void boutonSitesClick(MouseEvent event) {
	    	try {
	            fxml = FXMLLoader.load(getClass().getResource("/interfaces/SiteAdmin.fxml"));
	            borderPaneAdminInfo.getChildren().removeAll();
	            borderPaneAdminInfo.getChildren().setAll(fxml);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	    }
	    
	    @FXML
	    void closeInfoClick(MouseEvent event) {
	    	try {
	            fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilAdmin.fxml"));
	            borderPaneAdminInfo.getChildren().removeAll();
	            borderPaneAdminInfo.getChildren().setAll(fxml);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	    }
	    
	    @FXML
	    void boutonAjouterClick(MouseEvent event) {
	    	try {
	            fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilAdminAjouter.fxml"));
	            borderPaneAdminInfo.getChildren().removeAll();
	            borderPaneAdminInfo.getChildren().setAll(fxml);
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
		
		
		public void createEmployerInfo(int id) {
			JSONArray employes = apiRequest.employerGetById(id);
			JSONArray employerSite = apiRequest.siteGetById(employes.getJSONObject(0).getInt("employerSite"));
			JSONArray employerService = apiRequest.serviceGetById(employes.getJSONObject(0).getInt("employerService"));
			 Platform.runLater(() -> {				 
				 nom.setText(employes.getJSONObject(0).getString("nom"));
				 prenom.setText(employes.getJSONObject(0).getString("prenom"));
				 fixe.setText(employes.getJSONObject(0).getString("fixe"));
				 portable.setText(employes.getJSONObject(0).getString("portable"));
				 email.setText(employes.getJSONObject(0).getString("email"));
				 service.setText(employerService.getJSONObject(0).getString("nomService"));
				 site.setText(employerSite.getJSONObject(0).getString("ville"));
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
		createTableau();
	}
	
	public void setData(int id) {
		this.id=id;
		createEmployerInfo(id);
	}

}
