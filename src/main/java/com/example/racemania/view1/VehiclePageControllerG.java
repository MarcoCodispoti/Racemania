package com.example.racemania.view1;


import com.example.racemania.controller.BookLapsReservationController;
import com.example.racemania.model.LoggedUser;
import com.example.racemania.model.Vehicle;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import com.example.racemania.model.bean.VehicleBean;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label ErrorLabel;

    @FXML
    private Button ProceedButton;

    @FXML
    public void ClickedOnProceed(ActionEvent event) throws IOException {      //il comando è collegato al bottone dal file FXML
        bookLapsReservationController = new BookLapsReservationController();

        isFull = checkIsFull();
        if(isFull){
            if (!isValidVehicle()) {
                return;
            }

            if(!isValidPlate(plateTextField.getText())){
                ErrorLabel.setText("Inserisci una targa valida");
                return;
            }



            VehicleBean vehicleBean = fillVehicle();
            // Vehicle actualvehicle = setNewVehicle(vehicleBean);

            try {
                bookLapsReservationController.insertVehicle(vehicleBean);
            } catch (Exception e) {
                // to be handled
                ErrorLabel.setText("Errore nell'inserimento del veicolo");
                // System.out.println("Errore nell'inserimento del vehicle.");
            }

            actualLapsReservationBean.setVehiclePlate(vehicleBean.getPlate());
            //FxmlLoader.setPage("DatePage");                          //Comando per cambiare pagina
            DatePageControllerG controller = FxmlLoader.setPageAndReturnController("DatePage");
            controller.setTrackLapsReservationBean(actualLapsReservationBean);
        }
        else{
            ErrorLabel.setText("Devi compilare tutti i campi");
            // System.out.println("Devi compilare tutti i campi!");
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

    private boolean isValidPlate(String plate) {
        // Rimuove eventuali spazi e converte in maiuscolo
        plate = plate.trim().toUpperCase();

        // Formato Italiano: AA123AA
        String italianPattern = "^[A-Z]{2}\\d{3}[A-Z]{2}$";

        // Formati Europei comuni
        String euroPattern1 = "^[A-Z]{2}\\d{4}$";       // AB1234
        String euroPattern2 = "^[A-Z]{1}\\d{3}[A-Z]{2}$"; // A123BC
        String euroPattern3 = "^[A-Z]{3}\\d{3}$";       // ABC123

        return plate.matches(italianPattern) ||
                plate.matches(euroPattern1) ||
                plate.matches(euroPattern2) ||
                plate.matches(euroPattern3);
    }

    private boolean isValidVehicle() {
        try {
            int year = Integer.parseInt(yearTextField.getText().trim());
            int lastCheckYear = Integer.parseInt(lastcheckyearTextField.getText().trim());
            int power = Integer.parseInt(powerTextField.getText().trim());

            // L'anno deve essere nel range ragionevole (es: 1900 - anno corrente + 1)
            int currentYear = java.time.Year.now().getValue();
            if (year < 1900 || year > currentYear) {
                ErrorLabel.setText("Anno di immatricolazione non valido");
                return false;
            }

            if (lastCheckYear < year || lastCheckYear > currentYear + 1) {
                ErrorLabel.setText("Ultimo tagliando deve essere dopo l'immatricolazione");
                return false;
            }

            if (power <= 0 || power >= 3000) {
                ErrorLabel.setText("La potenza deve essere un numero positivo inferiore a 3000");
                return false;
            }

        } catch (NumberFormatException e) {
            ErrorLabel.setText("Anno, tagliando e potenza devono essere numeri interi validi");
            return false;
        }

        return true;
    };

}
