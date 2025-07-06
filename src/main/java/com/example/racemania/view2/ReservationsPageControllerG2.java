package com.example.racemania.view2;

import com.example.racemania.model.LoggedUser;
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

public class ReservationsPageControllerG2{

    @FXML
    private Label errorLabel;

    @FXML
    private VBox lapsReservationsVBox;

    @FXML
    private void clickedOnCircuitsHyperlink(){
        errorLabel.setText("Not implemented yet");
    }

    @FXML
    private void clickedOnHomePageHyperlink(){
        FxmlLoader2.setPage("HomePage2");
    }




    public void populateLapsReservations(List<TrackLapsReservation> lapsReservationsList ) {
        lapsReservationsVBox.getChildren().clear();

        for (TrackLapsReservation trackLapsReservation : lapsReservationsList) {
            try {
                FXMLLoader cardloader = new FXMLLoader(getClass().getResource("/com/example/racemania/view2/TrackLapsReservationCard2.fxml"));
                Parent trackLapsReservationCard = cardloader.load();
                TrackLapsReservationCardControllerG2 controller = cardloader.getController();
                controller.setData(trackLapsReservation);
                controller.setCardUI(trackLapsReservationCard);
                controller.setParentController(this);

                lapsReservationsVBox.getChildren().add(trackLapsReservationCard);

            } catch (IOException _) {
                errorLabel.setText("Tracks loading error");
            } catch (Exception _) {
                errorLabel.setText("Generic error");
            }
        }
    }



    public void initialize(){
        int userId = LoggedUser.getInstance().getCustomer().getUserId();
        TrackLapsReservationBean bean = new TrackLapsReservationBean();

        List<TrackLapsReservation> lapsReservationsList;
        try {
            lapsReservationsList = bean.getUserLapsReservation(userId);
        } catch (SQLException _){
            errorLabel.setText("Active reservations loading error");
            return;
        }

        if (lapsReservationsList == null || lapsReservationsList.isEmpty()) {
            errorLabel.setText("No track found");
            return;
        }
        populateLapsReservations(lapsReservationsList);
    }

}
