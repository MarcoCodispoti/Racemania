package com.example.racemania.model;

// import java.util.List;

public class Customer extends User {
//    protected List<TrackLapsReservation> trackLapsReservations;
//    protected List<Vehicle> vehicles;

    public Customer() {}

    public Customer(int userId, String email) {
        this.userId = userId;
        this.email = email;
        this.role = User.Role.CUSTOMER;
    }

    public Customer(int userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = User.Role.CUSTOMER;
    }
}
