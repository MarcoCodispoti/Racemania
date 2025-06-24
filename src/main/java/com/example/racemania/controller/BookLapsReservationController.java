package com.example.racemania.controller;

import com.example.racemania.exceptions.FailedResearchException;
import com.example.racemania.exceptions.FailedInsertException;
import com.example.racemania.model.LoggedUser;
import com.example.racemania.model.Track;
import com.example.racemania.model.dao.TrackDao;
import com.example.racemania.model.dao.TrackLapsReservationDao;
import com.example.racemania.model.dao.VehicleDao;
import com.example.racemania.model.bean.TrackBean;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import com.example.racemania.model.bean.VehicleBean;


public class BookLapsReservationController{

    public TrackBean getAvailableTracks() throws FailedResearchException {
        TrackBean bean = new TrackBean();
        TrackDao trackDao = new TrackDao();
        try {
            bean.setAvailableTracks(trackDao.findTracks()); // salva nel campo della classe
        } catch (FailedResearchException _) {
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
        } catch (FailedInsertException _) {
            throw new FailedInsertException("Can't insert vehicle");
        }
    }

    public void setTicketInfo(TrackLapsReservationBean trackLapsReservationBean,boolean isDaily, int lapPrice,int total, int laps ) throws FailedInsertException{
        trackLapsReservationBean.setIsDaily(isDaily);
        trackLapsReservationBean.setLapPrice(lapPrice);
        trackLapsReservationBean.setLaps(laps);
        trackLapsReservationBean.setPrice(total);
    }


    public void insertLapsReservation(TrackLapsReservationBean trackLapsReservationBean) throws FailedInsertException {

        if(LoggedUser.getInstance().getCustomer().getUserId() != 0) {
            trackLapsReservationBean.setUserID(LoggedUser.getInstance().getCustomer().getUserId());
        } else {
            throw new FailedInsertException("Can't insert laps reservation");
        }
        trackLapsReservationBean.setConfirmationStatus("Waiting for confirmation");
        TrackLapsReservationDao trackLapsReservationDao = new TrackLapsReservationDao();
        trackLapsReservationDao.insertTrackLapsReservation(trackLapsReservationBean);
    }

}
