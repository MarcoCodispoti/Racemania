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
        System.out.println("" + actualLapsReservationBean.getLapPrice());
        System.out.println("" + actualLapsReservationBean.getDailyPrice());
        setPrices(trackLapsReservationBean);
        System.out.println("" + actualLapsReservationBean.getLapPrice());
        System.out.println("" + actualLapsReservationBean.getDailyPrice());

        updateTextFlow(DailyPriceTextFlow, DailyPrice+ " €",24);  // nel parametro a destra mettere il testo da inserire
        updateTextFlow(LapPriceTextFlow, LapPrice+" €",24);
    }

    private boolean isDaily = false;
    private int DailyPrice;
    private int LapsNumber = 1;
    private int LapPrice;
    private int Total;


    @FXML
    private RadioButton LapsRadioButton;
    @FXML
    private ToggleGroup TicketToogleGroup;

    @FXML
    private RadioButton DailyRadioButton;

    @FXML
    private Button AddLapButton;

    @FXML
    private Button RemoveLapButton;

    @FXML
    private TextFlow DailyPriceTextFlow;

    @FXML
    private TextFlow LapsNumberTextFlow;

    @FXML
    private TextFlow LapPriceTextFlow;

    @FXML
    private TextFlow TotalTextFlow;

    @FXML
    private Button PaymentButton;



    public void updateTextFlow(TextFlow selectedTextFlow, String text, int textSize){
        selectedTextFlow.getChildren().clear();
        Text info = new Text(text);
        info.setFont(Font.font("Arial", textSize));
        info.setId("info");
        selectedTextFlow.getChildren().add(info);
    }

    public void updateTotalTextFlow(){
        TotalTextFlow.getChildren().clear();
        Text info = new Text(Total+" €");
        info.setFont(Font.font("Arial", 28));
        info.setId("info");
        TotalTextFlow.getChildren().add(info);
    }

    public void setPrices(TrackLapsReservationBean trackLapsReservationBean){
        DailyPrice = trackLapsReservationBean.getDailyPrice();
        LapPrice = trackLapsReservationBean.getLapPrice();
    }

    public void initialize() {
        //updateDailyPriceTextFlow();
        //setPrices(actualLapsReservationBean);

         // nel parametro a destra mettere il testo da inserire
        updateTextFlow(LapsNumberTextFlow, " " + LapsNumber ,24);
        updateTextFlow(TotalTextFlow, Total +" €",28);

    }

    public void ClickedOnAddLap(ActionEvent actionEvent) throws IOException {
        if(isDaily==false) {

            LapsNumber++;
            updateTextFlow(LapsNumberTextFlow, " " + LapsNumber, 24);
            if(LapsNumber>=16){
                LapsNumber = 0;
                updateTextFlow(LapsNumberTextFlow, " " + LapsNumber, 24);
                isDaily = true;
                Total = DailyPrice;
                DailyRadioButton.setSelected(true);
                LapsRadioButton.setSelected(false);
                updateTotalTextFlow();
            } else {
                isDaily = false;
                Total = LapPrice * LapsNumber;
                updateTotalTextFlow();
            }
        } else{
            return;
        }
    }

    public void ClickedOnRemoveLap(ActionEvent actionEvent) throws IOException {
        if(isDaily == false) {
            if (LapsNumber == 1) {
                return;
            } else {
                LapsNumber--;
                updateTextFlow(LapsNumberTextFlow, " " + LapsNumber, 24);
            }
            Total = LapPrice * LapsNumber;
            updateTotalTextFlow();
        } else {
            return;
        }
    }

    public void ClickedOnPayment(ActionEvent event) throws IOException {      //il comando è collegato al bottone dal file FXML
        // FxmlLoader.setPage("PaymentPage");                          //Comando per cambiare pagina
        bookLapsReservationController = new BookLapsReservationController();

        if(LapsRadioButton.isSelected() || DailyRadioButton.isSelected()) {
            if(DailyRadioButton.isSelected()){
                LapsNumber = 0;
            }

            try {
                bookLapsReservationController.setTicketInfo(actualLapsReservationBean, isDaily, LapPrice, Total, LapsNumber);
            }
            catch (FailedInsertException e) {
                e.printStackTrace();
            }

            PaymentPageControllerG controller = FxmlLoader.setPageAndReturnController("PaymentPage");
            controller.setTrackLapsReservationBean(actualLapsReservationBean);
        } else {
            System.out.println("Seleziona un tipo di biglietto");
            return;
        }
    }

    public void ClickedOnLapsRadioButton(ActionEvent actionEvent) throws IOException {
        isDaily = false;
        LapsNumber = 1;
        updateTextFlow(LapsNumberTextFlow, " " + LapsNumber, 24);
        Total = LapPrice*LapsNumber;
        updateTotalTextFlow();
    }

    public void ClickedOnDailyRadioButton(ActionEvent actionEvent) throws IOException {
        isDaily = true;
        Total = DailyPrice;
        updateTotalTextFlow();
    }
}
