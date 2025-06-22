package com.example.racemania.view1;

import com.example.racemania.controller.BookLapsReservationController;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.time.LocalDate;

public class DatePageControllerG {
    BookLapsReservationController bookLapsReservationController;
    TrackLapsReservationBean actualLapsReservationBean;

    public void setTrackLapsReservationBean(TrackLapsReservationBean trackLapsReservationBean) {
        this.actualLapsReservationBean = trackLapsReservationBean;
    }

    @FXML
    private DatePicker reservationDatePicker;

    @FXML
    private Button ProceedButton;

    @FXML
    void ClickedOnProceed(ActionEvent event) throws IOException {        //il comando è collegato al bottone dal file FXML
                                                                         //Comando per cambiare pagina
        LocalDate reservationDate = reservationDatePicker.getValue();
        if(isValidDate(reservationDate)){
            actualLapsReservationBean.setDate(reservationDate);

            //FxmlLoader.setPage("TicketPage");
            TicketPageControllerG controller = FxmlLoader.setPageAndReturnController("TicketPage");
            controller.setTrackLapsReservationBean(actualLapsReservationBean);
        }
    }

    private boolean isValidDate(LocalDate reservationDate){
        if(reservationDate==null){
            System.out.println("Scegli una data");
            return false;
        }
        else if(reservationDate.isBefore(LocalDate.now()) || reservationDate.isEqual(LocalDate.now())){
            System.out.println("Scegli una data successiva ad oggi");
            return false;
        } else {
            System.out.println("Hai scelto la data: " + reservationDate);
            return true;
        }
    }

}
