package com.identity.steps;

import com.identity.TestContext;
import com.identity.model.CarDetails;
import com.identity.utils.FileParser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * CarValuationSteps implements the Cucumber step definitions for car valuation scenarios.
 * This class handles the end-to-end flow of:
 * - Loading registration numbers from input files
 * - Loading expected car details from output files
 * - Validating car details against the valuation website
 * - Comparing actual results with expected data
 */
public class CarValuationSteps {
    private final FileParser fileParser;
    private Set<String> registrations;
    private Map<String, CarDetails> expectedDetails;
    private String currentRegistration;

    /**
     * Initializes a new instance of CarValuationSteps with a FileParser.
     */
    public CarValuationSteps() {
        this.fileParser = new FileParser();
    }

    /**
     * Loads car registration numbers from the specified input file.
     *
     * @param inputFile The name of the file containing registration numbers
     * @throws IOException if there's an error reading the file
     * @throws IllegalStateException if no registration numbers are found
     */
    @Given("I get car registration numbers from {string}")
    public void iHaveCarRegistrationNumbersFrom(String inputFile) throws IOException {
        registrations = fileParser.extractRegistrationNumbers("src/test/resources/testdata/" + inputFile);
        if (registrations == null || registrations.isEmpty()) {
            throw new IllegalStateException("No registration numbers were loaded from the input file");
        }
        System.out.println("Loaded " + registrations.size() + " registration numbers");
    }

    /**
     * Loads expected car details from the specified output file.
     *
     * @param outputFile The name of the file containing expected car details
     * @throws IOException if there's an error reading the file
     * @throws IllegalStateException if no car details are found
     */
    @Given("I have expected car details from {string}")
    public void iHaveExpectedCarDetailsFrom(String outputFile) throws IOException {
        expectedDetails = fileParser.parseOutputFile("src/test/resources/testdata/" + outputFile);
        if (expectedDetails == null || expectedDetails.isEmpty()) {
            throw new IllegalStateException("No car details were loaded from the output file");
        }
        System.out.println("Loaded " + expectedDetails.size() + " car details");
    }

    /**
     * Sets the current registration number for processing.
     *
     * @param registration The registration number to process
     */
    @Given("I have the car registration {string}")
    public void iHaveTheCarRegistration(String registration) {
        currentRegistration = registration;
    }

    /**
     * Processes each car registration by checking its details on the valuation website.
     * For each registration:
     * 1. Navigates to the valuation page
     * 2. Enters the registration and mileage
     * 3. Validates the returned details if the car is found
     */
    @When("I check each car's details on the valuation website")
    public void iCheckEachCarsDetailsOnTheValuationWebsite() {
        System.out.println("Starting to check car details for " + registrations.size() + " registrations");

        for (String regNumber : registrations) {
            System.out.println("============================================================================");
            System.out.println("Checking details for registration: " + regNumber);
            System.out.println("============================================================================");
            currentRegistration = regNumber;
            
            boolean carFound = checkCarDetails(regNumber);
            
            if (carFound) {
                CarDetails expected = expectedDetails.get(regNumber);
                if (expected != null) {
                    System.out.println("Validating details for registration: " + regNumber);
                    TestContext.getValuationPage().validateCarDetails(expected);
                } else {
                    System.out.println("Skipping validation for registration: " + regNumber + " - no expected details found");
                }
            } else {
                System.out.println("Skipping validation for registration: " + regNumber + " - car not found on website");
            }
        }
    }

    /**
     * Verifies that all car details have been processed successfully.
     *
     * @throws IllegalStateException if no registrations were processed
     */
    @Then("the car details should match the expected output")
    public void theCarDetailsShouldMatchTheExpectedOutput() {
        if (registrations == null || registrations.isEmpty()) {
            throw new IllegalStateException("No registrations were processed");
        }
        System.out.println("All car details have been processed successfully");
    }

    /**
     * Helper method to check car details for a specific registration number.
     * This method handles the interaction with the valuation website.
     *
     * @param regNumber The registration number to check
     * @return true if the car was found on the website, false otherwise
     * @throws IllegalArgumentException if the registration number is invalid
     * @throws IllegalStateException if the ValuationPage is not initialized
     */
    private boolean checkCarDetails(String regNumber) {
        if (regNumber == null || regNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Registration number cannot be null or empty");
        }

        if (TestContext.getValuationPage() == null) {
            throw new IllegalStateException("ValuationPage is not initialized");
        }

        System.out.println("Navigating to valuation page for " + regNumber);
        TestContext.getValuationPage().navigateToValuationPage();

        System.out.println("Entering registration: " + regNumber);
        TestContext.getValuationPage().enterRegistration(regNumber);

        System.out.println("Entering random mileage");
        TestContext.getValuationPage().enterRandomMileage();

        System.out.println("Clicking car valuation button");
        return TestContext.getValuationPage().clickCarValuation(regNumber);
    }
}
