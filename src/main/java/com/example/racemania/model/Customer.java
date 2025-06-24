package com.example.racemania.model;


public class Customer extends User {

    public Customer(){}

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
