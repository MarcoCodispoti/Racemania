package com.example.racemania.view2;

import com.example.racemania.controller.BookLapsReservationController;
import com.example.racemania.model.LoggedUser;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import com.example.racemania.model.bean.VehicleBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class NewReservationDetailsPageControllerG2 {
    BookLapsReservationController bookLapsReservationController = new BookLapsReservationController();

    private boolean isDaily = false;
    private int dailyPrice;
    private int lapsNumber = 1;
    private int lapPrice;
    private int total;

    TrackLapsReservationBean actualLapsReservationBean;
    public void setTrackLapsReservationBean(TrackLapsReservationBean trackLapsReservationBean) {
        this.actualLapsReservationBean = trackLapsReservationBean;
        setPrices(trackLapsReservationBean);
        total = lapsNumber * lapPrice;
        totalPriceLabel.setText(total + "");
    }

    @FXML
    private Label errorLabel;

    @FXML
    private Slider lapsNumberSlider;

    @FXML
    private Label lapsNumberLabel;

    @FXML
    private TextField brandTextField;

    @FXML
    private TextField modelTextField;

    @FXML
    private TextField plateTextField;

    @FXML
    private TextField immatriculationYearTextField;

    @FXML
    private TextField horsepowerTextField;

    @FXML
    private TextField lastCheckYearTextField;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Button backButton;

    @FXML
    private Button proceedButton;

    @FXML
    private void clickedOnBackButton(){
        FxmlLoader2.setPage("NewReservationPage2");
    }

    @FXML
    private void clickedOnProceedButton(){
        if(checkTextFields() && validateHorsepower()) {

            bookLapsReservationController.setTicketInfo(actualLapsReservationBean,isDaily,actualLapsReservationBean.getLapPrice(), total, lapsNumber);
            VehicleBean vehicleBean = fillVehicle();

            bookLapsReservationController.insertVehicle(vehicleBean);
            actualLapsReservationBean.setVehiclePlate(vehicleBean.getPlate());

            try {
                bookLapsReservationController.insertLapsReservation(actualLapsReservationBean);
            } catch (Exception _) {
                errorLabel.setText("Errore nel caricare la prenotazione");
            }
            FxmlLoader2.setPage("ConfirmationPage2");
        }
    }

    @FXML
    private void initialize() {
        final int minLaps = 1;
        final int maxLaps = 16;
        // Imposta range minimo e massimo dello slider
        lapsNumberSlider.setMin(minLaps);
        lapsNumberSlider.setMax(maxLaps);
        lapsNumberSlider.setValue(minLaps); // Valore iniziale

        // Mostra il valore iniziale nello slider
        lapsNumberLabel.setText("" + (int) lapsNumberSlider.getValue());

        // Listener per aggiornare la label in tempo reale
        lapsNumberSlider.valueProperty().addListener((obs, oldVal, newVal) -> {

            if(newVal.intValue() == maxLaps){
                lapsNumberLabel.setText("Daily");
                lapsNumber = 0;
                total = dailyPrice;
                totalPriceLabel.setText("" + total);
                isDaily = true;
            } else {
                int laps = newVal.intValue();
                isDaily = false;
                lapsNumber = laps;
                lapsNumberLabel.setText("" + laps);
                total = lapsNumber * lapPrice;
                totalPriceLabel.setText(""+ total);
            }

        });
    }

    @FXML
    private boolean checkTextFields(){
        if( brandTextField.getText().equals("") || modelTextField.getText().equals("") || plateTextField.getText().equals("") ||
            immatriculationYearTextField.getText().equals("") || horsepowerTextField.getText().equals("") || lastCheckYearTextField.getText().equals("")){
            errorLabel.setText("Vehicle fields cannot be empty");
            return false;
        } else {
            return validateYearFields();
        }
    }

    private boolean validateYearFields() {
        String immatriculationYear = immatriculationYearTextField.getText();
        String lastCheckYear = lastCheckYearTextField.getText();
        String yearRegex = "\\d{4}";
        boolean valid = true;
        if (!immatriculationYear.matches(yearRegex)) {
            errorLabel.setText("Immatriculation Year Invalid");
            valid = false;
        }
        if (!lastCheckYear.matches(yearRegex)) {
            errorLabel.setText("Last Check Year Invalid");
            valid = false;
        }
        return valid;
    }

    public void setPrices(TrackLapsReservationBean trackLapsReservationBean){
        dailyPrice = trackLapsReservationBean.getDailyPrice();
        lapPrice = trackLapsReservationBean.getLapPrice();
    }

    public VehicleBean fillVehicle(){
        VehicleBean vehicleBean = new VehicleBean(plateTextField.getText(), LoggedUser.getInstance().getCustomer().getUserId(),brandTextField.getText(),
                modelTextField.getText(),Integer.parseInt(immatriculationYearTextField.getText()),
                Integer.parseInt(horsepowerTextField.getText()),
                Integer.parseInt(lastCheckYearTextField.getText()));

        return vehicleBean;
    }

    private boolean validateHorsepower() {
        String horsepowerText = horsepowerTextField.getText();
        try {
            int horsepower = Integer.parseInt(horsepowerText);
            if (horsepower > 0 && horsepower <= 3000) {
                return true;
            } else {
                errorLabel.setText("Horsepower must be between 0 and 3000");
                return false;
            }
        } catch (NumberFormatException e) {
            errorLabel.setText("Power must be an integer");
            return false;
        }
    }

}
