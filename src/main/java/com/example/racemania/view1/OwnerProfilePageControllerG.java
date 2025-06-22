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
    private Label ErrorLabel;

    @FXML
    private Label UsernameLabel;

    @FXML
    private Label EmailLabel;

    @FXML
    private Label UserIdLabel;

    @FXML
    private Label RoleLabel;

    @FXML
    private Hyperlink HomepageHyperlink;

    @FXML
    private Hyperlink InstructorsHyperlink;


    @FXML
    private void ClickedOnInstructorsHyperlink(ActionEvent event){
        ErrorLabel.setText("Not implemented sorry");
        ErrorLabel.setVisible(true);
    }

    @FXML
    private void ClickedOnHomepageHyperlink(ActionEvent event){
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
                System.out.println("userBean is null");
            }
        } catch (Exception e) {
            System.out.println("userBean is null");
        }

        UsernameLabel.setText(userBean.getUserName());
        UserIdLabel.setText(""+userBean.getUserId());
        RoleLabel.setText(userRole);
        EmailLabel.setText(userBean.getEmail());
    }
}
