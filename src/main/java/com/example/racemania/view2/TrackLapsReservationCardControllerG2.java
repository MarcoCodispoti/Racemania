package com.example.racemania.view2;

import com.example.racemania.controller.ManageLapsReservationsController;
import com.example.racemania.controller.ReservationsHistoryController;
import com.example.racemania.model.Track;
import com.example.racemania.model.TrackLapsReservation;
import com.example.racemania.model.bean.TrackBean;
import com.example.racemania.model.bean.VehicleBean;
import com.example.racemania.view1.ActiveReservationsPageControllerG;
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
    private Label TrackNameLabel;

    @FXML
    private Label PriceLabel;

    @FXML
    private Label DetailsLabel;

    @FXML
    private Label VehicleLabel;

    @FXML
    private Label ConfirmationStatusLabel;

    @FXML
    private Label DateLabel;



    @FXML
    private void handleClick(){
        // to be handled
    }



    public void setData(TrackLapsReservation trackLapsReservation) throws SQLException {
        this.trackLapsReservation = trackLapsReservation;
        TrackBean trackBean = new TrackBean();
        VehicleBean vehicleBean; // = new VehicleBean();

        int reservationTrackId = trackLapsReservation.getTrackID();
        String reservationVehiclePlate = trackLapsReservation.getVehiclePlate();

        Track reservationTrack;

        try {
            trackBean = reservationsHistoryController.getLapsReservationTrack(reservationTrackId);
            // reservationTrack = trackBean.getTrack(reservationTrackId);
            vehicleBean = reservationsHistoryController.getVehicle(reservationVehiclePlate);
        }
        catch (SQLException e){
            // not handled
            System.out.println("Errore nell'ottenere informazioni sul tracciato o sul veicolo");
            throw new SQLException();
        }

        TrackNameLabel.setText(trackBean.getName());
        PriceLabel.setText(""+trackLapsReservation.getPrice()+" €");
        if(trackLapsReservation.getIsDaily()) {
            DetailsLabel.setText("Ingresso giornaliero");
        } else {
            DetailsLabel.setText("Numero di giri: "+trackLapsReservation.getLaps());
        }
        VehicleLabel.setText(""+vehicleBean.getBrand() + " " + vehicleBean.getModel() + " : " + vehicleBean.getPlate());
        ConfirmationStatusLabel.setText(""+trackLapsReservation.getConfirmationStatus());
        DateLabel.setText(""+trackLapsReservation.getDate());
    }

    public void setParentController(ReservationsPageControllerG2 controller) {
        this.parentController = controller;
    }

    public void setCardUI(Parent cardUI) {
        this.cardUI = cardUI;
    }


}
