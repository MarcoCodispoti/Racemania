package com.example.racemania.model;

public class User {

    protected int userId;
    protected String username;
    protected String email;
    protected Role role;

    public enum Role { CUSTOMER, TRACK_OWNER }

    public User(){
        // default constructor
    }

    public User(int userId, String username, String email, Role role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public void setUserId(int userId) {this.userId = userId;}
    public void setName(String username){ this.username = username; }
    public void setEmail(String email){ this.email = email; }
    public void setRole(Role role) { this.role = role; }

    public int getUserId(){ return this.userId; }
    public String getName(){ return this.username; }
    public String getEmail(){ return this.email; }
    public Role getRole() { return this.role; }
}
