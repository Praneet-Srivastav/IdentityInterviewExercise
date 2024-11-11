package com.identity.hooks;
import com.identity.TestContext;
import com.identity.pages.CarValuationPage;
import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    private static Playwright playwright;
    private static Browser browser;
    private static Page page;
    private static CarValuationPage valuationPage;

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
