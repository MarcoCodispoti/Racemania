package com.example.racemania.model.service;


import com.example.racemania.model.bean.TrackLapsReservationBean;
import com.example.racemania.model.bean.VehicleBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

public class Query {

    public static ResultSet findTracksq() throws SQLException{
        Connection conn = Connector.getInstance().getConnection();
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        return stmt.executeQuery("select * from track");
    }



    public static void insertTrackLapsReservation(Statement stmt, TrackLapsReservationBean trackLapsReservationBean) throws SQLException {
        System.out.println("Inserito la trackLapsReservation della query \n");

        String formattedDateTime = trackLapsReservationBean.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int isDaily = trackLapsReservationBean.getIsDaily() ? 1 : 0;  //ricontrollare poi
        System.out.println("Chiamato il metodo insertTrackLapsReservation");
        String selectedStatement = String.format("INSERT INTO TrackLapsReservation (user_id, reservation_date, track_id, vehicle_plate, price, laps, is_daily, lap_price,daily_price,confirmation_status) " + "VALUES (%d, '%s', %d, '%s', %d, %d, %d, %d, %d,'%s')"
                , trackLapsReservationBean.getUserID(),formattedDateTime,trackLapsReservationBean.getTrackID(),trackLapsReservationBean.getVehiclePlate(), trackLapsReservationBean.getPrice(),trackLapsReservationBean.getLaps(),
                isDaily,trackLapsReservationBean.getLapPrice(),trackLapsReservationBean.getDailyPrice(),trackLapsReservationBean.getConfirmationStatus());
        System.out.println("SQL Query: " + selectedStatement);
        stmt.executeUpdate(selectedStatement);
    }


    public static void insertVehicle(Statement stmt, VehicleBean vehicleBean) throws SQLException {
        String selectedStatement = String.format("INSERT INTO Vehicle (plate, owner_id, brand, model, immatriculation_year, power, lastcheck_year) " + "VALUES ('%s',%d, '%s', '%s', %d, %d, %d)"
                ,vehicleBean.getPlate(), vehicleBean.getOwnerId(),vehicleBean.getBrand(),vehicleBean.getModel(),vehicleBean.getImmatriculationYear(), vehicleBean.getPower(),vehicleBean.getLastcheckYear());
        // correggre i placeholder e inserire i parametri
        stmt.executeUpdate(selectedStatement);
    }


    public static ResultSet checkCredentials(Statement stmt, String email, String password) throws SQLException {
        String query = "SELECT user_id,user_role,track_id FROM Users WHERE email = '" + email + "' AND user_password = '" + password + "'";
        System.out.println(query);
        return stmt.executeQuery(query);
    }

    public static ResultSet findLapsReservations(int userId) throws SQLException{
        Connection conn = Connector.getInstance().getConnection();
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM TrackLapsReservation WHERE user_id = '" + userId + "'";
        return stmt.executeQuery(query);
    }

    public static ResultSet findOwnerLapsReservations(int trackId) throws SQLException{
        Connection conn = Connector.getInstance().getConnection();
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM TrackLapsReservation WHERE track_id = '" + trackId + "'";
        return stmt.executeQuery(query);
    }

    public static ResultSet findOwnerActiveLapsReservations(int trackId) throws SQLException{
        Connection conn = Connector.getInstance().getConnection();
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM TrackLapsReservation WHERE track_id = '" + trackId + "' AND confirmation_status = 'Confirmed' ";
        return stmt.executeQuery(query);
    }

    public static ResultSet findTrackInfo(int trackId) throws SQLException{
        Connection conn = Connector.getInstance().getConnection();
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Track WHERE track_id = '" + trackId + "'";
        return stmt.executeQuery(query);
    }

    public static ResultSet findUserInfo(int userId) throws SQLException{
        Connection conn = Connector.getInstance().getConnection();
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT username,email FROM Users WHERE user_id = '" + userId + "'";
        return stmt.executeQuery(query);
    }

    public static ResultSet findVehicleInfo(String plate) throws SQLException{
        Connection conn = Connector.getInstance().getConnection();
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Vehicle WHERE plate = '" + plate + "'";
        return stmt.executeQuery(query);
    }

    public static void manageLapsReservation(int reservationID,int accepted_status) throws SQLException {
        Connection conn = Connector.getInstance().getConnection();
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query;

        System.out.println("Accepted status: " + accepted_status);
        System.out.println("Reservation ID: " + reservationID);

        if(accepted_status == 1){
            query = "UPDATE TrackLapsReservation SET confirmation_status = 'Confirmed' WHERE lapreservation_id = '" + reservationID + "'";
        } else if (accepted_status == 0) {
            query = "UPDATE TrackLapsReservation SET confirmation_status = 'Rejected' WHERE lapreservation_id = '" + reservationID + "'";
        } else{
            throw new SQLException();
        }
        stmt.executeUpdate(query);

    }


}