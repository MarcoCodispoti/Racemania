package com.example.racemania.view2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OwnerReservationsPageControllerG2{

    @FXML
    private Label errorLabel;

    @FXML
    private void ClickedOnLapsReservationsButton(ActionEvent event){
        FxmlLoader2.setPage("OwnerLapsReservationsPage2");
    }


    @FXML
    private void ClickedOnSupercarReservationsButton(ActionEvent event){
        errorLabel.setText("Not implemented sorry");
    }

    @FXML
    private void ClickedOnBackButton(ActionEvent event){
        FxmlLoader2.setPage("OwnerHomePage2");
    }


}
