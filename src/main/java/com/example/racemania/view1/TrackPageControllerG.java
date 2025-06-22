package com.example.racemania.view1;


import com.example.racemania.controller.BookLapsReservationController;
import com.example.racemania.model.Track;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import com.example.racemania.model.bean.TrackBean;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

public class TrackPageControllerG {

    private BookLapsReservationController bookLapsReservationController;
    private Track selectedTrack;
    private Parent selectedCardUI;
    private TrackLapsReservationBean actualLapsReservationBean;

    @FXML
    private Button ProceedButton;

    @FXML
    private VBox TrackVBox;

    @FXML
    private ScrollPane TrackScrollPane;

    public void setTrackLapsReservationBean(TrackLapsReservationBean trackLapsReservationBean) {
        this.actualLapsReservationBean = trackLapsReservationBean;
    }


    public void ClickedOnProceed(ActionEvent event) throws IOException {      //il comando è collegato al bottone dal file FXML
        if (selectedTrack == null) {
            System.out.println("Seleziona una pista prima di proseguire!");
            return;
        }


        System.out.println("Hai scelto: " + selectedTrack.getName());
        bookLapsReservationController.saveTrackDetails(selectedTrack,actualLapsReservationBean);

        //FxmlLoader.setPage("VehiclePage");                          //Comando per cambiare pagina
        VehiclePageControllerG controller = FxmlLoader.setPageAndReturnController("VehiclePage");
        controller.setTrackLapsReservationBean(actualLapsReservationBean);

    }


    public void populateTracks(List<Track> trackList ) {
        TrackVBox.getChildren().clear();

        for (Track track : trackList) {
            try {
                System.out.println("Sto caricando una TrackCard per: " + track.getName());

                FXMLLoader cardloader = new FXMLLoader(getClass().getResource("/com/example/racemania/view1/TrackCard.fxml"));
                Parent trackCard = cardloader.load();

                System.out.println("TrackCard.fxml caricata con successo");

                TrackCardControllerG controller = cardloader.getController();
                controller.setData(track);
                controller.setCardUI(trackCard);
                controller.setParentController(this);

                TrackVBox.getChildren().add(trackCard);

            } catch (IOException e) {
                System.out.println("ERRORE nel caricamento TrackCard.fxml");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Errore generico:");
                e.printStackTrace();
            }
        }
    }


    public void setSelectedTrack(Track track, Parent cardUI) {
        this.selectedTrack = track;

        // resetta evidenziazione su vecchia card (se c'è)
        if (selectedCardUI != null) {
            selectedCardUI.setStyle(""); // oppure stile di default
        }

        // evidenzia la nuova card selezionata
        selectedCardUI = cardUI;
        selectedCardUI.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-border-radius: 10;");
    }


    public void initialize(){
        TrackBean availableTracksBean;
        bookLapsReservationController = new BookLapsReservationController();
        List<Track> trackList = null;

        try {
            availableTracksBean = bookLapsReservationController.getAvailableTracks();
            trackList = availableTracksBean.getAvailableTracks();
        }
        catch (Exception e) {
            // to be handled
        }

        if (trackList == null || trackList.isEmpty()) {
            System.out.println("Nessun circuito trovato nel database.");
            return;
        }
        System.out.println("Caricati " + trackList.size() + " tracciati dal TrackBean.");

        populateTracks(trackList);
    }



}
