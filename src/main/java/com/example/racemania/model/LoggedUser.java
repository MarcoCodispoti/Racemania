package com.example.racemania.model;

public class LoggedUser {
    private Customer customer;
    private TrackOwner trackOwner;
    private String role;

    private LoggedUser() {}

    //singleton
    public static LoggedUser getInstance() {
        if(instance==null) {
            instance = new LoggedUser();
        }
        return instance;
    }

    public String getRole() {
        if (customer != null) {
            return customer.getRole().toString();
        } else {
            return trackOwner.getRole().toString();
        }
    }

    public static void logout(){
        if(instance!=null) {
            instance.customer = null;
            instance.trackOwner = null;
            instance.role = null;
        }
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TrackOwner getTrackOwner() {
        return trackOwner;
    }

    public void setTrackOwner(TrackOwner trackOwner) {
        this.trackOwner = trackOwner;
    }

    private static LoggedUser instance = null;

}
