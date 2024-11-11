package com.identity;

import com.identity.pages.CarValuationPage;
import com.microsoft.playwright.Page;

public class TestContext {
    private static Page page;
    private static CarValuationPage valuationPage;

    public static CarValuationPage getValuationPage() {
        if (valuationPage == null) {
            throw new IllegalStateException("ValuationPage has not been initialized. Ensure Hooks.setUp() is being called.");
        }
        return valuationPage;
    }

    public static void setPage(Page newPage) {
        if (newPage == null) {
            throw new IllegalArgumentException("Page cannot be null");
        }
        page = newPage;
    }

    public static void setValuationPage(CarValuationPage newValuationPage) {
        if (newValuationPage == null) {
            throw new IllegalArgumentException("ValuationPage cannot be null");
        }
        valuationPage = newValuationPage;
    }

    public static void reset() {
        page = null;
        valuationPage = null;
    }
}
