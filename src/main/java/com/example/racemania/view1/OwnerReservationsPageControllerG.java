package com.example.racemania.view1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class OwnerReservationsPageControllerG {

    @FXML
    private Label errorLabel;

   @FXML
   private Label errorLabel2;

    @FXML
    private Button lapsReservationsButton;

    @FXML
    private Button supercarReservationsButton;

    @FXML
    private Hyperlink homePageHyperlink;

    @FXML
    private Hyperlink instructorsHyperlink;

    @FXML
    public void clickedOnLapsReservationsButton(ActionEvent event){
        FxmlLoader.setPage("OwnerLapsReservationsPage");
        // to be implemented
    }

    @FXML
    public void clickedOnSupercarReservationsButton(ActionEvent event) {
        errorLabel2.setText("Not implemented sorry");
        errorLabel2.setVisible(true);
    }

    @FXML
    public void clickedOnHomePageHyperlink(ActionEvent event) {
        FxmlLoader.setPage("OwnerHomePage");
    }

    @FXML
    public void clickedOnInstructorsHyperlink(ActionEvent event) {
        errorLabel.setText("Not implemented sorry");
        errorLabel.setVisible(true);
    }

}
