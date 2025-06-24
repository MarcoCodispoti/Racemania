package com.example.racemania.model;

public class Track {
    private int trackId;
    private String name;
    private int lenght;
    private String place;
    private int ownerId;
    private int lapPrice;
    private int dailyPrice;

    // costruttore, prova, da rimuovere nel caso
    public Track(int trackId,String name, int lenght, String place, int ownerId, int lapPrice, int dailyPrice) {
        this.trackId = trackId;
        this.name = name;
        this.lenght = lenght;
        this.place = place;
        this.ownerId = ownerId;
        this.lapPrice = lapPrice;
        this.dailyPrice = dailyPrice;
    }


    public void setName(String name){ this.name = name; }
    public void setLenght(int lenght){ this.lenght = lenght; }
    public void setPlace(String place){ this.place = place; }
    public void setOwnerId(int ownerId){ this.ownerId = ownerId; }
    public void setLapPrice(int lapPrice){ this.lapPrice = lapPrice; }
    public void setDailyPrice(int dailyPrice){ this.dailyPrice = dailyPrice; }

    public int getTrackId() { return this.trackId; }
    public String getName(){ return this.name; }
    public int getLenght(){ return this.lenght; }
    public String getPlace(){ return this.place; }
    public int getOwnerId(){ return this.ownerId; }
    public int getLapPrice(){ return this.lapPrice; }
    public int getDailyPrice(){ return this.dailyPrice; }
}
