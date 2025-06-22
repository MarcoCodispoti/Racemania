package com.example.racemania.model;

public class Supercar {
    private int supercarID;
    private String brand;
    private String model;
    private int year;
    private String engine;
    private int horsepower;
    private int acceleration;
    private int maxSpeed;
    private String gears;


    public void setSupercarID(int supercarID){ this.supercarID = supercarID; }

    public void setBrand(String brand){ this.brand = brand; }

    public void setModel(String model){ this.model = model; }

    public void setYear(int year){ this.year = year; }

    public void setEngine(String engine){ this.engine = engine; }

    public void setHorsepower(int horsepower){ this.horsepower = horsepower; }

    public void setAcceleration(int acceleration){ this.acceleration = acceleration; }

    public void setMaxSpeed(int maxSpeed){ this.maxSpeed = maxSpeed; }

    public void setGears(String gears){ this.gears = gears; }



    public int getSupercarID(){ return supercarID; }

    public String getBrand(){ return brand; }

    public String getModel(){ return model; }

    public int getYear(){ return year; }

    public String getEngine(){ return engine; }

    public int getHorsepower(){ return horsepower; }

    public int getAcceleration(){ return acceleration; }

    public int getMaxSpeed(){ return maxSpeed; }

    public String getGears(){ return gears; }

}
