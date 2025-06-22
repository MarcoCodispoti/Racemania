package com.example.racemania.model;

import java.util.ArrayList;
import java.util.List;

public class TrackOwner extends User {
    private int trackId;
    protected List<Instructor> Instructors;

    public TrackOwner() {}

    public TrackOwner(int userId, String email,int trackID) {
        this.userId = userId;
        this.email = email;
        this.role = Role.TRACK_OWNER;
        this.trackId = trackID;
    }

    public void setTrackId(int trackId) {this.trackId = trackId;}
    public void setInstructors(List<Instructor> Instructors) {this.Instructors = Instructors;}

    public int getTrackId(){ return trackId;}
    public List<Instructor> getInstructors() {return Instructors;}
}
