package com.example.racemania.view2;

import com.example.racemania.controller.LoginController;
import com.example.racemania.exceptions.FailedFileAccessException;
import com.example.racemania.model.LoggedUser;
import com.example.racemania.model.bean.AccountBean;
import com.example.racemania.model.bean.LoginBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

public class LoginPageControllerG2 {
    LoginController loginController = new LoginController();
    AccountBean accountBean = new AccountBean();

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorLabel;

    @FXML
    private void clickedOnLoginButton() {
        if (checkFormats()) {                                     // controllo se i formati inseriti siano validi
            AccountBean actualaccountBean = getAccountBean();
            if (actualaccountBean != null) {
                checkAccount();
            }
        }
    }

    private void checkAccount(){
        if (LoggedUser.getInstance().getCustomer() != null) {             // doppio controllo
            if (LoggedUser.getInstance().getRole().equals("CUSTOMER")) {
                FxmlLoader2.setPage("HomePage2");
            }
        } else if (LoggedUser.getInstance().getTrackOwner() != null) {
            if (LoggedUser.getInstance().getRole().equals("TRACK_OWNER")) {
                FxmlLoader2.setPage("OwnerHomePage2");
            }
        } else {
            setErrorLabel("Errore di inizializzazione dell'utente loggato");
        }
    }

        public boolean checkFormats (){
            String email = emailTextField.getText();
            String password = passwordTextField.getText();

            if (email.isEmpty()) {
                setErrorLabel("Campo email vuoto");
                return false;
            }
            if (password.isEmpty()) {
                setErrorLabel("Campo password vuoto");
                return false;
            }

            if (!isValidEmail(email)) {
                setErrorLabel("Campo email non valido");
                return false;
            }
            if (password.length() < 8) {
                setErrorLabel("La password deve essere lunga almeno 8 caratteri");
                return false;
            }

            return true;
        }

        private boolean isValidEmail (String email){
            return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        }

        public void setErrorLabel (String error){
            errorLabel.setText(error);
            errorLabel.setVisible(true);
        }



    public AccountBean getAccountBean(){

        if (!checkFormats()){
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

}


