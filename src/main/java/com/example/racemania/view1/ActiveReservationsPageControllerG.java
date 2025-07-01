package com.example.racemania.view1;

import com.example.racemania.model.LoggedUser;
import com.example.racemania.model.TrackLapsReservation;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ActiveReservationsPageControllerG {

    @FXML
    private VBox lapsReservationsVBox;

    @FXML
    private Hyperlink reservationsHyperlink;

    @FXML
    private Hyperlink homePageHyperlink;

    @FXML
    public void clickedOnReservationsHyperlink(ActionEvent event) throws IOException {
        // to be implemented
    }

    @FXML
    public void clickedOnHomePageHyperlink(ActionEvent event){
        FxmlLoader.setPage("HomePage");
    }


    public void populateLapsReservations(List<TrackLapsReservation> lapsReservationsList ) {
        lapsReservationsVBox.getChildren().clear();

        for (TrackLapsReservation trackLapsReservation : lapsReservationsList) {
            try {

                FXMLLoader cardloader = new FXMLLoader(getClass().getResource("/com/example/racemania/view1/TrackLapsReservationCard.fxml"));
                Parent trackLapsReservationCard = cardloader.load();

                TrackLapsReservationCardControllerG controller = cardloader.getController();
                controller.setData(trackLapsReservation);
                controller.setCardUI(trackLapsReservationCard);
                controller.setParentController(this);

                lapsReservationsVBox.getChildren().add(trackLapsReservationCard);

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
