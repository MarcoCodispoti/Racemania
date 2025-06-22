package com.example.racemania.view2;

import com.example.racemania.model.LoggedUser;
import com.example.racemania.model.TrackLapsReservation;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import com.example.racemania.view1.TrackLapsReservationCardControllerG;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReservationsPageControllerG2{

    @FXML
    private Label errorLabel;

    @FXML
    private VBox LapsReservationsVBox;

    @FXML
    private void ClickedOnCircuitsHyperlink(){
        errorLabel.setText("Not implemented yet");
    }

    @FXML
    private void ClickedOnHomePageHyperlink(){
        FxmlLoader2.setPage("HomePage2");
    }




    public void populateLapsReservations(List<TrackLapsReservation> lapsReservationsList ) {
        LapsReservationsVBox.getChildren().clear();

        for (TrackLapsReservation trackLapsReservation : lapsReservationsList) {
            try {
                System.out.println("Sto caricando una TrackCard per: " + trackLapsReservation.getReservationID());

                FXMLLoader cardloader = new FXMLLoader(getClass().getResource("/com/example/racemania/view2/TrackLapsReservationCard2.fxml"));
                Parent trackLapsReservationCard = cardloader.load();

                System.out.println("TrackCard.fxml caricata con successo");

                TrackLapsReservationCardControllerG2 controller = cardloader.getController();
                controller.setData(trackLapsReservation);
                controller.setCardUI(trackLapsReservationCard);
                controller.setParentController(this);

                LapsReservationsVBox.getChildren().add(trackLapsReservationCard);

            } catch (IOException e) {
                System.out.println("ERRORE nel caricamento TrackCard.fxml");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Errore generico:");
                e.printStackTrace();
            }
        }
    }



    public void initialize(){
        int userId = LoggedUser.getInstance().getCustomer().getUserId();
        TrackLapsReservationBean bean = new TrackLapsReservationBean();

        List<TrackLapsReservation> lapsReservationsList;
        try {
            lapsReservationsList = bean.getUserLapsReservation(userId);
        } catch (SQLException e) {
            System.out.println("Errore nel caricamento delle prenotazioni effettuate");
            throw new RuntimeException(e);
        }

        if (lapsReservationsList == null || lapsReservationsList.isEmpty()) {
            System.out.println("Nessun circuito trovato nel database.");
            return;
        }
        System.out.println("Caricate " + lapsReservationsList.size() + " prenotazioni dal Bean.");

        populateLapsReservations(lapsReservationsList);
    }

}
