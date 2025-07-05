package com.example.racemania.view2;


import com.example.racemania.controller.ManageLapsReservationsController;
import com.example.racemania.model.TrackLapsReservation;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OwnerLapsReservationsPageControllerG2 {
    ManageLapsReservationsController manageLapsReservationsController = new ManageLapsReservationsController();
    private TrackLapsReservation selectedLapsReservation;
    private Parent selectedCardUI;

    @FXML
    private Label errorLabel;

    @FXML
    private VBox lapsReservationsVBox;

    public void initialize() {
        TrackLapsReservationBean lapsReservationBean;
        try {
            lapsReservationBean = manageLapsReservationsController.getOwnerLapsReservation();

        } catch (SQLException e) {
            errorLabel.setText("Reservations loading error");
            throw new RuntimeException(e);
        }

        populateLapsReservations(lapsReservationBean.getOwnerTrackLapsReservations());
    }

    public void populateLapsReservations(List<TrackLapsReservation> lapsReservationsList) {
        lapsReservationsVBox.getChildren().clear();
        for (TrackLapsReservation trackLapsReservation : lapsReservationsList) {
            try {

                FXMLLoader cardloader = new FXMLLoader(getClass().getResource("/com/example/racemania/view2/OwnerLapsReservationCard2.fxml"));
                Parent ownerLapsReservationCard = cardloader.load();

                OwnerLapsReservationsCardControllerG2 controller = cardloader.getController();
                controller.setData(trackLapsReservation);
                controller.setCardUI(ownerLapsReservationCard);
                controller.setParentController(this);

                lapsReservationsVBox.getChildren().add(ownerLapsReservationCard);

            } catch (IOException _) {
                errorLabel.setText("Loading error");
            } catch (Exception _) {
                errorLabel.setText("Generic error");
            }
        }
    }

    public boolean checkSelection(TrackLapsReservation trackLapsReservation){
        if(trackLapsReservation == null){
            errorLabel.setText("Please select a reservation first");
            return false;
        }
        if(trackLapsReservation.getConfirmationStatus().equals("Rejected") || trackLapsReservation.getConfirmationStatus().equals("Accepted")){
            System.out.println("La prenotazione è già stata accettata/rifiutata");
            errorLabel.setText("Reservation already accepted/rejected");
            return false;
        }
        return true;
    }

    public void setSelectedLapsReservation(TrackLapsReservation trackLapsReservation, Parent cardUI) {
        this.selectedLapsReservation = trackLapsReservation;

        if (selectedCardUI != null) {
            selectedCardUI.setStyle(""); // oppure stile di default
        }
        selectedCardUI = cardUI;
        selectedCardUI.setStyle("-fx-border-color: #3d3e80; -fx-border-width: 3; -fx-border-radius: 10;");
    }

    @FXML
    public void ClickedOnBackButton() {
        FxmlLoader2.setPage("OwnerHomePage2");
    }

    @FXML
    public void ClickedOnConfirmButton() {
        if(!checkSelection(selectedLapsReservation)){
            return;
        }
        try {
            manageLapsReservationsController.manageLapsReservation(selectedLapsReservation.getReservationID(),"Accepted");
            initialize();
        } catch (SQLException _) {
            errorLabel.setText("An error occured");
        }
    }

    @FXML
    public void ClickedOnRejectButton(){
        if(!checkSelection(selectedLapsReservation)){
            return;
        }
        try {
            manageLapsReservationsController.manageLapsReservation(selectedLapsReservation.getReservationID(),"Rejected");
            initialize();
        } catch (SQLException _) {
            errorLabel.setText("An error occured");
        }
    }

    @FXML
    public void ClickedOnHomePageHyperlink(){
        FxmlLoader2.setPage("OwnerHomePage2");
    }

}

