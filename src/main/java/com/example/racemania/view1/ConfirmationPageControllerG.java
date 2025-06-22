package com.example.racemania.view1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import com.example.racemania.model.Track;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class ConfirmationPageControllerG {


    @FXML
    private Button HomepageButton;

    @FXML
    private Button ToReservations;

    @FXML
    public void ClickedOnHomepage(ActionEvent event) {
        FxmlLoader.setPage("HomePage");
    }

}
