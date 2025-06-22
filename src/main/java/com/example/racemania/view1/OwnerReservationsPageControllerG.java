package com.example.racemania.view1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class OwnerReservationsPageControllerG {

    @FXML
    private Label ErrorLabel;

   @FXML
   private Label ErrorLabel2;

    @FXML
    private Button LapsReservationsButton;

    @FXML
    private Button SupercarReservationsButton;

    @FXML
    private Hyperlink HomePageHyperlink;

    @FXML
    private Hyperlink InstructorsHyperlink;

    @FXML
    public void ClickedOnLapsReservationsButton(ActionEvent event){
        FxmlLoader.setPage("OwnerLapsReservationsPage");
        // to be implemented
    }

    @FXML
    public void ClickedOnSupercarReservationsButton(ActionEvent event) {
        ErrorLabel2.setText("Not implemented sorry");
        ErrorLabel2.setVisible(true);
    }

    @FXML
    public void ClickedOnHomePageHyperlink(ActionEvent event) {
        FxmlLoader.setPage("OwnerHomePage");
    }

    @FXML
    public void ClickedOnInstructorsHyperlink(ActionEvent event) {
        ErrorLabel.setText("Not implemented sorry");
        ErrorLabel.setVisible(true);
    }

}
