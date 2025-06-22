package com.example.racemania.controller;

import com.example.racemania.model.LoggedUser;
import com.example.racemania.model.TrackLapsReservation;
import com.example.racemania.model.UserDao;
import com.example.racemania.model.dao.TrackLapsReservationDao;
import com.example.racemania.model.dao.VehicleDao;
import com.example.racemania.model.bean.TrackLapsReservationBean;
import com.example.racemania.model.bean.UserBean;
import com.example.racemania.model.bean.VehicleBean;

import java.sql.SQLException;
import java.util.List;

public class ManageLapsReservationsController {

    // usata da TrackOwner
    public TrackLapsReservationBean getOwnerLapsReservation() throws SQLException{
        System.out.println("prendo Track");
        int trackId = LoggedUser.getInstance().getTrackOwner().getTrackId();
        System.out.println("TrackId: " + trackId);

        TrackLapsReservationBean trackLapsReservationBean = new TrackLapsReservationBean();
        TrackLapsReservationDao trackLapsReservationDao = new TrackLapsReservationDao();

        try {
            trackLapsReservationBean.setOwnerTrackLapsReservations(trackLapsReservationDao.findOwnerLapsReservations(trackId));
        }
        catch (SQLException e){
            throw new SQLException();
        }

        List<TrackLapsReservation> trackLapsReservations = trackLapsReservationBean.getOwnerTrackLapsReservations();
        if (trackLapsReservations == null || trackLapsReservations.isEmpty()) {
            System.out.println("Nessun circuito trovato nel database.");
            return null;
        }

        return trackLapsReservationBean;
    }

    // Usata da owner
    public void manageLapsReservation(int reservationID,String decision) throws SQLException {
        TrackLapsReservationDao trackLapsReservationDao = new TrackLapsReservationDao();
        try {
            trackLapsReservationDao.ManageLapsReservation(reservationID,decision);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    // Usata sia da owner che da Customer
    public VehicleBean getVehicle(String vehiclePlate) throws SQLException {
        VehicleBean vehicleBean;
        VehicleDao vehicleDao = new VehicleDao();

        vehicleBean = vehicleDao.getVehicleFromDB(vehiclePlate);
        return vehicleBean;
    }


    public TrackLapsReservationBean getActiveOwnerLapsReservation() throws SQLException{
        // devo modificare questa
        System.out.println("prendo Track");
        int trackId = LoggedUser.getInstance().getTrackOwner().getTrackId();
        System.out.println("TrackId: " + trackId);

        TrackLapsReservationBean trackLapsReservationBean = new TrackLapsReservationBean();
        TrackLapsReservationDao trackLapsReservationDao = new TrackLapsReservationDao();

        try {
            trackLapsReservationBean.setOwnerActiveTrackLapsReservations(trackLapsReservationDao.findOwnerActiveLapsReservations(trackId));
        }
        catch (SQLException e){
            throw new SQLException();
        }

        List<TrackLapsReservation> trackLapsReservations = trackLapsReservationBean.getOwnerActiveTrackLapsReservations();
        if (trackLapsReservations == null || trackLapsReservations.isEmpty()) {
            System.out.println("Nessuna prenotazione trovata nel database.");
            return null;
        }

        return trackLapsReservationBean;
    }

    // Usata da TrackOwner
    public UserBean getUser(int userId) throws SQLException {
        UserBean userBean = null;
        UserDao userDao = new UserDao();

        try {
            userBean = userDao.getUserFromDB(userId);
        }
        catch (SQLException e){
            // to be handled
        }
        return userBean;
    }

}
