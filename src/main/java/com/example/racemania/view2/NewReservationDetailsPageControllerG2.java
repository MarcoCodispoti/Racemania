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
    private int DailyPrice;
    private int LapsNumber = 1;
    private int LapPrice;
    private int Total;

    TrackLapsReservationBean actualLapsReservationBean;
    public void setTrackLapsReservationBean(TrackLapsReservationBean trackLapsReservationBean) {
        this.actualLapsReservationBean = trackLapsReservationBean;
        setPrices(trackLapsReservationBean);
        Total = LapsNumber * LapPrice;
        totalPriceLabel.setText(Total + "");
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
    private void ClickedOnBackButton(){
        FxmlLoader2.setPage("NewReservationPage2");
    }

    @FXML
    private void ClickedOnProceedButton(){
        if(checkTextFields() && validateHorsepower()) {

            bookLapsReservationController.setTicketInfo(actualLapsReservationBean,isDaily,actualLapsReservationBean.getLapPrice(),Total,LapsNumber);
            VehicleBean vehicleBean = fillVehicle();

            System.out.println("Plate: " + vehicleBean.getPlate());
            System.out.println("Brand: " + vehicleBean.getBrand());
            System.out.println("Model: " + vehicleBean.getModel());
            System.out.println("IY: " + vehicleBean.getImmatriculationYear());
            System.out.println("Powe " + vehicleBean.getPower());
            System.out.println("LYC: " + vehicleBean.getLastcheckYear());


            bookLapsReservationController.insertVehicle(vehicleBean);
            actualLapsReservationBean.setVehiclePlate(vehicleBean.getPlate());

            System.out.println("userId" + actualLapsReservationBean.getUserID());
            System.out.println("date: " + actualLapsReservationBean.getDate());
            System.out.println("trackId: " + actualLapsReservationBean.getTrackID());
            System.out.println("vehiclePlate: " + actualLapsReservationBean.getVehiclePlate());
            System.out.println("price: " + actualLapsReservationBean.getPrice());
            System.out.println("laps: " + actualLapsReservationBean.getLaps());
            System.out.println("isDaily: " + actualLapsReservationBean.getIsDaily());
            System.out.println("lapsPrice: " + actualLapsReservationBean.getLapPrice());
            System.out.println("dailyPrice: " + actualLapsReservationBean.getDailyPrice());



            try {
                bookLapsReservationController.insertLapsReservation(actualLapsReservationBean);
            } catch (Exception e) {
                System.out.println("Errore nel caricare la prenotazione");
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
                LapsNumber = 0;
                Total = DailyPrice;
                totalPriceLabel.setText("" + Total);
                isDaily = true;
            } else {
                int laps = newVal.intValue();
                isDaily = false;
                LapsNumber = laps;
                lapsNumberLabel.setText("" + laps);
                Total = LapsNumber * LapPrice;
                totalPriceLabel.setText(""+Total);
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
        DailyPrice = trackLapsReservationBean.getDailyPrice();
        System.out.println("Daily Price " + DailyPrice);
        LapPrice = trackLapsReservationBean.getLapPrice();
        System.out.println("Lap Price " + LapPrice);
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
