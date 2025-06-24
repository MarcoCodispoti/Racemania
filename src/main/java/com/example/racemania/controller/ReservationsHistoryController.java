package com.example.racemania.controller;

import com.example.racemania.model.UserDao;
import com.example.racemania.model.dao.TrackDao;
import com.example.racemania.model.dao.VehicleDao;
import com.example.racemania.model.bean.TrackBean;
import com.example.racemania.model.bean.UserBean;
import com.example.racemania.model.bean.VehicleBean;

import java.sql.SQLException;

public class ReservationsHistoryController {

public VehicleBean getVehicle(String vehiclePlate) throws SQLException {
    VehicleBean vehicleBean;
    VehicleDao vehicleDao = new VehicleDao();

    vehicleBean = vehicleDao.getVehicleFromDB(vehiclePlate);
    return vehicleBean;
}

    public TrackBean getLapsReservationTrack(int reservationTrackId) throws SQLException {
        TrackBean trackBean = null;
        TrackDao trackDao = new TrackDao();

        try {
            trackBean = trackDao.getTrackFromDB(reservationTrackId);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return trackBean;
    }

    public UserBean getUser(int userId) throws SQLException {
        UserBean userBean = null;
        UserDao userDao = new UserDao();

        try {
            userBean = userDao.getUserFromDB(userId);
        }
        catch (SQLException e){
            throw new SQLException(e);
        }
        return userBean;
    }

}
