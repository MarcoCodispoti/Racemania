package com.example.racemania.model;

import java.time.LocalDate;

public class Reservation {
    protected int reservationId;
    protected int userID;
    protected LocalDate date;
    protected int trackID;
    protected int price;
    protected int laps;

    public Reservation(){
        // default constructor
    }

    public Reservation(int reservationId, int userID, LocalDate date, int trackID,int price,int laps){
        this.reservationId = reservationId;
        this.userID = userID;
        this.date = date;
        this.trackID = trackID;
        this.price = price;
        this.laps = laps;
    }

    public void setReservationID(int reservationID) {this.reservationId = reservationID;}
    public void setUserID(int userID) {this.userID = userID;}
    public void setDate(LocalDate date) {this.date = date;}
    public void setTrackID(int trackID) {this.trackID = trackID;}
    public void setPrice(int price) {this.price = price;}
    public void setLaps(int laps) {this.laps = laps;}

    public int getReservationID() {return this.reservationId;}
    public int getUserID() {return this.userID;}
    public LocalDate getDate() {return this.date;}
    public int getTrackID() {return this.trackID;}
    public int getPrice() {return this.price;}
    public int getLaps() {return this.laps;}


}
