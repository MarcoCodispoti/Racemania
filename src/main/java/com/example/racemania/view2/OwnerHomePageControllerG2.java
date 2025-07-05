package com.example.racemania.view2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class OwnerHomePageControllerG2 {

    @FXML
    private Label errorLabel;

    @FXML
    private Hyperlink instructorsHyperlink;

    @FXML
    private Hyperlink profileHyperlink;

    @FXML
    private Hyperlink logoutHyperlink;

    @FXML
    private Button manageReservationsButton;

    @FXML
    private Button activeReservations;

    @FXML
    private void clickedOnInstructorsHyperlink(ActionEvent event) {
        errorLabel.setText("Not implemented yet");
    }

    @FXML
    private void clickedOnProfileHyperlink(ActionEvent event){
        FxmlLoader2.setPage("OwnerProfilePage2");
    }

    @FXML
    private void clickedOnLogoutHyperlink(ActionEvent event){
        FxmlLoader2.setPage("LoginPage2");
    }

    @FXML
    private void clickedOnManageReservationsButton(ActionEvent event){
        FxmlLoader2.setPage("OwnerReservationsPage2");
    }

    @FXML
    private void clickedOnActiveReservationsButton(ActionEvent event){
        FxmlLoader2.setPage("OwnerActiveReservationsPage2");
    }


}
