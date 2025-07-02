package com.example.racemania.view1;

import com.example.racemania.model.bean.TrackLapsReservationBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;

public class HomeControllerG {
    TrackLapsReservationBean actualLapsReservationBean;

    public void setTrackLapsReservationBean(TrackLapsReservationBean trackLapsReservationBean) {
        this.actualLapsReservationBean = trackLapsReservationBean;
    }

    @FXML
    private Hyperlink logoutHyperlink;

    @FXML
    private Hyperlink reservationsHyperlink;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button bookTDButton;

    @FXML
    private Button driveSupercarButton;

    @FXML
    private Hyperlink profileHyperlink;


    public void clickedOnBookTrackDay(ActionEvent event){      //il comando è collegato al bottone dal file FXML
        actualLapsReservationBean = new TrackLapsReservationBean();


        TrackPageControllerG controller = FxmlLoader.setPageAndReturnController("TrackPage");
        controller.setTrackLapsReservationBean(actualLapsReservationBean);                        //Comando per cambiare pagina
    }

    @FXML
    public void clickedOnReservationsHyperlink(ActionEvent event){
        FxmlLoader.setPage("ActiveReservationsPage");
    }

    @FXML
    public void clickedOnProfileHyperlink(ActionEvent event){
        FxmlLoader.setPage("ProfilePage");
    }

    @FXML
    public void clickedOnLogoutHyperlink(ActionEvent event){
        FxmlLoader.setPage("LoginPage");
    }

}
