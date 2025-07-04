package com.example.racemania.view2;


import com.example.racemania.controller.BookLapsReservationController;
import com.example.racemania.exceptions.FailedResearchException;
import com.example.racemania.model.Track;
import com.example.racemania.model.bean.TrackBean;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.List;

public class NewReservationPageControllerG2 {
    private BookLapsReservationController bookLapsReservationController = new BookLapsReservationController();
    private TrackLapsReservationBean actualLapsReservationBean;
    private boolean lapsReservation;
    private boolean supercarReservation;
    private Track selectedTrack;

    @FXML
    private Label errorLabel;

    @FXML
    private DatePicker reservationDatePicker;

    @FXML
    private RadioButton trackLapsRadioButton;

    @FXML
    private RadioButton supercarLapsRadioButton;

    @FXML
    private ChoiceBox<Track> trackChoiceBox;

    @FXML
    private Button backButton;

    @FXML
    private Button proceedButton;

    @FXML
    private void clickedOnBackButton(){
        FxmlLoader2.setPage("HomePage2");
    }

    @FXML
    private void clickedOnProceedButton(){
        if(handleDateSelection()) {
            if(supercarLapsRadioButton.isSelected()){
                errorLabel.setText("Supercar laps not available yet, Sorry");
                return;
            }

            selectedTrack = trackChoiceBox.getValue();
            if (selectedTrack == null) {
                errorLabel.setText("Please select a track");
                return;
            }

            actualLapsReservationBean  = new TrackLapsReservationBean();

            actualLapsReservationBean.setTrackID(selectedTrack.getTrackId());

            actualLapsReservationBean.setDate(reservationDatePicker.getValue());
            bookLapsReservationController.saveTrackDetails(selectedTrack,actualLapsReservationBean);

            NewReservationDetailsPageControllerG2 controller = FxmlLoader2.setPageAndReturnController("NewReservationDetailsPage2");
            controller.setTrackLapsReservationBean(actualLapsReservationBean);
        }
    }

    @FXML
    private void clickedOnTrackLapsRadioButton(){
        lapsReservation = trackLapsRadioButton.isSelected();
        supercarReservation = supercarLapsRadioButton.isSelected();
    }

    @FXML
    private void clickedOnSupercarLapsRadioButton(){
        lapsReservation = trackLapsRadioButton.isSelected();
        supercarReservation = supercarLapsRadioButton.isSelected();
    }

    private boolean handleDateSelection(){
        LocalDate selectedDate;
        selectedDate = reservationDatePicker.getValue();
        if(selectedDate == null){
            errorLabel.setText("Please select a date");
            return false;
        }
        return true;
    }


    @FXML
    private void initialize(){
        List<Track> tracks = null;
        try {
            TrackBean trackBean = bookLapsReservationController.getAvailableTracks();
            tracks = trackBean.getAvailableTracks();
        } catch (FailedResearchException _){
            // to be handled
        }

        if (tracks != null) {
            trackChoiceBox.getItems().addAll(tracks);
        }

        trackChoiceBox.setConverter(new StringConverter<Track>() {
            @Override
            public String toString(Track track) {
                if (track == null) return "";
                return track.getName() + " - " + track.getLenght() + " m - " + track.getPlace() + " \nGiro: € " + track.getLapPrice() + ",  Giornaliero: € " + track.getDailyPrice();
            }

            @Override
            public Track fromString(String string) {
                return null; // non serve, ChoiceBox non lo usa
            }
        });


    }



}
