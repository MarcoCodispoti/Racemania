package com.example.racemania.view2;

import com.example.racemania.controller.ReservationsHistoryController;
import com.example.racemania.model.LoggedUser;
import com.example.racemania.model.bean.UserBean;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class ProfilePageControllerG2 {
    ReservationsHistoryController reservationsHistoryController = new ReservationsHistoryController();

    @FXML
    private Label usernameLabel, emailLabel, roleLabel, userIdLabel, errorLabel;

    @FXML
    private Hyperlink homepageHyperlink;

    @FXML
    private Hyperlink circuitsHyperlink;

    @FXML
    private Hyperlink reservationsHyperlink;

    @FXML
    public void ClickedOnHomePageHyperlink(){
        FxmlLoader2.setPage("HomePage2");
    }

    @FXML
    public void ClickedOnCircuitsHyperlink(){
        errorLabel.setText("Not implemented yet");
    }

    @FXML
    public void ClickedOnReservationsHyperlink(){
        FxmlLoader2.setPage("ReservationsPage2");
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
                System.out.println("userBean is null");
            }
        } catch (Exception e) {
            System.out.println("userBean is null");
        }


        usernameLabel.setText(userBean.getUserName());
        userIdLabel.setText(""+userBean.getUserId());
        roleLabel.setText(userRole);
        emailLabel.setText(userBean.getEmail());
    }
}
