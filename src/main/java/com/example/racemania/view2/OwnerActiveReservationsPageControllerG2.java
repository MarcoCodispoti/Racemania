package com.example.racemania.view2;

import com.example.racemania.controller.ManageLapsReservationsController;
import com.example.racemania.model.TrackLapsReservation;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class OwnerActiveReservationsPageControllerG2{
    private ManageLapsReservationsController manageLapsReservationsController = new ManageLapsReservationsController();

    @FXML
    private VBox ActiveReservationsVBox;

    @FXML
    private Hyperlink homePageHyperlink;

    @FXML
    private void ClickedOnHomePageHyperlink(){
        FxmlLoader2.setPage("OwnerHomePage2");
    }



    public void initialize(){
        TrackLapsReservationBean bean;
        List<TrackLapsReservation> trackLapsReservationList = null;

        try {
            bean = manageLapsReservationsController.getActiveOwnerLapsReservation();
            trackLapsReservationList = bean.getOwnerActiveTrackLapsReservations();
        }
        catch (Exception e) {
            // to be handled
        }

        if (trackLapsReservationList == null || trackLapsReservationList.isEmpty()) {
            System.out.println("Nessuna prenotazione trovata nel database.");
            return;
        }
        System.out.println("Caricati " + trackLapsReservationList.size() + " tracciati dal TrackBean.");

        populateActiveLapsReservations(trackLapsReservationList);
    }



    public void populateActiveLapsReservations(List<TrackLapsReservation> trackLapsReservatonList) {
        ActiveReservationsVBox.getChildren().clear();

        for (TrackLapsReservation trackLapsReservation : trackLapsReservatonList) {
            try {
                System.out.println("Sto caricando una LapsReservationCard per: " + trackLapsReservation.getReservationID());

                FXMLLoader cardloader = new FXMLLoader(getClass().getResource("/com/example/racemania/view2/OwnerActiveReservationCard2.fxml"));
                Parent ownerActiveReservationCard = cardloader.load();

                System.out.println("TrackCard.fxml caricata con successo");

                OwnerActiveReservationCardControllerG2 controller = cardloader.getController();
                controller.setData(trackLapsReservation);
                controller.setCardUI(ownerActiveReservationCard);
                controller.setParentController(this);

                ActiveReservationsVBox.getChildren().add(ownerActiveReservationCard);

            } catch (IOException e) {
                System.out.println("ERRORE nel caricamento TrackCard.fxml");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Errore generico:");
                e.printStackTrace();
            }
        }
    }




}
