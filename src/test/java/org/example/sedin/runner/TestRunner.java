package org.example.sedin.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
        (
                features = "src/test/resources/features/API",
                glue = {"org.example.sedin.stepDefinition", "org.example.sedin.hooks"},
                tags = "@APITests",
                monochrome = true,
                plugin = {"pretty",
                        "json:target/cucumber.json",
                        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
                }
        )
public class TestRunner extends AbstractTestNGCucumberTests {
}
