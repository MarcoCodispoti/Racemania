package com.example.racemania.view2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ConfirmationPageControllerG2{

    @FXML
    private Button homePageButton;

    @FXML
    private Button reservationsButton;

    @FXML
    private void clickedOnReservationsButton(){
        FxmlLoader2.setPage("ReservationsPage2");
    }

    @FXML
    private void clickedOnHomePageButton(){
        FxmlLoader2.setPage("HomePage2");
    }
}
