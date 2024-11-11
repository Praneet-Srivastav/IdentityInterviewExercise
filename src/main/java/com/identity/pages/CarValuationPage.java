package com.identity.pages;

import com.microsoft.playwright.*;
import com.identity.model.CarDetails;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.Random;

public class CarValuationPage {
    // Constants
    private static final String BASE_URL = "https://www.webuyanycar.com/car-valuation/";
    private static final int MIN_MILEAGE = 10000;
    private static final int MAX_MILEAGE_RANGE = 50000;
    private static final int DEFAULT_TIMEOUT = 5000;
    private static final int ERROR_WAIT_TIMEOUT = 10000;

    // Page elements
    private final Page page;
    private final Locator registrationInput;
    private final Locator mileageInput;
    private final Locator submitButton;
    private final Locator cookieAcceptButton;
    private final Locator vehicleQuestionsSection;

    private final Random random;

    public CarValuationPage(Page page) {
        this.page = page;
        this.random = new Random();

        // Initialize locators
        this.registrationInput = page.getByPlaceholder("Enter your reg");
        this.mileageInput = page.getByPlaceholder("Mileage");
        this.submitButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Get my car valuation chevron"));
        this.cookieAcceptButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Accept all cookies"));
        this.vehicleQuestionsSection = page.locator("vehicle-questions");
    }

    /**
     * Navigates to the car valuation page and handles cookie consent
     */
    public void navigateToValuationPage() {
        page.navigate(BASE_URL);
        page.waitForLoadState(LoadState.NETWORKIDLE);

        try {
            cookieAcceptButton.waitFor(new Locator.WaitForOptions().setTimeout(DEFAULT_TIMEOUT));
            if (cookieAcceptButton.isVisible()) {
                cookieAcceptButton.click();
            }
        } catch (TimeoutError e) {
            // Cookie button might not appear if cookies are already accepted
            System.out.println("Cookie accept button not found - continuing");
        }
    }

    /**
     * Enters the registration number into the form
     *
     * @param regNumber Vehicle registration number
     */
    public void enterRegistration(String regNumber) {
        registrationInput.fill(regNumber);
    }

    /**
     * Enters a random mileage between MIN_MILEAGE and MIN_MILEAGE + MAX_MILEAGE_RANGE
     */
    public void enterRandomMileage() {
        String mileage = String.valueOf(MIN_MILEAGE + random.nextInt(MAX_MILEAGE_RANGE));
        mileageInput.fill(mileage);
    }

    /**
     * Clicks the car valuation button and checks if the vehicle was found
     * @param regNumber Vehicle registration number
     * @return true if the vehicle was found, false otherwise
     */
    public boolean clickCarValuation(String regNumber) {
        submitButton.click();
        
        // Wait for any network activity to complete
        page.waitForLoadState(LoadState.NETWORKIDLE);

        Locator errorHeading = page.getByRole(AriaRole.HEADING, 
            new Page.GetByRoleOptions().setName("Sorry, we couldn't find your"));

        // First check for error message
        try {
            errorHeading.waitFor(new Locator.WaitForOptions().setTimeout(ERROR_WAIT_TIMEOUT));
            System.out.println("SORRY, NO VEHICLE FOUND WITH REG: " + regNumber);
            return false;
        } catch (TimeoutError e) {
            // If error message not found, check for vehicle questions section
            try {
                vehicleQuestionsSection.waitFor(new Locator.WaitForOptions().setTimeout(ERROR_WAIT_TIMEOUT));
                System.out.println("Vehicle questions section found for reg: " + regNumber);
                return true;
            } catch (TimeoutError e2) {
                throw new PlaywrightException("Neither vehicle questions section nor error message appeared within " + ERROR_WAIT_TIMEOUT + "ms");
            }
        }
    }

    /**
     * Validates the displayed car details against the expected details
     * @param expectedCarDetails The expected car details to validate against
     * @throws PlaywrightException if assertions fail
     */
    public void validateCarDetails(CarDetails expectedCarDetails) {
        System.out.println("Validating Registration Number: " + expectedCarDetails.getRegistrationNumber());
        assertThat(vehicleQuestionsSection).containsText(expectedCarDetails.getRegistrationNumber());
        
        System.out.println("Validating Make: " + expectedCarDetails.getMake());
        assertThat(vehicleQuestionsSection).containsText(expectedCarDetails.getMake());
        
        System.out.println("Validating Model: " + expectedCarDetails.getModel());
        assertThat(vehicleQuestionsSection).containsText(expectedCarDetails.getModel());
        
        System.out.println("Validating Year: " + expectedCarDetails.getYear());
        assertThat(vehicleQuestionsSection).containsText(expectedCarDetails.getYear());
        
        System.out.println("All validations passed successfully!");
    }
}
