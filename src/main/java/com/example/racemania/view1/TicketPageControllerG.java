package com.example.racemania.view1;

import com.example.racemania.controller.BookLapsReservationController;
import com.example.racemania.exceptions.FailedInsertException;

import java.io.IOException;

import com.example.racemania.model.bean.TrackLapsReservationBean;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class TicketPageControllerG {
    BookLapsReservationController bookLapsReservationController;
    TrackLapsReservationBean actualLapsReservationBean;

    public void setTrackLapsReservationBean(TrackLapsReservationBean trackLapsReservationBean) {
        this.actualLapsReservationBean = trackLapsReservationBean;
        setPrices(trackLapsReservationBean);

        updateTextFlow(dailyPriceTextFlow, dailyPrice + " €",24);  // nel parametro a destra mettere il testo da inserire
        updateTextFlow(lapPriceTextFlow, lapPrice +" €",24);
    }

    private boolean isDaily = false;
    private int dailyPrice;
    private int lapsNumber = 1;
    private int lapPrice;
    private int total;


    @FXML
    private RadioButton lapsRadioButton;
    @FXML
    private ToggleGroup ticketToogleGroup;

    @FXML
    private RadioButton dailyRadioButton;

    @FXML
    private Button addLapButton;

    @FXML
    private Button removeLapButton;

    @FXML
    private TextFlow dailyPriceTextFlow;

    @FXML
    private TextFlow lapsNumberTextFlow;

    @FXML
    private TextFlow lapPriceTextFlow;

    @FXML
    private TextFlow totalTextFlow;

    @FXML
    private Button paymentButton;


    public void updateTextFlow(TextFlow selectedTextFlow, String text, int textSize){
        selectedTextFlow.getChildren().clear();
        Text info = new Text(text);
        info.setFont(Font.font("Arial", textSize));
        info.setId("info");
        selectedTextFlow.getChildren().add(info);
    }

    public void updateTotalTextFlow(){
        totalTextFlow.getChildren().clear();
        Text info = new Text(total +" €");
        info.setFont(Font.font("Arial", 28));
        info.setId("info");
        totalTextFlow.getChildren().add(info);
    }

    public void setPrices(TrackLapsReservationBean trackLapsReservationBean){
        dailyPrice = trackLapsReservationBean.getDailyPrice();
        lapPrice = trackLapsReservationBean.getLapPrice();
    }

    public void initialize() {
        updateTextFlow(lapsNumberTextFlow, " " + lapsNumber,24);
        updateTextFlow(totalTextFlow, total +" €",28);

    }

    @FXML
    public void clickedOnAddLap(ActionEvent actionEvent) throws IOException {
        if(isDaily==false) {

            lapsNumber++;
            updateTextFlow(lapsNumberTextFlow, " " + lapsNumber, 24);
            if(lapsNumber >=16){
                lapsNumber = 0;
                updateTextFlow(lapsNumberTextFlow, " " + lapsNumber, 24);
                isDaily = true;
                total = dailyPrice;
                dailyRadioButton.setSelected(true);
                lapsRadioButton.setSelected(false);
                updateTotalTextFlow();
            } else {
                isDaily = false;
                total = lapPrice * lapsNumber;
                updateTotalTextFlow();
            }
        } else{
            return;
        }
    }

    @FXML
    public void clickedOnRemoveLap(ActionEvent actionEvent) throws IOException {
        if(isDaily == false) {
            if (lapsNumber == 1) {
                return;
            } else {
                lapsNumber--;
                updateTextFlow(lapsNumberTextFlow, " " + lapsNumber, 24);
            }
            total = lapPrice * lapsNumber;
            updateTotalTextFlow();
        } else {
            return;
        }
    }

    @FXML
    public void clickedOnPayment(ActionEvent event) throws IOException {      //il comando è collegato al bottone dal file FXML

        bookLapsReservationController = new BookLapsReservationController();

        if(lapsRadioButton.isSelected() || dailyRadioButton.isSelected()) {
            if(dailyRadioButton.isSelected()){
                lapsNumber = 0;
            }

            try {
                bookLapsReservationController.setTicketInfo(actualLapsReservationBean, isDaily, lapPrice, total, lapsNumber);
            }
            catch (FailedInsertException _) {
               // to be handled
            }

            PaymentPageControllerG controller = FxmlLoader.setPageAndReturnController("PaymentPage");
            controller.setTrackLapsReservationBean(actualLapsReservationBean);
        } else {
            // System.out.println("Seleziona un tipo di biglietto");
            // aggiungere un error label
        }
    }

    @FXML
    public void clickedOnLapsRadioButton(ActionEvent actionEvent) throws IOException {
        isDaily = false;
        lapsNumber = 1;
        updateTextFlow(lapsNumberTextFlow, " " + lapsNumber, 24);
        total = lapPrice * lapsNumber;
        updateTotalTextFlow();
    }

    @FXML
    public void clickedOnDailyRadioButton(ActionEvent actionEvent) throws IOException {
        isDaily = true;
        total = dailyPrice;
        updateTotalTextFlow();
    }
}
