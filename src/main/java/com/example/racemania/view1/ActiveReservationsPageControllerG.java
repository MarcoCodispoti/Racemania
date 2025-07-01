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

            } catch (IOException _) {
                // Non carica le trackCard, to be handled
            } catch (Exception _) {
                // errore generico, to be handled
            }
        }
    }

    public void initialize() throws SQLException{
        int userId = LoggedUser.getInstance().getCustomer().getUserId();
        TrackLapsReservationBean bean = new TrackLapsReservationBean();

        List<TrackLapsReservation> lapsReservationsList;
        try {
            lapsReservationsList = bean.getUserLapsReservation(userId);
        } catch (SQLException e) {
            throw new SQLException(e);  // errore nel caricamento delle prenotazioni effettuate
        }

        if (lapsReservationsList == null || lapsReservationsList.isEmpty()) {
            return;
        }

        populateLapsReservations(lapsReservationsList);
    }

}
