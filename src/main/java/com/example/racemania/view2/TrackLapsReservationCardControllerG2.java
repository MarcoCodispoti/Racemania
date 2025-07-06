package com.example.racemania.view2;

import com.example.racemania.controller.ManageLapsReservationsController;
import com.example.racemania.controller.ReservationsHistoryController;
import com.example.racemania.model.Track;
import com.example.racemania.model.TrackLapsReservation;
import com.example.racemania.model.bean.TrackBean;
import com.example.racemania.model.bean.VehicleBean;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class TrackLapsReservationCardControllerG2 {


    private ReservationsHistoryController reservationsHistoryController = new ReservationsHistoryController();
    private ManageLapsReservationsController manageLapsReservationsController = new ManageLapsReservationsController();
    private TrackLapsReservation trackLapsReservation;
    private ReservationsPageControllerG2 parentController;
    private Parent cardUI;

    @FXML
    private Label errorLabel;

    @FXML
    private Label trackNameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label detailsLabel;

    @FXML
    private Label vehicleLabel;

    @FXML
    private Label confirmationStatusLabel;

    @FXML
    private Label dateLabel;



//    @FXML
//    private void handleClick(){
//        // to be handled
//    }



    public void setData(TrackLapsReservation trackLapsReservation){
        this.trackLapsReservation = trackLapsReservation;
        TrackBean trackBean;
        VehicleBean vehicleBean;

        int reservationTrackId = trackLapsReservation.getTrackID();
        String reservationVehiclePlate = trackLapsReservation.getVehiclePlate();

        try {
            trackBean = reservationsHistoryController.getLapsReservationTrack(reservationTrackId);
            vehicleBean = reservationsHistoryController.getVehicle(reservationVehiclePlate);
        }
        catch (SQLException _){
            errorLabel.setText("Can't obtain track or vehicle info");
            return;
        }

        trackNameLabel.setText(trackBean.getName());
        priceLabel.setText(""+trackLapsReservation.getPrice()+" €");
        if(trackLapsReservation.getIsDaily()) {
            detailsLabel.setText("Ingresso giornaliero");
        } else {
            detailsLabel.setText("Numero di giri: "+trackLapsReservation.getLaps());
        }
        vehicleLabel.setText(""+vehicleBean.getBrand() + " " + vehicleBean.getModel() + " : " + vehicleBean.getPlate());
        confirmationStatusLabel.setText(""+trackLapsReservation.getConfirmationStatus());
        dateLabel.setText(""+trackLapsReservation.getDate());
    }

    public void setParentController(ReservationsPageControllerG2 controller) {
        this.parentController = controller;
    }

    public void setCardUI(Parent cardUI) {
        this.cardUI = cardUI;
    }


}
