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
import java.sql.SQLException;
import java.util.List;

public class OwnerLapsReservationsPageControllerG {
    private ManageLapsReservationsController manageLapsReservationsController = new ManageLapsReservationsController();
    private TrackLapsReservation selectedLapsReservation;
    private Parent selectedCardUI;

    @FXML
    private ScrollPane lapsReservationsScrollPane;

    @FXML
    private Label errorLabel;

    @FXML
    private VBox lapsReservationsVBox;

    @FXML
    private Button confirmButton;

    @FXML
    private Button rejectButton;

    @FXML
    private Button backButton;

    @FXML
    private Hyperlink reservationsHyperlink;

    @FXML
    private Hyperlink homepageHyperlink;

    @FXML
    public void clickedOnBackButton(ActionEvent event) {
        FxmlLoader.setPage("OwnerReservationsPage");
    }

    @FXML
    public void clickedOnConfirmButton(ActionEvent event) {
        if(!checkSelection(selectedLapsReservation)){
            return;
        }
        try {
            manageLapsReservationsController.manageLapsReservation(selectedLapsReservation.getReservationID(),"Accepted");
            initialize();
        } catch (SQLException _) {
            // not handled
        }
    }

    @FXML
    public void clickedOnRejectButton(ActionEvent event) {
        if(!checkSelection(selectedLapsReservation)){
            return;
        }
        try {
            manageLapsReservationsController.manageLapsReservation(selectedLapsReservation.getReservationID(),"Rejected");
            initialize();
        } catch (SQLException _) {
            // not handled
        }
    }

    @FXML
    public void clickedOnHomepageHyperlink(ActionEvent event) {
        FxmlLoader.setPage("OwnerHomePage");
    }

    public void clickedOnInstructorsHyperlink(ActionEvent event) {
        errorLabel.setText("Not implemented sorry");
        errorLabel.setVisible(true);
    }



    public void initialize() {
        TrackLapsReservationBean lapsReservationBean = new TrackLapsReservationBean();

        try {
            lapsReservationBean = manageLapsReservationsController.getOwnerLapsReservation();

        } catch (SQLException _) {
            // to be handled
        }

        populateLapsReservations(lapsReservationBean.getOwnerTrackLapsReservations());
    }

    public void setSelectedLapsReservation(TrackLapsReservation trackLapsReservation, Parent cardUI) {
        this.selectedLapsReservation = trackLapsReservation;

        // resetta evidenziazione su vecchia card (se c'è)
        if (selectedCardUI != null) {
            selectedCardUI.setStyle(""); // oppure stile di default
        }

        // evidenzia la nuova card selezionata
        selectedCardUI = cardUI;
        selectedCardUI.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10;");
    }


    public void populateLapsReservations(List<TrackLapsReservation> lapsReservationsList ) {
        lapsReservationsVBox.getChildren().clear();

        for (TrackLapsReservation trackLapsReservation : lapsReservationsList) {
            try {

                FXMLLoader cardloader = new FXMLLoader(getClass().getResource("/com/example/racemania/view1/OwnerLapsReservationCard.fxml"));
                Parent ownerLapsReservationCard = cardloader.load();

                System.out.println("TrackCard.fxml caricata con successo");

                OwnerLapsReservationCardControllerG controller = cardloader.getController();
                controller.setData(trackLapsReservation);
                controller.setCardUI(ownerLapsReservationCard);
                controller.setParentController(this);

                lapsReservationsVBox.getChildren().add(ownerLapsReservationCard);

            } catch (IOException _) {
                // to be handled
            } catch(Exception _) {
                // to be handled in another way
            }
        }
    }

    public boolean checkSelection(TrackLapsReservation trackLapsReservation){
        if(trackLapsReservation == null){
            errorLabel.setText("Seleziona una prenotazione prima");
            return false;
        }
        if(trackLapsReservation.getConfirmationStatus().equals("Rejected") || trackLapsReservation.getConfirmationStatus().equals("Accepted")){
            errorLabel.setText("La prenotazione è già stata accettata/rifiutata");
            return false;
        }
        return true;
    }

}
