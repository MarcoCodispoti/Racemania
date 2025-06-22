package com.example.racemania.view1;

import com.example.racemania.model.LoggedUser;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeControllerG {
    TrackLapsReservationBean actualLapsReservationBean;

    public void setTrackLapsReservationBean(TrackLapsReservationBean trackLapsReservationBean) {
        this.actualLapsReservationBean = trackLapsReservationBean;
    }

    @FXML
    private Hyperlink LogoutHyperlink;

    @FXML
    private Hyperlink ReservationsHyperlink;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button BookTDButton;

    @FXML
    private Button DriveSupercarButton;

    @FXML
    private Hyperlink ProfileHyperlink;


    public void ClickedOnBookTrackDay(ActionEvent event) throws IOException {      //il comando è collegato al bottone dal file FXML
        actualLapsReservationBean = new TrackLapsReservationBean();

//        if(actualLapsReservationBean.getUserID()==0 ){
//            actualLapsReservationBean.setUserID(LoggedUser.getInstance().getCustomer().getUserId());
//        }


        TrackPageControllerG controller = FxmlLoader.setPageAndReturnController("TrackPage");
        controller.setTrackLapsReservationBean(actualLapsReservationBean);                        //Comando per cambiare pagina
    }

    @FXML
    public void ClickedOnReservationsHyperlink(ActionEvent event) throws IOException {
        FxmlLoader.setPage("ActiveReservationsPage");
    }

    @FXML
    public void ClickedOnProfileHyperlink(ActionEvent event) throws IOException {
        FxmlLoader.setPage("ProfilePage");
    }

    @FXML
    public void ClickedOnLogoutHyperlink(ActionEvent event) throws IOException {
        FxmlLoader.setPage("LoginPage");
    }

}
