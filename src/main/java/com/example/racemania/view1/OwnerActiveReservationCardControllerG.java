package com.example.racemania.view1;

import com.example.racemania.controller.ManageLapsReservationsController;
import com.example.racemania.model.TrackLapsReservation;
import com.example.racemania.model.bean.UserBean;
import com.example.racemania.model.bean.VehicleBean;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.sql.SQLException;

public class OwnerActiveReservationCardControllerG {
    ManageLapsReservationsController manageLapsReservationsController = new ManageLapsReservationsController();

    @FXML
    private Label DateLabel;

    @FXML
    private Label VehicleLabel;

    @FXML
    private Label DetailsLabel;

    @FXML
    private Label UserLabel;

    @FXML
    private Label StatusLabel;

    @FXML
    private ScrollPane scrollPane;

    private TrackLapsReservation trackLapsReservation;
    private OwnerHomeControllerG parentController;
    private Parent cardUI;


    @FXML
//    private void handleClick() {
//        if (parentController != null) {
//            parentController.setSelectedTrack(track, cardUI);
//        }
//    }

    public void setData(TrackLapsReservation trackLapsReservation) {
        this.trackLapsReservation = trackLapsReservation;

        VehicleBean vehicleBean = new VehicleBean();
        UserBean userBean = new UserBean();

        String reservationVehiclePlate = trackLapsReservation.getVehiclePlate();

        try {
            userBean = manageLapsReservationsController.getUser(trackLapsReservation.getUserID());
            vehicleBean = manageLapsReservationsController.getVehicle(reservationVehiclePlate);
        }
        catch (SQLException e){
            // not handled
            System.out.println("Errore nell'ottenere informazioni sul tracciato o sul veicolo");
            // throw new SQLException();
        }


        DateLabel.setText(trackLapsReservation.getDate().toString());
        VehicleLabel.setText(vehicleBean.getBrand()+" "+vehicleBean.getModel()+" "+vehicleBean.getPlate());
        if(trackLapsReservation.getIsDaily()) {
            DetailsLabel.setText("Prenotazione Giri: Ingresso giornaliero");
        } else {
            DetailsLabel.setText("Prenotazione Giri, Numero di giri: "+ trackLapsReservation.getLaps());
        }
        UserLabel.setText(userBean.getUserName());
        StatusLabel.setText(trackLapsReservation.getConfirmationStatus());
    }

    public void setParentController(OwnerHomeControllerG controller) {
        this.parentController = controller;
    }


    public void setCardUI(Parent cardUI) {
        this.cardUI = cardUI;
    }

}
