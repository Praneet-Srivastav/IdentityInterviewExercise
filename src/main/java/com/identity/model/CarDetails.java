package com.identity.model;

public class CarDetails {
    private String registrationNumber;
    private String make;
    private String model;
    private int year;

    public CarDetails(String registrationNumber, String make, String model, int year) {
        this.registrationNumber = registrationNumber;
        this.make = make;
        this.model = model;
        this.year = year;
    }


    public String getRegistrationNumber() { return registrationNumber; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public String getYear() { return String.valueOf(year); }
}
