package com.example.racemania.model.bean;

import com.example.racemania.model.Track;

import java.util.List;

//classe Bean:
    // Solo dati niente logica,
    // Attributi privati
    // metodi get/set publici

public class TrackBean {
    private int trackId;
    private String name;
    private int lenght;
    private String place;
    private int ownerId;
    private int lapPrice;
    private int dailyPrice;

    private List<Track> availableTracks;
    private int selectedTrackId;

    public void setAvailableTracks(List<Track> availableTracks) {
        this.availableTracks = availableTracks;
    }
    public List<Track> getAvailableTracks(){ return this.availableTracks; }

    public void setSelectedTrackId(int selectedTrackId) { this.selectedTrackId = selectedTrackId; }
    public int getSelectedTrackId() {return this.selectedTrackId; }


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

