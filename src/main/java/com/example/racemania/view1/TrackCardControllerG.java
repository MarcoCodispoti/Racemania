package com.example.racemania.view1;

import com.example.racemania.model.Track;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class TrackCardControllerG{
    private Track track;
    private TrackPageControllerG parentController;
    private Parent cardUI;

        @FXML
        private Label trackName;

        @FXML
        private Label trackLocation;

        @FXML
        private Label trackLength;

        @FXML
        private Label trackLapPrice;

        @FXML
        private Label trackDailyPrice;

        @FXML
        private void handleClick() {
            if (parentController != null) {
                parentController.setSelectedTrack(track, cardUI);
            }
        }

        public void setData(Track track) {
            this.track = track;
            trackName.setText(track.getName());
            trackLocation.setText(track.getPlace());
            trackLength.setText(track.getLenght()+" m");
            trackLapPrice.setText(track.getLapPrice()+" €");
            trackDailyPrice.setText(track.getDailyPrice()+" €");
        }

    public void setParentController(TrackPageControllerG controller) {
        this.parentController = controller;
    }


    public void setCardUI(Parent cardUI) {
        this.cardUI = cardUI;
    }
}
