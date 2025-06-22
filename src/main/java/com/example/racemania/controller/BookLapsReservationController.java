package com.example.racemania.controller;

import com.example.racemania.exceptions.FailedResearchException;
import com.example.racemania.exceptions.FailedInsertException;
import com.example.racemania.model.LoggedUser;
import com.example.racemania.model.Track;
// import com.example.racemania.model.TrackLapsReservation;
import com.example.racemania.model.dao.TrackDao;
import com.example.racemania.model.dao.TrackLapsReservationDao;
import com.example.racemania.model.dao.VehicleDao;
import com.example.racemania.model.bean.TrackBean;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import com.example.racemania.model.bean.VehicleBean;

import java.sql.SQLException;

public class BookLapsReservationController{

    public TrackBean getAvailableTracks() throws FailedResearchException {
        TrackBean bean = new TrackBean();
        TrackDao trackDao = new TrackDao();
        try {
            bean.setAvailableTracks(trackDao.findTracks()); // salva nel campo della classe
        } catch (FailedResearchException e) {
            throw new FailedResearchException("Can't get available tracks");
        }
        return bean;
    }

    public void saveTrackDetails(Track selectedTrack, TrackLapsReservationBean selectedLapsReservationBean) {
        selectedLapsReservationBean.setTrackID(selectedTrack.getTrackId());
        selectedLapsReservationBean.setLapPrice(selectedTrack.getLapPrice());
        selectedLapsReservationBean.setDailyPrice(selectedTrack.getDailyPrice());
    }

    public void insertVehicle(VehicleBean vehicleBean) throws FailedInsertException {
        try {
            VehicleDao vehicleDao = new VehicleDao();
            vehicleDao.insertVehicle(vehicleBean);
        } catch (FailedInsertException e) {
            throw new FailedInsertException("Can't insert vehicle");
        }
    }

    public void setTicketInfo(TrackLapsReservationBean trackLapsReservationBean,boolean isDaily, int lapPrice,int total, int laps ) throws FailedInsertException{
        trackLapsReservationBean.setIsDaily(isDaily);
        trackLapsReservationBean.setLapPrice(lapPrice);
        trackLapsReservationBean.setLaps(laps);
        trackLapsReservationBean.setPrice(total);
    }


    public void insertLapsReservation(TrackLapsReservationBean trackLapsReservationBean) throws SQLException {
        // TrackLapsReservation trackLapsReservation = new TrackLapsReservation();

        if(LoggedUser.getInstance().getCustomer().getUserId() != 0) {
            trackLapsReservationBean.setUserID(LoggedUser.getInstance().getCustomer().getUserId());
        } else {
            throw new FailedInsertException("Can't insert laps reservation");
        }
        trackLapsReservationBean.setConfirmationStatus("Waiting for confirmation");

        System.out.println("ho creato l'istanza di trackLapsReservation \n");

        System.out.println("Strampo i valori del bean");

        System.out.println("Ho eseguito fill lapsReservation \n \n Stampo i valor della trackLapsReservation \n");
        // StampaValoriReservation(trackLapsReservation);

        System.out.println("Creo l'istanza della DAO");
        TrackLapsReservationDao trackLapsReservationDao = new TrackLapsReservationDao();
        System.out.println("Ho creato l'istanza di TrackLapsReservation \n");

        System.out.println("Faccio partire la funzione DAO che inserisce i valori nel database \n");
        // trackLapsReservationDao.insertTrackLapsReservation(trackLapsReservation);   //L'errore è qui
        trackLapsReservationDao.insertTrackLapsReservation(trackLapsReservationBean);

        System.out.println("La DAO ha inserito i valori nel database\n");
    }

}
