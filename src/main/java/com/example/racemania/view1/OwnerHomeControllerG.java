package com.example.racemania.view1;

import com.example.racemania.controller.ManageLapsReservationsController;
import com.example.racemania.model.TrackLapsReservation;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class OwnerHomeControllerG {
    private ManageLapsReservationsController manageLapsReservationsController = new ManageLapsReservationsController();

    @FXML
    private Hyperlink logoutHyperlink;

    @FXML
    private ScrollPane activeReservationsScrollPane;

    @FXML
    private VBox activeReservationsVBox;

    @FXML
    private Hyperlink reservationsHyperlink;

    @FXML
    private Hyperlink instructorsHyperlink;

    @FXML
    private Label errorLabel;

    @FXML
    private Hyperlink profileHyperlink;

    @FXML
    private void clickedOnProfileHyperlink(ActionEvent event) {
        FxmlLoader.setPage("OwnerProfilePage");
    }

    @FXML
    private void clickedOnLogoutHyperlink(ActionEvent event){
        FxmlLoader.setPage("LoginPage");
    }

    @FXML
    public void clickedOnReservationsHyperlink(ActionEvent event){
        FxmlLoader.setPage("OwnerReservationsPage");
    }

    @FXML
    public void clickedOnInstructorsHyperlink(ActionEvent event){
        errorLabel.setText("Not implemented sorry");
        errorLabel.setVisible(true);
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
            // to be handled
            return; // nessuna prenotazione trovata
        }

        populateActiveLapsReservations(trackLapsReservationList);
    }

    public void populateActiveLapsReservations(List<TrackLapsReservation> trackLapsReservatonList) {
        activeReservationsVBox.getChildren().clear();

        for (TrackLapsReservation trackLapsReservation : trackLapsReservatonList) {
            try {
                FXMLLoader cardloader = new FXMLLoader(getClass().getResource("/com/example/racemania/view1/OwnerActiveReservationCard.fxml"));
                Parent ownerActiveReservationCard = cardloader.load();

                OwnerActiveReservationCardControllerG controller = cardloader.getController();
                controller.setData(trackLapsReservation);
                controller.setCardUI(ownerActiveReservationCard);
                controller.setParentController(this);

                activeReservationsVBox.getChildren().add(ownerActiveReservationCard);

            } catch (IOException _) {
                //to be handled // errore nel caricamento della track_card
            } catch (Exception _) {
                //to be handled // errore generico
            }
        }
    }


}
