package com.example.racemania.view1;

import com.example.racemania.controller.ReservationsHistoryController;
import com.example.racemania.model.LoggedUser;
import com.example.racemania.model.bean.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class OwnerProfilePageControllerG {
    ReservationsHistoryController reservationsHistoryController = new ReservationsHistoryController();

    @FXML
    private Label errorLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label userIdLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Hyperlink homepageHyperlink;

    @FXML
    private Hyperlink instructorsHyperlink;


    @FXML
    private void clickedOnInstructorsHyperlink(ActionEvent event){
        errorLabel.setText("Not implemented sorry");
        errorLabel.setVisible(true);
    }

    @FXML
    private void clickedOnHomepageHyperlink(ActionEvent event){
        FxmlLoader.setPage("OwnerHomePage");
    }

    public void initialize(){
        UserBean userBean = new UserBean();
        String userRole = null;

        try {
            if (LoggedUser.getInstance().getCustomer() != null) {
                userBean = reservationsHistoryController.getUser(LoggedUser.getInstance().getCustomer().getUserId());
                userRole = "Customer";
            } else {
                userBean = reservationsHistoryController.getUser(LoggedUser.getInstance().getTrackOwner().getUserId());
                userRole = "TrackOwner";
            }
            if(userBean == null){
                // userBean is null  // to be handled
                System.out.println("userBean is null");
            }
        } catch (Exception _) {
            // userBean is null // to be handled
        }
        usernameLabel.setText(userBean.getUserName());
        userIdLabel.setText(""+userBean.getUserId());
        roleLabel.setText(userRole);
        emailLabel.setText(userBean.getEmail());
    }
}
