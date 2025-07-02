package com.example.racemania.view1;

import java.lang.reflect.Field;

import com.example.racemania.controller.BookLapsReservationController;
import com.example.racemania.exceptions.FailedInsertException;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class PaymentPageControllerG {
    TrackLapsReservationBean actualLapsReservationBean;
    private int OrderTotal;

    public void setTrackLapsReservationBean(TrackLapsReservationBean trackLapsReservationBean) {
        this.actualLapsReservationBean = trackLapsReservationBean;
        OrderTotal = actualLapsReservationBean.getPrice();
        updateTotalTextFlow();
    }

    public void updateTotalTextFlow(){
        totalPriceTextFlow.getChildren().clear();
        Text info = new Text(OrderTotal+" €");
        info.setFont(Font.font("Arial", 28));
        info.setId("info");
        totalPriceTextFlow.getChildren().add(info);
    }

    @FXML
    private Label errorLabel;

    @FXML
    private TextField cardNumberTextField;

    @FXML
    private TextField cardExpireDateTextField;

    @FXML
    private TextField cardOwnerTextFIeld;

    @FXML
    private TextFlow totalPriceTextFlow;

    @FXML
    private Button payButton;

    @FXML
    private TextField cvvTextField;

    @FXML
    void clickedOnPayButton(ActionEvent event) {
        if(validateCardNumber() && validateCardExpireDate() && validateCardOwner() && validateCVV()){
            BookLapsReservationController bookLapsReservationController = new BookLapsReservationController();
            try {
                bookLapsReservationController.insertLapsReservation(actualLapsReservationBean);
            } catch (FailedInsertException _){
                // to be handled
            }
            FxmlLoader.setPage("ConfirmationPage");
        }
    }

    private boolean validateCardNumber(){
        String cardnumber = cardNumberTextField.getText();
        if(cardnumber.matches("\\d{16}" /*"\\d{4} \\d{4} \\d{4} \\d{4}"*/)){
            return true;
        } else{
            errorLabel.setText("Inserisci Numero di carta valido nel formato: xxxx xxxx xxxx xxxx");
            return false;
        }
    }

    private boolean validateCardOwner(){
        String cardowner = cardOwnerTextFIeld.getText();
        if(cardowner.matches("[a-zA-Z\\s]+")){
            return true;
        } else{
            errorLabel.setText("Inserisci Nome proprietario valido");
            return false;
        }
    }

    private boolean validateCardExpireDate(){
        String expireDate = cardExpireDateTextField.getText();
        if(expireDate.matches("\\d{2}/\\d{2}")){
            return true;
        } else{
            errorLabel.setText("Formato data scadenza non valido:  Usa 'mm/aa'");
            return false;
        }
    }

    private boolean validateCVV(){
        String cvv = cvvTextField.getText();
        if(cvv.matches("\\d{3}")){
            return true;
        } else{
            errorLabel.setText("Inserisci CVV valido");
            return false;
        }
    }

}
