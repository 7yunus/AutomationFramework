package org.example.sedin.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
        (
                features = "src/test/resources/features",
                glue = {"org.example.sedin.stepDefinition", "org.example.sedin.hooks"},
                tags = "@UITest",
                monochrome = true,
                plugin = {"pretty",
                        "json:target/cucumber.json"
                }
        )
public class TestRunner extends AbstractTestNGCucumberTests {
}
