package com.example.racemania.model.bean;

import com.example.racemania.model.TrackLapsReservation;
import com.example.racemania.model.dao.TrackLapsReservationDao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TrackLapsReservationBean {
        private List<TrackLapsReservation> userActiveTrackLapsReservations;
        private List<TrackLapsReservation> ownerTrackLapsReservations;
        private List<TrackLapsReservation> ownerActiveTrackLapsReservations;
        // private int selectedTrackId;

        public List<TrackLapsReservation> getUserLapsReservation(int userId) throws SQLException {
                TrackLapsReservationDao trackLapsReservationDao = new TrackLapsReservationDao();
                try {
                        this.userActiveTrackLapsReservations = trackLapsReservationDao.findUserLapsReservations(userId); // salva nel campo della classe
                } catch (SQLException e) {
                        throw new SQLException(e);
                }
                return this.userActiveTrackLapsReservations;
        }

        public List<TrackLapsReservation> getOwnerLapsReservation(int trackId) throws SQLException {
                TrackLapsReservationDao trackLapsReservationDao = new TrackLapsReservationDao();
                try {
                        this.userActiveTrackLapsReservations = trackLapsReservationDao.findOwnerLapsReservations(trackId); // salva nel campo della classe
                } catch (SQLException e) {
                        throw new SQLException(e);
                }
                return this.userActiveTrackLapsReservations;
        }

        // vado a spostare la funzione nel controller
        public void manageLapsReservation(int reservationID,String decision) throws SQLException {
                TrackLapsReservationDao trackLapsReservationDao = new TrackLapsReservationDao();
                try {
                        trackLapsReservationDao.ManageLapsReservation(reservationID,decision);
                } catch (SQLException e) {
                        throw new SQLException(e);
                }
        }


        //attributi classe padre
        private int reservationID;
        private int userID;
        private LocalDate date;
        private int trackID;

        private int price;
        private int laps;

        //attributi classe figlio
        private String vehiclePlate;
        private int lapsNumber;
        private boolean isDaily;
        private int lapPrice;
        private int dailyPrice;
        private String confirmationStatus;


        public void setReservationID(int reservationID){this.reservationID = reservationID;}
        public void setUserID(int userID){this.userID = userID;}
        public void setDate(LocalDate date){this.date = date;}
        public void setTrackID(int trackID){this.trackID = trackID;}
        public void setPrice(int price){this.price = price;}
        public void setLaps(int laps){this.laps = laps;}

        public void setVehiclePlate(String plate){this.vehiclePlate = plate;}
        public void setLapsNumber(int lapsNumber){this.lapsNumber = lapsNumber;}
        public void setIsDaily(boolean daily){this.isDaily = daily;}
        public void setLapPrice(int lapPrice){this.lapPrice = lapPrice;}
        public void setDailyPrice(int dailyPrice){this.dailyPrice = dailyPrice;}
        public void setConfirmationStatus(String reservationStatus){this.confirmationStatus = reservationStatus;}


        public int getReservationID(){return reservationID;}
        public int getUserID(){return userID;}
        public LocalDate getDate(){return date;}
        public int getTrackID(){return trackID;}
        public int getPrice(){return price;}
        public int getLaps(){return laps;}

        public String getVehiclePlate(){return vehiclePlate;}
        public int getLapsNumber(){return lapsNumber;}
        public boolean getIsDaily(){return isDaily;}
        public int getLapPrice(){return lapPrice;}
        public int getDailyPrice(){return dailyPrice;}
        public String getConfirmationStatus(){return confirmationStatus;}

        public void setUserActiveTrackLapsReservations(List<TrackLapsReservation> activeTrackLapsReservations){ this.userActiveTrackLapsReservations = activeTrackLapsReservations;}
        public List<TrackLapsReservation> getUserActiveTrackLapsReservations(){return this.userActiveTrackLapsReservations;}

        public void setOwnerTrackLapsReservations(List<TrackLapsReservation> ownerTrackLapsReservations){ this.ownerTrackLapsReservations = ownerTrackLapsReservations;}
        public List<TrackLapsReservation> getOwnerTrackLapsReservations(){return this.ownerTrackLapsReservations;}

        public void setOwnerActiveTrackLapsReservations(List<TrackLapsReservation> ownerActiveTrackLapsReservations){ this.ownerActiveTrackLapsReservations = ownerActiveTrackLapsReservations;}
        public List<TrackLapsReservation> getOwnerActiveTrackLapsReservations(){return this.ownerActiveTrackLapsReservations;}
}
