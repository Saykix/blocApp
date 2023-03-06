package controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MdpPage {
	
	public static Boolean admin = false;
	
	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		MdpPage.admin = admin;
	}
	
	String mdp = "AnnifWayan";
    @FXML
    private BorderPane borderPaneMdp;

    @FXML
    private Button boutonConfirmer;

    @FXML
    private PasswordField mdpText;

    @FXML
    void boutonConfirmerClick(MouseEvent event) {
    	if(mdpText.getText().equals(mdp)) {
    		admin = true;
    	    Node source = (Node) event.getSource();
    	    Stage stage = (Stage) source.getScene().getWindow();

    	    stage.close();
    	}else {
    		Alert alert = new Alert(AlertType.ERROR,"Le mot de passe est incorrect", ButtonType.OK);
			alert.showAndWait();
    	}
    }

}
