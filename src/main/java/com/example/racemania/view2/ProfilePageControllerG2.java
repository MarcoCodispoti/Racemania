package com.example.racemania.view2;

import com.example.racemania.controller.ReservationsHistoryController;
import com.example.racemania.model.LoggedUser;
import com.example.racemania.model.bean.UserBean;
import com.example.racemania.view1.FxmlLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class ProfilePageControllerG2 {
    ReservationsHistoryController reservationsHistoryController = new ReservationsHistoryController();

    @FXML
    private Label usernameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Label userIdLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private Hyperlink homepageHyperlink;

    @FXML
    private Hyperlink circuitsHyperlink;

    @FXML
    private Hyperlink reservationsHyperlink;

    @FXML
    public void clickedOnHomePageHyperlink(){
        if (LoggedUser.getInstance().getCustomer() != null) {
            FxmlLoader2.setPage("HomePage2");
        } else if(LoggedUser.getInstance().getTrackOwner() != null) {
            FxmlLoader2.setPage("OwnerHomePage2");
        }
    }


    public void initialize(){
        UserBean userBean;
        String userRole;

        try {
            if (LoggedUser.getInstance().getCustomer() != null) {
                userBean = reservationsHistoryController.getUser(LoggedUser.getInstance().getCustomer().getUserId());
                userRole = "Customer";
            } else {
                userBean = reservationsHistoryController.getUser(LoggedUser.getInstance().getTrackOwner().getUserId());
                userRole = "TrackOwner";
            }
            if(userBean == null){
               return;
            }
        } catch (Exception _) {
            return;
        }


        usernameLabel.setText(userBean.getUserName());
        userIdLabel.setText(""+userBean.getUserId());
        roleLabel.setText(userRole);
        emailLabel.setText(userBean.getEmail());
    }
}
