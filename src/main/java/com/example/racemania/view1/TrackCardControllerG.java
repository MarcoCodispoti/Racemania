package com.example.racemania.view1;

import com.example.racemania.model.Track;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;

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
