package com.example.racemania.view1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class UserTypeControllerG{


    @FXML
    private Button TrackOwnerButton;

    @FXML
    private Button UserButton;

    @FXML
    void ClickedOnUserButton(ActionEvent event) {
        FxmlLoader.setPage("LoginPage");
    }

    @FXML
    void ClickedOnTrackOwner(ActionEvent event) {

    }

}
