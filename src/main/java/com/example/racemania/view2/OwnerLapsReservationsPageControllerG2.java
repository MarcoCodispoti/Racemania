package com.example.racemania.view2;


import com.example.racemania.controller.ManageLapsReservationsController;
import com.example.racemania.model.TrackLapsReservation;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import com.example.racemania.view1.FxmlLoader;
import com.example.racemania.view1.OwnerLapsReservationCardControllerG;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OwnerLapsReservationsPageControllerG2 {
    ManageLapsReservationsController manageLapsReservationsController = new ManageLapsReservationsController();
    private TrackLapsReservation selectedLapsReservation;
    private Parent selectedCardUI;

    @FXML
    private VBox LapsReservationsVBox;

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

    public void populateLapsReservations(List<TrackLapsReservation> lapsReservationsList) {
        LapsReservationsVBox.getChildren().clear();
        System.out.println("Numero di prenotazioni trovate: " + lapsReservationsList.size());
        for (TrackLapsReservation trackLapsReservation : lapsReservationsList) {
            try {

                FXMLLoader cardloader = new FXMLLoader(getClass().getResource("/com/example/racemania/view2/OwnerLapsReservationCard2.fxml"));
                System.out.println("Ho istanziato cardloader");
                System.out.println(cardloader);
                Parent ownerLapsReservationCard = cardloader.load();
                System.out.println("Card caricata: " + ownerLapsReservationCard);

                System.out.println("TrackCard.fxml caricata con successo");

                OwnerLapsReservationsCardControllerG2 controller = cardloader.getController();
                controller.setData(trackLapsReservation);
                controller.setCardUI(ownerLapsReservationCard);
                controller.setParentController(this);

                LapsReservationsVBox.getChildren().add(ownerLapsReservationCard);

            } catch (IOException e) {
                System.out.println("ERRORE nel caricamento TrackCard.fxml");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Porcaccio dio");
                System.out.println("Errore generico:");
                // e.printStackTrace();
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

    public void setSelectedLapsReservation(TrackLapsReservation trackLapsReservation, Parent cardUI) {
        this.selectedLapsReservation = trackLapsReservation;

        // resetta evidenziazione su vecchia card (se c'è)
        if (selectedCardUI != null) {
            selectedCardUI.setStyle(""); // oppure stile di default
        }

        // evidenzia la nuova card selezionata
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

    @FXML
    public void ClickedOnRejectButton(){
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

    @FXML
    public void ClickedOnHomePageHyperlink(){
        FxmlLoader2.setPage("OwnerHomePage2");
    }

}

