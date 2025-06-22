package com.example.racemania.model.dao;

import com.example.racemania.exceptions.FailedInsertException;


import com.example.racemania.model.service.Connector;
import com.example.racemania.model.service.Query;
import com.example.racemania.model.TrackLapsReservation;
import com.example.racemania.model.bean.TrackLapsReservationBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class TrackLapsReservationDao{

    public List<TrackLapsReservation> findUserLapsReservations(int userId) throws SQLException {
        Statement stmt = null;
        Connection conn; // = null;
        List<TrackLapsReservation> trackLapsReservations = new ArrayList<>();

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findLapsReservations(userId);
            while (rs.next()) {
                trackLapsReservations.add(extractLapsReservations(rs));
                // System.out.println("Ciao");  //dummy
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw new SQLException(e);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                //not handled
            }
        }
        return trackLapsReservations;
    }

    public void insertTrackLapsReservation(TrackLapsReservationBean trackLapsReservationBean) throws FailedInsertException {
        Statement stmt = null;
        Connection conn;

        try {
            conn = Connector.getInstance().getConnection();
            if (conn == null) {
                throw new FailedInsertException("Impossibile ottenere una connessione al database.");
            }
            stmt = conn.createStatement();
            try {
                Query.insertTrackLapsReservation(stmt, trackLapsReservationBean);
            } catch (Exception e) {
                System.out.println("Errore SQL reale:");
                // e.printStackTrace(); // <<--- AGGIUNGI QUESTO
                throw new FailedInsertException("An error during lesson insertion occurred.",e);
            }

        } catch (Exception e) {
            throw new FailedInsertException("An error during lesson insertion occurred.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                //not handled
            }
        }
    }

    private TrackLapsReservation extractLapsReservations(ResultSet rs) throws SQLException {
        LocalDate date = LocalDate.parse(rs.getString("reservation_date"));

        return new TrackLapsReservation(
                rs.getInt("lapreservation_id"),
                rs.getInt("user_id"),
                date,
                rs.getInt("track_id"),
                rs.getInt("price"),
                rs.getInt("laps"),
                rs.getString("vehicle_plate"),
                rs.getBoolean("is_daily"),
                rs.getInt("lap_price"),
                rs.getInt("daily_price"),
                rs.getString("confirmation_status"));
    }


    public List<TrackLapsReservation> findOwnerLapsReservations(int trackId) throws SQLException {
        Statement stmt = null;
        Connection conn; // = null;
        List<TrackLapsReservation> trackLapsReservations = new ArrayList<>();

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findOwnerLapsReservations(trackId);
            while (rs.next()) {
                trackLapsReservations.add(extractLapsReservations(rs));
                // System.out.println("Ciao");  //dummy
            }
        } catch (Exception e) {
            // e.printStackTrace();
            throw new SQLException(e);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                //not handled
            }
        }
        return trackLapsReservations;
    }


    public List<TrackLapsReservation> findOwnerActiveLapsReservations(int trackId) throws SQLException {
        Statement stmt = null;
        Connection conn; // = null;
        List<TrackLapsReservation> trackLapsReservations = new ArrayList<>();

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findOwnerActiveLapsReservations(trackId);
            while (rs.next()) {
                trackLapsReservations.add(extractLapsReservations(rs));
                // System.out.println("Ciao");  //dummy
            }
        } catch (Exception e) {
            // e.printStackTrace();
            throw new SQLException(e);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                //not handled
            }
        }
        return trackLapsReservations;
    }


    public void ManageLapsReservation(int reservationId, String decision) throws SQLException {
        int accepted_status = -1;

        Statement stmt = null;
        Connection conn; // = null;
        // List<TrackLapsReservation> trackLapsReservations = new ArrayList<>();

        if(decision.equals("Accepted")){
            accepted_status = 1;
        } else if(decision.equals("Rejected")) {
            accepted_status = 0;
        }

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Query.manageLapsReservation(reservationId,accepted_status);
        }
        catch (Exception e) {
            // e.printStackTrace();
            throw new SQLException(e);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                //not handled
            }
        }
    }


}
