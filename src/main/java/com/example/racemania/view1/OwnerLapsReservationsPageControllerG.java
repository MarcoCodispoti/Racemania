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
    private ScrollPane LapsReservationsScrollPane;

    @FXML
    private Label ErrorLabel;

    @FXML
    private VBox LapsReservationsVBox;

    @FXML
    private Button ConfirmButton;

    @FXML
    private Button RejectButton;

    @FXML
    private Button BackButton;

    @FXML
    private Hyperlink ReservationsHyperlink;

    @FXML
    private Hyperlink HomerpageHyperlink;

    @FXML
    public void ClickedOnBackButton(ActionEvent event) {
        FxmlLoader.setPage("OwnerReservationsPage");
    }

    public void ClickedOnConfirmButton(ActionEvent event) {
        if(!checkSelection(selectedLapsReservation)){
            return;
        }
        // Vado a sostituire la funzione
        // TrackLapsReservationBean trackLapsReservationBean = new TrackLapsReservationBean();
        try {
            manageLapsReservationsController.manageLapsReservation(selectedLapsReservation.getReservationID(),"Accepted");
            // trackLapsReservationBean.manageLapsReservation(selectedLapsReservation.getReservationID(), "Rejected");
            initialize();
        } catch (SQLException ex) {
            // Errore SQL
        }
    }

    public void ClickedOnRejectButton(ActionEvent event) {
        if(!checkSelection(selectedLapsReservation)){
            return;
        }
        // Vado a sostituire la funzione
        // TrackLapsReservationBean trackLapsReservationBean = new TrackLapsReservationBean();
        try {
            manageLapsReservationsController.manageLapsReservation(selectedLapsReservation.getReservationID(),"Rejected");
            // trackLapsReservationBean.manageLapsReservation(selectedLapsReservation.getReservationID(), "Rejected");
            initialize();
        } catch (SQLException ex) {
            // Errore SQL
        }
    }

    public void ClickedOnHomepageHyperlink(ActionEvent event) {
        FxmlLoader.setPage("OwnerHomePage");
    }

    public void ClickedOnInstructorsHyperlink(ActionEvent event) {
        ErrorLabel.setText("Not implemented sorry");
        ErrorLabel.setVisible(true);
    }



    public void initialize() {
        TrackLapsReservationBean lapsReservationBean;
        // List<TrackLapsReservation> lapsReservationsList;
        System.out.println("Inizializzo la pagina delle prenotazioni \n \n");

        try {
            lapsReservationBean = manageLapsReservationsController.getOwnerLapsReservation();

        } catch (SQLException e) {
            System.out.println("Errore nel caricamento delle prenotazioni effettuate");
            throw new RuntimeException(e);
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
        LapsReservationsVBox.getChildren().clear();

        for (TrackLapsReservation trackLapsReservation : lapsReservationsList) {
            try {

                FXMLLoader cardloader = new FXMLLoader(getClass().getResource("/com/example/racemania/view1/OwnerLapsReservationCard.fxml"));
                Parent ownerLapsReservationCard = cardloader.load();

                System.out.println("TrackCard.fxml caricata con successo");

                OwnerLapsReservationCardControllerG controller = cardloader.getController();
                controller.setData(trackLapsReservation);
                controller.setCardUI(ownerLapsReservationCard);
                controller.setParentController(this);

                LapsReservationsVBox.getChildren().add(ownerLapsReservationCard);

            } catch (IOException e) {
                System.out.println("ERRORE nel caricamento TrackCard.fxml");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Errore generico:");
                e.printStackTrace();
            }
        }
    }

    public boolean checkSelection(TrackLapsReservation trackLapsReservation){
        if(trackLapsReservation == null){
            System.out.println("Seleziona una prenotazione prima");
            return false;
        }
        if(trackLapsReservation.getConfirmationStatus().equals("Rejected") || trackLapsReservation.getConfirmationStatus().equals("Accepted")){
            System.out.println("La prenotazione è già stata accettata/rifiutata");
            return false;
        }
        return true;
    }

}
