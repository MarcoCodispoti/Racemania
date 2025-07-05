package com.example.racemania.view2;

import com.example.racemania.controller.ManageLapsReservationsController;
import com.example.racemania.model.TrackLapsReservation;
import com.example.racemania.model.bean.UserBean;
import com.example.racemania.model.bean.VehicleBean;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.sql.SQLException;

public class OwnerActiveReservationCardControllerG2 {
    ManageLapsReservationsController manageLapsReservationsController = new ManageLapsReservationsController();


    @FXML
    private Label dateLabel;

    @FXML
    private Label vehicleLabel;

    @FXML
    private Label detailsLabel;

    @FXML
    private Label userLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private ScrollPane scrollPane;

    private TrackLapsReservation trackLapsReservation;
    private OwnerActiveReservationsPageControllerG2 parentController;
    private Parent cardUI;


    public void setData(TrackLapsReservation trackLapsReservation) {
        this.trackLapsReservation = trackLapsReservation;

        VehicleBean vehicleBean = new VehicleBean();
        UserBean userBean = new UserBean();

        String reservationVehiclePlate = trackLapsReservation.getVehiclePlate();

        try {
            userBean = manageLapsReservationsController.getUser(trackLapsReservation.getUserID());
            vehicleBean = manageLapsReservationsController.getVehicle(reservationVehiclePlate);
        }
        catch (SQLException _){
            // not handled
        }


        dateLabel.setText(trackLapsReservation.getDate().toString());
        vehicleLabel.setText(vehicleBean.getBrand()+" "+vehicleBean.getModel()+" "+vehicleBean.getPlate());
        if(trackLapsReservation.getIsDaily()) {
            detailsLabel.setText("Prenotazione Giri: Ingresso giornaliero");
        } else {
            detailsLabel.setText("Prenotazione Giri, Numero di giri: "+ trackLapsReservation.getLaps());
        }
        userLabel.setText(userBean.getUserName());
        statusLabel.setText(trackLapsReservation.getConfirmationStatus());
    }

    public void setParentController(OwnerActiveReservationsPageControllerG2 controller) {
        this.parentController = controller;
    }


    public void setCardUI(Parent cardUI) {
        this.cardUI = cardUI;
    }

}
