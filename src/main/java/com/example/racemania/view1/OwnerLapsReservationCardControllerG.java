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
    private Label DateLabel;

    @FXML
    private Label VehicleLabel;

    @FXML
    private Label DetailsLabel;

    @FXML
    private Label ConfirmationStatusLabel;

    @FXML
    private void handleClick() {
        if (parentController != null) {
            parentController.setSelectedLapsReservation(this.trackLapsReservation, cardUI);
        }
    }


    public void setData(TrackLapsReservation trackLapsReservation) throws SQLException {
        this.trackLapsReservation = trackLapsReservation;
        TrackBean trackBean = new TrackBean();
        VehicleBean vehicleBean = new VehicleBean();

        String reservationVehiclePlate = trackLapsReservation.getVehiclePlate();

        try {
            vehicleBean = manageLapsReservationsController.getVehicle(reservationVehiclePlate);
        }
        catch (SQLException e){
            // not handled
            System.out.println("Errore nell'ottenere informazioni sul tracciato o sul veicolo");
            throw new SQLException();
        }

        if(trackLapsReservation.getIsDaily()) {
            DetailsLabel.setText("Ingresso giornaliero");
        } else {
            DetailsLabel.setText("Numero di giri: "+trackLapsReservation.getLaps());
        }

        VehicleLabel.setText(""+vehicleBean.getBrand() + " " + vehicleBean.getModel() +
                             " IY: " + vehicleBean.getImmatriculationYear() + ", last check: " + vehicleBean.getLastcheckYear());
        ConfirmationStatusLabel.setText(""+trackLapsReservation.getConfirmationStatus());
        DateLabel.setText(""+trackLapsReservation.getDate());
    }



    public void setParentController(OwnerLapsReservationsPageControllerG controller) {
        this.parentController = controller;
    }

    public void setCardUI(Parent cardUI) {
        this.cardUI = cardUI;
    }
}
