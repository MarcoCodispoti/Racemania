package com.example.racemania.view1;

import com.example.racemania.controller.ManageLapsReservationsController;
import com.example.racemania.model.TrackLapsReservation;
import com.example.racemania.model.bean.TrackBean;
import com.example.racemania.model.bean.VehicleBean;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class OwnerLapsReservationCardControllerG {
    ManageLapsReservationsController manageLapsReservationsController = new ManageLapsReservationsController();
    private TrackLapsReservation trackLapsReservation;
    private OwnerLapsReservationsPageControllerG parentController;
    private Parent cardUI;

    @FXML
    private Label dateLabel;

    @FXML
    private Label vehicleLabel;

    @FXML
    private Label detailsLabel;

    @FXML
    private Label confirmationStatusLabel;

    @FXML
    private void handleClick() {
        if (parentController != null) {
            parentController.setSelectedLapsReservation(this.trackLapsReservation, cardUI);
        }
    }


    public void setData(TrackLapsReservation trackLapsReservation) throws SQLException {
        this.trackLapsReservation = trackLapsReservation;
        VehicleBean vehicleBean;

        String reservationVehiclePlate = trackLapsReservation.getVehiclePlate();

        try {
            vehicleBean = manageLapsReservationsController.getVehicle(reservationVehiclePlate);
        }
        catch (SQLException e){
            // not handled   // errore nell'ottenere informazioni sul tracciato o veicolo
            throw new SQLException(e);
        }

        if(trackLapsReservation.getIsDaily()) {
            detailsLabel.setText("Ingresso giornaliero");
        } else {
            detailsLabel.setText("Numero di giri: "+trackLapsReservation.getLaps());
        }

        vehicleLabel.setText(""+vehicleBean.getBrand() + " " + vehicleBean.getModel() +
                             " IY: " + vehicleBean.getImmatriculationYear() + ", last check: " + vehicleBean.getLastcheckYear());
        confirmationStatusLabel.setText(""+trackLapsReservation.getConfirmationStatus());
        dateLabel.setText(""+trackLapsReservation.getDate());
    }



    public void setParentController(OwnerLapsReservationsPageControllerG controller) {
        this.parentController = controller;
    }

    public void setCardUI(Parent cardUI) {
        this.cardUI = cardUI;
    }
}
