package com.example.racemania.view1;


import com.example.racemania.controller.BookLapsReservationController;
import com.example.racemania.model.LoggedUser;
import com.example.racemania.model.Vehicle;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import com.example.racemania.model.bean.VehicleBean;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;


public class VehiclePageControllerG {
    BookLapsReservationController bookLapsReservationController;
    private boolean isFull;
    private Vehicle vehicle;
    private VehicleBean vehicleBean;

    TrackLapsReservationBean actualLapsReservationBean;
    public void setTrackLapsReservationBean(TrackLapsReservationBean trackLapsReservationBean) {
        this.actualLapsReservationBean = trackLapsReservationBean;
    }

    @FXML
    private TextField plateTextField;

    @FXML
    private TextField modelTextField;

    @FXML
    private TextField yearTextField;

    @FXML
    private TextField brandTextField;

    @FXML
    private TextField powerTextField;

    @FXML
    private TextField lastcheckyearTextField;

    @FXML
    private Button ProceedButton;

    @FXML
    public void ClickedOnProceed(ActionEvent event) throws IOException {      //il comando è collegato al bottone dal file FXML
        bookLapsReservationController = new BookLapsReservationController();

        isFull = checkIsFull();
        if(isFull){
            // aggiungi controllo sui tipi di dati inseriti e sul loro formato

            VehicleBean vehicleBean = fillVehicle();
            // Vehicle actualvehicle = setNewVehicle(vehicleBean);

            try {
                bookLapsReservationController.insertVehicle(vehicleBean);
            } catch (Exception e) {
                // to be handled
                System.out.println("Errore nell'inserimento del vehicle.");
            }

            actualLapsReservationBean.setVehiclePlate(vehicleBean.getPlate());
            //FxmlLoader.setPage("DatePage");                          //Comando per cambiare pagina
            DatePageControllerG controller = FxmlLoader.setPageAndReturnController("DatePage");
            controller.setTrackLapsReservationBean(actualLapsReservationBean);
        }
        else{
        System.out.println("Devi compilare tutti i campi!");
        return;
        }

    }


    public VehicleBean fillVehicle(){
        VehicleBean vehicleBean = new VehicleBean(plateTextField.getText(), LoggedUser.getInstance().getCustomer().getUserId(),brandTextField.getText(),
                                                modelTextField.getText(),Integer.parseInt(yearTextField.getText()),
                                                Integer.parseInt(powerTextField.getText()),
                                                Integer.parseInt(lastcheckyearTextField.getText()));

        return vehicleBean;
    }

    public boolean checkIsFull(){
        if( brandTextField.getText().isEmpty() ||
                plateTextField.getText().isEmpty() ||
                modelTextField.getText().isEmpty()||
                yearTextField.getText().isEmpty()||
                powerTextField.getText().isEmpty()||
                lastcheckyearTextField.getText().isEmpty()){
            return false;
        } else {
            return true;
        }
    }

}
