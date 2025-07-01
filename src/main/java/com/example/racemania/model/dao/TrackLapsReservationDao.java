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
        Connection conn;
        List<TrackLapsReservation> trackLapsReservations = new ArrayList<>();

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findLapsReservations(userId);
            while (rs.next()) {
                trackLapsReservations.add(extractLapsReservations(rs));
            }
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException _) {
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
            tryInsertion(stmt, trackLapsReservationBean);

        } catch (Exception _) {
            throw new FailedInsertException("An error during lesson insertion occurred.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException _) {
                //not handled
            }
        }
    }

    private TrackLapsReservation extractLapsReservations(ResultSet rs) throws SQLException {
        LocalDate date = LocalDate.parse(rs.getString("reservation_date"));

        // Inizializzo tramite un costruttore e una funzione di supporto per non avere troppi parametri nel costruttore
        TrackLapsReservation trackLapsReservation = new TrackLapsReservation(
                rs.getInt("lapreservation_id"),
                rs.getInt("user_id"),
                date,
                rs.getInt("track_id"),
                rs.getInt("price"),
                rs.getInt("laps"));

        trackLapsReservation.setTrackLapsReservation(
                rs.getString("vehicle_plate"),
                rs.getBoolean("is_daily"),
                rs.getInt("lap_price"),
                rs.getInt("daily_price"),
                rs.getString("confirmation_status"));

        return trackLapsReservation;
    }

    private void tryInsertion(Statement stmt, TrackLapsReservationBean trackLapsReservationBean) throws FailedInsertException {
        try {
            Query.insertTrackLapsReservation(stmt, trackLapsReservationBean);
        } catch (Exception _) {
            throw new FailedInsertException("An error during lesson insertion occurred.");
        }
    }

    public List<TrackLapsReservation> findOwnerLapsReservations(int trackId) throws SQLException {
        Statement stmt = null;
        Connection conn;
        List<TrackLapsReservation> trackLapsReservations = new ArrayList<>();

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findOwnerLapsReservations(trackId);
            while (rs.next()) {
                trackLapsReservations.add(extractLapsReservations(rs));
            }
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException _) {
                //not handled
            }
        }
        return trackLapsReservations;
    }


    public List<TrackLapsReservation> findOwnerActiveLapsReservations(int trackId) throws SQLException {
        Statement stmt = null;
        Connection conn;
        List<TrackLapsReservation> trackLapsReservations = new ArrayList<>();

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findOwnerActiveLapsReservations(trackId);
            while (rs.next()) {
                trackLapsReservations.add(extractLapsReservations(rs));
            }
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException _) {
                //not handled
            }
        }
        return trackLapsReservations;
    }


    public void manageLapsReservation(int reservationId, String decision) throws SQLException {
        int acceptedStatus = -1;

        Statement stmt = null;
        Connection conn;

        if(decision.equals("Accepted")){
            acceptedStatus = 1;
        } else if(decision.equals("Rejected")) {
            acceptedStatus = 0;
        }

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Query.manageLapsReservation(reservationId,acceptedStatus);
        }
        catch (Exception e) {
            throw new SQLException(e);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException _) {
                //not handled
            }
        }
    }


}
