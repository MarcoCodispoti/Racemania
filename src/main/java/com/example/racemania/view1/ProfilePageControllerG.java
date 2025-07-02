package com.example.racemania.view1;

import com.example.racemania.controller.ReservationsHistoryController;
import com.example.racemania.model.LoggedUser;
import com.example.racemania.model.bean.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class ProfilePageControllerG {
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
   private Hyperlink reservationslink;

   @FXML
    private Hyperlink circuitsHyperlink;

   @FXML
    private void clickedOnReservationsHyperlink(ActionEvent event){
       FxmlLoader.setPage("ActiveReservationsPage");
   }

   @FXML
    private void clickedOnCircuitsHyperlink(ActionEvent event){
       errorLabel.setText("Not implemented sorry");
       errorLabel.setVisible(true);
   }

   @FXML
    private void clickedOnHomepageHyperlink(ActionEvent event) {

       if (LoggedUser.getInstance().getCustomer() != null) {
           FxmlLoader.setPage("HomePage");
       } else if(LoggedUser.getInstance().getTrackOwner() != null) {
           FxmlLoader.setPage("OwnerHomePage");
       }
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
               // da gestire
           }
       } catch (Exception _) {
           // da gestire dopo
       }

       usernameLabel.setText(userBean.getUserName());
       userIdLabel.setText(""+userBean.getUserId());
       roleLabel.setText(userRole);
       emailLabel.setText(userBean.getEmail());
   }



}
