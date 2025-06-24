package com.example.racemania.model;

public class Account {
    private int userId;
    private String role;
    private int trackId;

    public Account(){
        // costruttore, inizializza con valori di default
    }


    public void setUserId(int userId) {this.userId = userId;}
    public void setRole(String role) {this.role = role;}
    public void setTrackId(int trackId) {this.trackId = trackId;}

    public int getUserId() {return this.userId;}
    public String getRole() {return this.role;}
    public int getTrackId() {return this.trackId;}

}
