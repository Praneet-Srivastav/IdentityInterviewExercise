package com.identity.model;

/**
 * CarDetails represents a data model for storing vehicle information.
 * This class encapsulates the essential details of a car including its
 * registration number, make, model, and year of manufacture.
 */
public class CarDetails {
    private String registrationNumber;
    private String make;
    private String model;
    private int year;

    /**
     * Constructs a new CarDetails instance with the specified parameters.
     *
     * @param registrationNumber The vehicle's registration number
     * @param make The manufacturer of the vehicle
     * @param model The specific model of the vehicle
     * @param year The year of manufacture
     */
    public CarDetails(String registrationNumber, String make, String model, int year) {
        this.registrationNumber = registrationNumber;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    /**
     * @return The vehicle's registration number
     */
    public String getRegistrationNumber() { return registrationNumber; }

    /**
     * @return The manufacturer of the vehicle
     */
    public String getMake() { return make; }

    /**
     * @return The specific model of the vehicle
     */
    public String getModel() { return model; }

    /**
     * @return The year of manufacture as a string
     */
    public String getYear() { return String.valueOf(year); }
}
