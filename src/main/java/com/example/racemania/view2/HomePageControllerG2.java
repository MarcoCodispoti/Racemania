package com.example.racemania.view2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class HomePageControllerG2 {

    @FXML
    private Label errorLabel;

    @FXML
    private Button NewReservationButton;

    @FXML
    private Hyperlink CircuitsHyperlink;

    @FXML
    private Hyperlink ReservationsHyperlink;

    @FXML
    private Hyperlink ProfileHyperlink;

    @FXML
    private Button LogoutButton;

    @FXML
    private void ClickedOnLogoutButton() {
        FxmlLoader2.setPage("LoginPage2");
    }

    @FXML
    private void ClickedOnNewReservationButton(){
        FxmlLoader2.setPage("NewReservationPage2");
    }

    @FXML
    private void ClickedOnCircuitsHyperlink(ActionEvent event) {
        errorLabel.setText("Not implemented sorry");

    }

    @FXML
    private void ClickedOnProfileHyperlink(){
        FxmlLoader2.setPage("ProfilePage2");
    }

    @FXML
    private void ClickedOnReservationsHyperlink(){
        FxmlLoader2.setPage("ReservationsPage2");
    }

    
}
