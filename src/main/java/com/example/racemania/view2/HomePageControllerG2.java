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
    private Button newReservationButton;

    @FXML
    private Hyperlink circuitsHyperlink;

    @FXML
    private Hyperlink reservationsHyperlink;

    @FXML
    private Hyperlink profileHyperlink;

    @FXML
    private Button logoutButton;

    @FXML
    private void clickedOnLogoutButton() {
        FxmlLoader2.setPage("LoginPage2");
    }

    @FXML
    private void clickedOnNewReservationButton(){
        FxmlLoader2.setPage("NewReservationPage2");
    }

    @FXML
    private void clickedOnCircuitsHyperlink(ActionEvent event) {
        errorLabel.setText("Not implemented sorry");

    }

    @FXML
    private void clickedOnProfileHyperlink(){
        FxmlLoader2.setPage("ProfilePage2");
    }

    @FXML
    private void clickedOnReservationsHyperlink(){
        FxmlLoader2.setPage("ReservationsPage2");
    }

    
}
