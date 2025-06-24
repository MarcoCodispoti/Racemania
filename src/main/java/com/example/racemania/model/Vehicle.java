package com.example.racemania.model;

import javafx.scene.text.TextFlow;

public class Vehicle {
    private String plate;
    private int ownerId;
    private String brand;
    private String model;
    private int immatriculationYear;
    private int power;
    private int lastcheckYear;


    public Vehicle(String plate,int ownerId, String brand, String model, int immatriculationYear, int power, int lastcheckYear){
        this.plate = plate;
        this.ownerId = ownerId;
        this.brand = brand;
        this.model = model;
        this.immatriculationYear = immatriculationYear;
        this.power = power;
        this.lastcheckYear = lastcheckYear;
    }

    public void setPlate(String plate) {this.plate = plate;}
    public void setOwnerId(int ownerId) {this.ownerId = ownerId;}
    public void setBrand(String brand){this.brand = brand;}
    public void setModel(String model){this.model = model;}
    public void setImmatriculationYear(int immatriculationYear){this.immatriculationYear = immatriculationYear;}
    public void setPower(int power){this.power = power;}
    public void setLastcheckYear(int lastcheckYear){this.lastcheckYear = lastcheckYear;}

    public String getPlate(){return plate;}
    public int getOwnerId(){return ownerId;}
    public String getBrand(){return brand;}
    public String getModel(){return model;}
    public int getImmatriculationYear(){return immatriculationYear;}
    public int getPower(){return power;}
    public int getLastcheckYear(){return lastcheckYear;}
}
