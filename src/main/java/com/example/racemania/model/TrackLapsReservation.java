package com.example.racemania.model;

import java.time.LocalDate;

public class TrackLapsReservation extends Reservation {
    private boolean isDaily;
    private int lapPrice;
    private int dailyPrice;
    private String vehiclePlate;
    private String confirmationStatus;

    public TrackLapsReservation(int reservationID,int userID,LocalDate date,
                                int trackID,int price,int lapsNumber){
        super(reservationID,userID,date,trackID,price,lapsNumber);
    }

//    public TrackLapsReservation(int reservationID,int userID,LocalDate date,
//                                     int trackID,int price,int lapsNumber,String vehiclePlate,
//                                     boolean isDaily, int lapPrice,int dailyPrice,String confirmationStatus){
//
//        // per dichiarare meno di 8 parametri nel costruttore (Non piace a sonar cloud)
//        super(reservationID,userID,date,trackID,price,lapsNumber);
//
//        //attributi classe figlio
//        this.vehiclePlate = vehiclePlate;
//        this.isDaily = isDaily;
//        this.lapPrice = lapPrice;
//        this.dailyPrice = dailyPrice;
//        this.confirmationStatus = confirmationStatus;
//    }

    public void SetTrackLapsReservation(String vehiclePlate, boolean isDaily, int lapPrice,int dailyPrice,String confirmationStatus){
        this.vehiclePlate = vehiclePlate;
        this.isDaily = isDaily;
        this.lapPrice = lapPrice;
        this.dailyPrice = dailyPrice;
        this.confirmationStatus = confirmationStatus;
    };


    public void setPrice(){
        if(isDaily){
            price = dailyPrice;
        } else {
            price = lapPrice*laps;
        }
    }

    public void setIsDaily(boolean daily){this.isDaily = daily;}
    public void setDailyPrice(boolean isDaily){this.isDaily = isDaily;};
    public void setLapPrice(int lapPrice){this.lapPrice = lapPrice;}
    public void setDailyPrice(int dailyPrice){this.dailyPrice = dailyPrice;}
    public void setVehiclePlate(String vehiclePlate){this.vehiclePlate= vehiclePlate;}
    public void setConfirmationStatus(String confirmationStatus){this.confirmationStatus = confirmationStatus;}

    public boolean getIsDaily(){return isDaily;}
    public void getDailyPrice(int dailyPrice){this.dailyPrice = dailyPrice;}
    public int getDailyPrice(){return dailyPrice;}
    public int getLapPrice(){return lapPrice;}
    public String getVehiclePlate(){return vehiclePlate;}
    public String getConfirmationStatus(){return confirmationStatus;}

}
