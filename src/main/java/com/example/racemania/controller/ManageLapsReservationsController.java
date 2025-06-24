package com.example.racemania.controller;

import com.example.racemania.model.LoggedUser;
import com.example.racemania.model.TrackLapsReservation;
import com.example.racemania.model.dao.UserDao;
import com.example.racemania.model.dao.TrackLapsReservationDao;
import com.example.racemania.model.dao.VehicleDao;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import com.example.racemania.model.bean.UserBean;
import com.example.racemania.model.bean.VehicleBean;

import java.sql.SQLException;
import java.util.List;

public class ManageLapsReservationsController {

    public TrackLapsReservationBean getOwnerLapsReservation() throws SQLException{
        int trackId = LoggedUser.getInstance().getTrackOwner().getTrackId();

        TrackLapsReservationBean trackLapsReservationBean = new TrackLapsReservationBean();
        TrackLapsReservationDao trackLapsReservationDao = new TrackLapsReservationDao();

        try {
            trackLapsReservationBean.setOwnerTrackLapsReservations(trackLapsReservationDao.findOwnerLapsReservations(trackId));
        }
        catch (SQLException e){
            throw new SQLException(e);
        }

        List<TrackLapsReservation> trackLapsReservations = trackLapsReservationBean.getOwnerTrackLapsReservations();
        if (trackLapsReservations == null || trackLapsReservations.isEmpty()) {
            return null;
        }

        return trackLapsReservationBean;
    }


    public void manageLapsReservation(int reservationID,String decision) throws SQLException {
        TrackLapsReservationDao trackLapsReservationDao = new TrackLapsReservationDao();
        try {
            trackLapsReservationDao.manageLapsReservation(reservationID,decision);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }


    public VehicleBean getVehicle(String vehiclePlate) throws SQLException {
        VehicleBean vehicleBean;
        VehicleDao vehicleDao = new VehicleDao();

        vehicleBean = vehicleDao.getVehicleFromDB(vehiclePlate);
        return vehicleBean;
    }


    public TrackLapsReservationBean getActiveOwnerLapsReservation() throws SQLException{
        int trackId = LoggedUser.getInstance().getTrackOwner().getTrackId();

        TrackLapsReservationBean trackLapsReservationBean = new TrackLapsReservationBean();
        TrackLapsReservationDao trackLapsReservationDao = new TrackLapsReservationDao();

        try {
            trackLapsReservationBean.setOwnerActiveTrackLapsReservations(trackLapsReservationDao.findOwnerActiveLapsReservations(trackId));
        }
        catch (SQLException e){
            throw new SQLException(e);
        }

        List<TrackLapsReservation> trackLapsReservations = trackLapsReservationBean.getOwnerActiveTrackLapsReservations();
        if (trackLapsReservations == null || trackLapsReservations.isEmpty()) {
            return null;
        }

        return trackLapsReservationBean;
    }


    public UserBean getUser(int userId) throws SQLException {
        UserBean userBean = null;
        UserDao userDao = new UserDao();

        try {
            userBean = userDao.getUserFromDB(userId);
        }
        catch (SQLException _){
            // to be handled
        }
        return userBean;
    }

}
