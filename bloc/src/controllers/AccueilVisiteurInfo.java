package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;

import Class.employer;
import application.apiRequest;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

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
	    private TextField searchBar;
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
	    private ChoiceBox<String> triServiceBox;

	    @FXML
	    private ChoiceBox<String> triSiteBox;
	    
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
	    	if(MdpPage.admin) {
	    		try {
	    			fxml = FXMLLoader.load(getClass().getResource("/interfaces/ServiceAdmin.fxml"));
	    			borderPanevisiteurInfo.getChildren().removeAll();
	    			borderPanevisiteurInfo.getChildren().setAll(fxml);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		} 

	    	}else {	    		
	    		try {
	    			fxml = FXMLLoader.load(getClass().getResource("/interfaces/ServiceVisiteur.fxml"));
	    			borderPanevisiteurInfo.getChildren().removeAll();
	    			borderPanevisiteurInfo.getChildren().setAll(fxml);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		} 
	    	}
	    }

	    @FXML
	    void boutonSitesClick(MouseEvent event) {
	    	if(MdpPage.admin) {
	    		try {
	    			fxml = FXMLLoader.load(getClass().getResource("/interfaces/SiteAdmin.fxml"));
	    			borderPanevisiteurInfo.getChildren().removeAll();
	    			borderPanevisiteurInfo.getChildren().setAll(fxml);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		} 

	    	}else {	    		
	    		try {
	    			fxml = FXMLLoader.load(getClass().getResource("/interfaces/SiteVisiteur.fxml"));
	    			borderPanevisiteurInfo.getChildren().removeAll();
	    			borderPanevisiteurInfo.getChildren().setAll(fxml);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		} 
	    	}
	    }
	    
	    @FXML
	    void closeInfoClick(MouseEvent event) {
	    	if(MdpPage.admin) {
	    		try {
	    			fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilAdmin.fxml"));
	    			borderPanevisiteurInfo.getChildren().removeAll();
	    			borderPanevisiteurInfo.getChildren().setAll(fxml);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		} 

	    	}else {	    		
	    		try {
	    			fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilVisiteur.fxml"));
	    			borderPanevisiteurInfo.getChildren().removeAll();
	    			borderPanevisiteurInfo.getChildren().setAll(fxml);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		} 
	    	}
	    }
	    
	    @FXML
	    void searchBarKey(KeyEvent event) {
	    	createTableau(triSiteBox.getValue(),triServiceBox.getValue(),searchBar.getText());
	    }
	    public static ObservableList<employer> getDataEmployer(String site, String service, String nom){
			if(site.equals("Site") && service.equals("Service") && nom.equals("")) {				
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
			}else if(!site.equals("Site") && service.equals("Service") && nom.equals("")){
				JSONArray employes = apiRequest.employerGet();
				ObservableList<employer> list = FXCollections.observableArrayList();
				
				for(int i = 0; i < employes.length(); i++) {
					JSONArray employerSite = apiRequest.siteGetById(employes.getJSONObject(i).getInt("employerSite"));
					JSONArray employerService = apiRequest.serviceGetById(employes.getJSONObject(i).getInt("employerService"));
					if(employerSite.getJSONObject(0).getString("ville").equals(site)) {						
						list.add(new employer(employes.getJSONObject(i).getInt("IdEmployer"), employes.getJSONObject(i).getString("nom"),
								employes.getJSONObject(i).getString("prenom"), employes.getJSONObject(i).getString("fixe"),
								employes.getJSONObject(i).getString("portable"),employes.getJSONObject(i).getString("email"),
								employerService.getJSONObject(0).getString("nomService"), employerSite.getJSONObject(0).getString("ville")
								));
					}
				}
				
				return list;
			}else if(site.equals("Site") && !service.equals("Service") && nom.equals("")){
				JSONArray employes = apiRequest.employerGet();
				ObservableList<employer> list = FXCollections.observableArrayList();
				
				for(int i = 0; i < employes.length(); i++) {
					JSONArray employerSite = apiRequest.siteGetById(employes.getJSONObject(i).getInt("employerSite"));
					JSONArray employerService = apiRequest.serviceGetById(employes.getJSONObject(i).getInt("employerService"));
					if(employerService.getJSONObject(0).getString("nomService").equals(service)) {						
						list.add(new employer(employes.getJSONObject(i).getInt("IdEmployer"), employes.getJSONObject(i).getString("nom"),
								employes.getJSONObject(i).getString("prenom"), employes.getJSONObject(i).getString("fixe"),
								employes.getJSONObject(i).getString("portable"),employes.getJSONObject(i).getString("email"),
								employerService.getJSONObject(0).getString("nomService"), employerSite.getJSONObject(0).getString("ville")
								));
					}
				}
				
				return list;
			}else if(!site.equals("Site") && !service.equals("Service") && nom.equals("")){
				JSONArray employes = apiRequest.employerGet();
				ObservableList<employer> list = FXCollections.observableArrayList();
				
				for(int i = 0; i < employes.length(); i++) {
					JSONArray employerSite = apiRequest.siteGetById(employes.getJSONObject(i).getInt("employerSite"));
					JSONArray employerService = apiRequest.serviceGetById(employes.getJSONObject(i).getInt("employerService"));
					if(employerService.getJSONObject(0).getString("nomService").equals(service) && employerSite.getJSONObject(0).getString("ville").equals(site)) {						
						list.add(new employer(employes.getJSONObject(i).getInt("IdEmployer"), employes.getJSONObject(i).getString("nom"),
								employes.getJSONObject(i).getString("prenom"), employes.getJSONObject(i).getString("fixe"),
								employes.getJSONObject(i).getString("portable"),employes.getJSONObject(i).getString("email"),
								employerService.getJSONObject(0).getString("nomService"), employerSite.getJSONObject(0).getString("ville")
								));
					}
				}
				
				return list;
			}else if(site.equals("Site") && service.equals("Service") && !nom.equals("")){
				JSONArray employes = apiRequest.employerGet();
				ObservableList<employer> list = FXCollections.observableArrayList();
				
				for(int i = 0; i < employes.length(); i++) {
					JSONArray employerSite = apiRequest.siteGetById(employes.getJSONObject(i).getInt("employerSite"));
					JSONArray employerService = apiRequest.serviceGetById(employes.getJSONObject(i).getInt("employerService"));
					if(employes.getJSONObject(i).getString("nom").toLowerCase().contains(nom.toLowerCase())) {						
						list.add(new employer(employes.getJSONObject(i).getInt("IdEmployer"), employes.getJSONObject(i).getString("nom"),
								employes.getJSONObject(i).getString("prenom"), employes.getJSONObject(i).getString("fixe"),
								employes.getJSONObject(i).getString("portable"),employes.getJSONObject(i).getString("email"),
								employerService.getJSONObject(0).getString("nomService"), employerSite.getJSONObject(0).getString("ville")
								));
					}
				}
				
				return list;
			}else if(!site.equals("Site") && service.equals("Service") && !nom.equals("")){
				JSONArray employes = apiRequest.employerGet();
				ObservableList<employer> list = FXCollections.observableArrayList();
				
				for(int i = 0; i < employes.length(); i++) {
					JSONArray employerSite = apiRequest.siteGetById(employes.getJSONObject(i).getInt("employerSite"));
					JSONArray employerService = apiRequest.serviceGetById(employes.getJSONObject(i).getInt("employerService"));
					if(employes.getJSONObject(i).getString("nom").toLowerCase().contains(nom.toLowerCase()) && employerSite.getJSONObject(0).getString("ville").equals(site)) {						
						list.add(new employer(employes.getJSONObject(i).getInt("IdEmployer"), employes.getJSONObject(i).getString("nom"),
								employes.getJSONObject(i).getString("prenom"), employes.getJSONObject(i).getString("fixe"),
								employes.getJSONObject(i).getString("portable"),employes.getJSONObject(i).getString("email"),
								employerService.getJSONObject(0).getString("nomService"), employerSite.getJSONObject(0).getString("ville")
								));
					}
				}
				
				return list;
			}else if(site.equals("Site") && !service.equals("Service") && !nom.equals("")){
				JSONArray employes = apiRequest.employerGet();
				ObservableList<employer> list = FXCollections.observableArrayList();
				
				for(int i = 0; i < employes.length(); i++) {
					JSONArray employerSite = apiRequest.siteGetById(employes.getJSONObject(i).getInt("employerSite"));
					JSONArray employerService = apiRequest.serviceGetById(employes.getJSONObject(i).getInt("employerService"));
					if(employes.getJSONObject(i).getString("nom").toLowerCase().contains(nom.toLowerCase()) && employerService.getJSONObject(0).getString("nomService").equals(service)) {						
						list.add(new employer(employes.getJSONObject(i).getInt("IdEmployer"), employes.getJSONObject(i).getString("nom"),
								employes.getJSONObject(i).getString("prenom"), employes.getJSONObject(i).getString("fixe"),
								employes.getJSONObject(i).getString("portable"),employes.getJSONObject(i).getString("email"),
								employerService.getJSONObject(0).getString("nomService"), employerSite.getJSONObject(0).getString("ville")
								));
					}
				}
				
				return list;
			}else if(!site.equals("Site") && !service.equals("Service") && !nom.equals("")){
				JSONArray employes = apiRequest.employerGet();
				ObservableList<employer> list = FXCollections.observableArrayList();
				
				for(int i = 0; i < employes.length(); i++) {
					JSONArray employerSite = apiRequest.siteGetById(employes.getJSONObject(i).getInt("employerSite"));
					JSONArray employerService = apiRequest.serviceGetById(employes.getJSONObject(i).getInt("employerService"));
					if(employes.getJSONObject(i).getString("nom").toLowerCase().contains(nom.toLowerCase()) && employerService.getJSONObject(0).getString("nomService").equals(service)&& employerSite.getJSONObject(0).getString("ville").equals(site)) {						
						list.add(new employer(employes.getJSONObject(i).getInt("IdEmployer"), employes.getJSONObject(i).getString("nom"),
								employes.getJSONObject(i).getString("prenom"), employes.getJSONObject(i).getString("fixe"),
								employes.getJSONObject(i).getString("portable"),employes.getJSONObject(i).getString("email"),
								employerService.getJSONObject(0).getString("nomService"), employerSite.getJSONObject(0).getString("ville")
								));
					}
				}
				
				return list;
			}
			
			return null;
			
		}
		
		
		public void createEmployerInfo(int id) {
			JSONArray employes = apiRequest.employerGetById(id);
			JSONArray employerSite = apiRequest.siteGetById(employes.getJSONObject(0).getInt("employerSite"));
			JSONArray employerService = apiRequest.serviceGetById(employes.getJSONObject(0).getInt("employerService"));
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
		
		
		public void createTableau(String site, String service, String nom) {
			tableEmployerNom.setCellValueFactory(new PropertyValueFactory<employer,String>("nom"));
			tableEmployerPrenom.setCellValueFactory(new PropertyValueFactory<employer,String>("prenom"));
			tableEmployerFixe.setCellValueFactory(new PropertyValueFactory<employer,String>("fixe"));
			tableEmployerPortable.setCellValueFactory(new PropertyValueFactory<employer,String>("portable"));
			tableEmployerEmail.setCellValueFactory(new PropertyValueFactory<employer,String>("email"));
			tableEmployerService.setCellValueFactory(new PropertyValueFactory<employer,String>("employerService"));
			tableEmployerSite.setCellValueFactory(new PropertyValueFactory<employer,String>("employerSite"));
			
			tableEmployer.setItems(getDataEmployer(site,service,nom));
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
		public void createList() {
			List<String> listSite = new ArrayList<>();
			listSite.add("Site");
			JSONArray sites = apiRequest.siteGet();
			for(int i = 0; i < sites.length(); i++) {
				listSite.add(sites.getJSONObject(i).getString("ville"));
			}
			triSiteBox.getItems().addAll(listSite);
			List<String> listService = new ArrayList<>();
			listService.add("Service");
			JSONArray services = apiRequest.serviceGet();
			for(int i = 0; i < services.length(); i++) {
				listService.add(services.getJSONObject(i).getString("nomService"));
			}
			triServiceBox.getItems().addAll(listService);
			triSiteBox.setValue("Site");
			triServiceBox.setValue("Service");
			triServiceBox.getSelectionModel().selectedItemProperty().addListener(
					(ObservableValue<? extends String> observable, String oldValue, String newValue) ->
					createTableau(triSiteBox.getValue(),newValue, searchBar.getText()) );
			
			triSiteBox.getSelectionModel().selectedItemProperty().addListener(
					(ObservableValue<? extends String> observable, String oldValue, String newValue) ->
					createTableau(newValue,triServiceBox.getValue(), searchBar.getText()) );
		}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		createTableau("Site","Service","");
		createList();
	}
	
	public void setData(int id) {
		createEmployerInfo(id);
	}

}
