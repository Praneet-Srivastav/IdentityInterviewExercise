package com.identity.hooks;

import com.identity.TestContext;
import com.identity.pages.CarValuationPage;
import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;

/**
 * Hooks class manages the test environment setup and teardown for Cucumber scenarios.
 * It handles the initialization and cleanup of Playwright resources including browser,
 * context, and page objects. This class ensures that each test scenario starts with
 * a fresh browser instance and all resources are properly cleaned up afterward.
 */
public class Hooks {
    private static Playwright playwright;
    private static Browser browser;
    private static Page page;
    private static CarValuationPage valuationPage;

    /**
     * Sets up the test environment before each scenario.
     * This method:
     * 1. Creates a new Playwright instance
     * 2. Launches a Chrome browser in non-headless mode
     * 3. Creates a new browser context with specified viewport
     * 4. Initializes a new page and CarValuationPage
     * 5. Sets up the TestContext with the new instances
     *
     * @throws Exception if any step in the setup process fails
     */
    @Before
    public void setUp() {
        System.out.println("Setting up test environment...");
        try {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setViewportSize(1920, 1080));
            page = context.newPage();
            valuationPage = new CarValuationPage(page);
            TestContext.setPage(page);
            TestContext.setValuationPage(valuationPage);
            System.out.println("Test environment setup completed successfully");
        } catch (Exception e) {
            System.err.println("Failed to set up test environment: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Tears down the test environment after each scenario.
     * This method ensures proper cleanup by:
     * 1. Closing the page
     * 2. Closing the browser
     * 3. Closing the Playwright instance
     *
     * Each step is executed independently to ensure maximum cleanup even if some steps fail.
     *
     * @throws Exception if any step in the teardown process fails
     */
    @After
    public void tearDown() {
        System.out.println("Tearing down test environment...");
        try {
            if (page != null) {
                page.close();
            }
            if (browser != null) {
                browser.close();
            }
            if (playwright != null) {
                playwright.close();
            }
            System.out.println("Test environment teardown completed successfully");
        } catch (Exception e) {
            System.err.println("Failed to tear down test environment: " + e.getMessage());
            throw e;
        }
    }
}
