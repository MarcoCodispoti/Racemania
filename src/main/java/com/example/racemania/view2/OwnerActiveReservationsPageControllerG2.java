package com.example.racemania.view2;

import com.example.racemania.controller.ManageLapsReservationsController;
import com.example.racemania.model.TrackLapsReservation;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class OwnerActiveReservationsPageControllerG2{
    private ManageLapsReservationsController manageLapsReservationsController = new ManageLapsReservationsController();

    @FXML
    private Label errorLabel;

    @FXML
    private VBox activeReservationsVBox;

    @FXML
    private Hyperlink homePageHyperlink;

    @FXML
    private void clickedOnHomePageHyperlink(){
        FxmlLoader2.setPage("OwnerHomePage2");
    }



    public void initialize(){
        TrackLapsReservationBean bean;
        List<TrackLapsReservation> trackLapsReservationList = null;

        try {
            bean = manageLapsReservationsController.getActiveOwnerLapsReservation();
            trackLapsReservationList = bean.getOwnerActiveTrackLapsReservations();
        }
        catch (Exception _) {
            // to be handled
        }

        if (trackLapsReservationList == null || trackLapsReservationList.isEmpty()) {
            errorLabel.setText("Nessuna prenotazione trovata nel database.");
            return;
        }

        populateActiveLapsReservations(trackLapsReservationList);
    }



    public void populateActiveLapsReservations(List<TrackLapsReservation> trackLapsReservatonList) {
        activeReservationsVBox.getChildren().clear();

        for (TrackLapsReservation trackLapsReservation : trackLapsReservatonList) {
            try {
                FXMLLoader cardloader = new FXMLLoader(getClass().getResource("/com/example/racemania/view2/OwnerActiveReservationCard2.fxml"));
                Parent ownerActiveReservationCard = cardloader.load();
                OwnerActiveReservationCardControllerG2 controller = cardloader.getController();
                controller.setData(trackLapsReservation);
                controller.setCardUI(ownerActiveReservationCard);
                controller.setParentController(this);

                activeReservationsVBox.getChildren().add(ownerActiveReservationCard);

            } catch (IOException _) {
                errorLabel.setText("Errore nel caricamento delle prenotazioni");
            } catch (Exception _) {
                errorLabel.setText("Errore di sistema");
            }
        }
    }




}
