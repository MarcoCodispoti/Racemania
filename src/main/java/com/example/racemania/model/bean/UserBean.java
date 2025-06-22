package com.example.racemania.model.bean;

public class UserBean {
    private int userId;
    private String email;
    private String userName;
    private String password;

    public void setUserId(int userId) {this.userId = userId;}
    public void setEmail(String email) {this.email = email;}
    public void setUserName(String userName) {this.userName = userName;}
    public void setPassword(String password) {this.password = password;}

    public int getUserId() {return this.userId;}
    public String getEmail() {return this.email;}
    public String getUserName() {return this.userName;}
    public String getPassword() {return this.password;}
}
