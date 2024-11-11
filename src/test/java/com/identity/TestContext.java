package com.identity;

import com.identity.pages.CarValuationPage;
import com.microsoft.playwright.Page;

/**
 * TestContext manages the shared test context across the test execution.
 * It provides a centralized way to manage Playwright Page and CarValuationPage instances,
 * ensuring proper initialization and cleanup of test resources.
 * This class follows the Singleton pattern to maintain a single instance of test context.
 */
public class TestContext {
    private static Page page;
    private static CarValuationPage valuationPage;

    /**
     * Retrieves the CarValuationPage instance.
     * 
     * @return The CarValuationPage instance
     * @throws IllegalStateException if ValuationPage hasn't been initialized
     */
    public static CarValuationPage getValuationPage() {
        if (valuationPage == null) {
            throw new IllegalStateException("ValuationPage has not been initialized. Ensure Hooks.setUp() is being called.");
        }
        return valuationPage;
    }

    /**
     * Sets the Playwright Page instance.
     * 
     * @param newPage The Page instance to set
     * @throws IllegalArgumentException if the provided page is null
     */
    public static void setPage(Page newPage) {
        if (newPage == null) {
            throw new IllegalArgumentException("Page cannot be null");
        }
        page = newPage;
    }

    /**
     * Sets the CarValuationPage instance.
     * 
     * @param newValuationPage The CarValuationPage instance to set
     * @throws IllegalArgumentException if the provided valuation page is null
     */
    public static void setValuationPage(CarValuationPage newValuationPage) {
        if (newValuationPage == null) {
            throw new IllegalArgumentException("ValuationPage cannot be null");
        }
        valuationPage = newValuationPage;
    }

    /**
     * Resets the test context by clearing all stored instances.
     * This should be called after each test to ensure a clean state.
     */
    public static void reset() {
        page = null;
        valuationPage = null;
    }
}
