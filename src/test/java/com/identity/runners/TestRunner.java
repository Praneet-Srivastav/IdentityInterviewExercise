package com.identity.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.identity.steps", "com.identity.hooks"},
        plugin = {"pretty", "html:target/cucumber.html"},
    tags = "@done"
)
public class TestRunner {
}
