package com.example.racemania.view2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ConfirmationPageControllerG2{

    @FXML
    private Button homePageButton;

    @FXML
    private Button reservationsButton;

    @FXML
    private void ClickedOnReservationsButton(){
        FxmlLoader2.setPage("ReservationsPage2");
    }

    @FXML
    private void ClickedOnHomePageButton(){
        FxmlLoader2.setPage("HomePage2");
    }
}
