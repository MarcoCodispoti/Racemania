package com.example.racemania.view1;

import com.example.racemania.controller.LoginController;
import com.example.racemania.exceptions.FailedFileAccessException;
import com.example.racemania.model.LoggedUser;
import com.example.racemania.model.bean.AccountBean;
import com.example.racemania.model.bean.LoginBean;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

import static java.lang.Thread.sleep;


public class LoginPageControllerG {

    @FXML
    private Label errorLabel;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private Button loginButton;

    AccountBean accountBean = new AccountBean();

    private final LoginController loginController = new LoginController();
    private int accountType = -1; // 0 = customer, 1 = trackowner

    TrackLapsReservationBean trackLapsReservationBean;


    public AccountBean getAccountBean(){
        if (!checkFormats()) {
            return null;
        }

        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        try {
            accountBean = loginController.authenticate(email, password);
        } catch (FailedLoginException | FailedFileAccessException _) {
            setErrorLabel("Credenziali errate");
            return null;
        } catch (SQLException _) {
            return null;
        }

        return accountBean;
    }


    @FXML
    public void clickedOnLogin(ActionEvent event){
        if(checkFormats()){                                     // controllo se i formati inseriti siano validi
            AccountBean actualaccountBean = getAccountBean();
            if (actualaccountBean != null) {  // l'account ottenuto non è nullo
                checkAccount();
            }
        }

    }


    public void checkAccount(){
        if(LoggedUser.getInstance().getCustomer() != null){
            if(LoggedUser.getInstance().getRole().equals("CUSTOMER")){
                FxmlLoader.setPage("HomePage");
            }
        }
        else if(LoggedUser.getInstance().getTrackOwner() != null){
            if(LoggedUser.getInstance().getRole().equals("TRACK_OWNER")){
                FxmlLoader.setPage("OwnerHomePage");
            }
        }
        else {
            setErrorLabel("C'è stato un errrore");
        }
    }


    public void setErrorLabel(String error) {
        errorLabel.setText(error);
        errorLabel.setVisible(true);
    }

    public boolean checkFormats(){

        if(emailTextField.getText().isEmpty()){ setErrorLabel("Campo email vuoto");
            return false;
        }
        if(passwordTextField.getText().isEmpty()){ setErrorLabel("Campo password vuoto");
            return false;
        }

        if(!isValidEmail(emailTextField.getText())){ setErrorLabel("Campo email non valido");
            return false;
        }
        if(passwordTextField.getText().length() < 8){ setErrorLabel("La password deve essere lunga almeno 8 caratteri");
            return false;
        }

        return true;
    }


    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }



}
