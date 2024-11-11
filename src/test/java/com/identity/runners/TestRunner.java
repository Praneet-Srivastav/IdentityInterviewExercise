package com.identity.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * TestRunner configures and executes Cucumber tests for the car valuation framework.
 * This class uses JUnit to run Cucumber tests and specifies various configuration options
 * through CucumberOptions.
 *
 * Configuration includes:
 * - Feature files location: src/test/resources/features
 * - Step definitions and hooks packages: com.identity.steps, com.identity.hooks
 * - HTML report generation in target/cucumber.html
 * - Execution of scenarios tagged with @done
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.identity.steps", "com.identity.hooks"},
    plugin = {"pretty", "html:target/cucumber.html"},
    tags = "@done"
)
public class TestRunner {
    // This class serves as a configuration class and doesn't need any implementation
}
