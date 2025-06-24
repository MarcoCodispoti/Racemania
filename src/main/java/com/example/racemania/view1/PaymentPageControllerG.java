package com.example.racemania.view1;

import java.lang.reflect.Field;

import com.example.racemania.controller.BookLapsReservationController;
import com.example.racemania.exceptions.FailedInsertException;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.sql.SQLException;

public class PaymentPageControllerG {
    TrackLapsReservationBean actualLapsReservationBean;
    private int OrderTotal;

    public void setTrackLapsReservationBean(TrackLapsReservationBean trackLapsReservationBean) {
        this.actualLapsReservationBean = trackLapsReservationBean;
        OrderTotal = actualLapsReservationBean.getPrice();
        updateTotalTextFlow();
    }

    public void updateTotalTextFlow(){
        TotalPriceTextFlow.getChildren().clear();
        Text info = new Text(OrderTotal+" €");
        info.setFont(Font.font("Arial", 28));
        info.setId("info");
        TotalPriceTextFlow.getChildren().add(info);
    }

    @FXML
    private TextField CardNumberTextField;

    @FXML
    private TextField CardExpireDateTextField;

    @FXML
    private TextField CardOwnerTextFIeld;

    @FXML
    private TextFlow TotalPriceTextFlow;

    @FXML
    private Button PayButton;

    @FXML
    private TextField cvvTextField;

    @FXML
    void ClickedOnPayButton(ActionEvent event) {
        //FxmlLoader.setPage("ConfirmationPage");
        if(validateCardNumber() && validateCardExpireDate() && validateCardOwner() && validateCVV()){
            BookLapsReservationController bookLapsReservationController = new BookLapsReservationController();
            try {
                // DebugUtils.printFields(bookLapsReservationController);
                // actualLapsReservationBean.setConfirmationStatus("Waiting for confirmation");

                System.out.println("Controller: "); DebugUtils.printFields(bookLapsReservationController);
                System.out.println("Bean: ");  DebugUtils.printFields(actualLapsReservationBean);
                System.out.println("Dati da passare al DAO:");
                System.out.println("User ID: " + actualLapsReservationBean.getUserID());
                System.out.println("Reservation ID: " + actualLapsReservationBean.getReservationID());
                System.out.println("Track ID: " + actualLapsReservationBean.getTrackID());
                System.out.println("Price: " + actualLapsReservationBean.getPrice());
                System.out.println("Laps: " + actualLapsReservationBean.getLaps());
                System.out.println("Vehicle Plate: " + actualLapsReservationBean.getConfirmationStatus());


                bookLapsReservationController.insertLapsReservation(actualLapsReservationBean);

            } catch (FailedInsertException e){
                e.printStackTrace();
            }
            FxmlLoader.setPage("ConfirmationPage");
        }
    }

    private boolean validateCardNumber(){
        String cardnumber = CardNumberTextField.getText();
        if(cardnumber.matches("\\d{16}" /*"\\d{4} \\d{4} \\d{4} \\d{4}"*/)){
            return true;
        } else{
            System.out.println("Inserisci Numero di carta valido nel formato: xxxx xxxx xxxx xxxx");
            return false;
        }
    }

    private boolean validateCardOwner(){
        String cardowner = CardOwnerTextFIeld.getText();
        if(cardowner.matches("[a-zA-Z\\s]+")){
            return true;
        } else{
            System.out.println("Inserisci un Nome Proprietario valido");
            return false;
        }
    }

    private boolean validateCardExpireDate(){
        String expireDate = CardExpireDateTextField.getText();
        if(expireDate.matches("\\d{2}/\\d{2}")){
            return true;
        } else{
            System.out.println("Formato data scadenza non valido.  Usa mm/aa");
            return false;
        }
    }

    private boolean validateCVV(){
        String cvv = cvvTextField.getText();
        if(cvv.matches("\\d{3}")){
            return true;
        } else{
            System.out.println("Inserisci un CVV valido");
            return false;
        }
    }


    public class DebugUtils {
        public static void printFields(Object obj) {
            if (obj == null) {
                System.out.println("Oggetto nullo");
                return;
            }

            Class<?> clazz = obj.getClass();
            System.out.println("Stampo i campi di: " + clazz.getSimpleName());

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true); // Permette di accedere anche a campi privati
                try {
                    Object value = field.get(obj);
                    System.out.println(field.getName() + " = " + value);
                } catch (IllegalAccessException e) {
                    System.out.println(field.getName() + " = accesso negato");
                }
            }
        }
    }


}
