package com.example.racemania.view1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
public class ConfirmationPageControllerG {


    @FXML
    private Button homepageButton;

    @FXML
    private Button toReservations;

    @FXML
    public void ClickedOnHomepage(ActionEvent event) {
        FxmlLoader.setPage("HomePage");
    }

}
