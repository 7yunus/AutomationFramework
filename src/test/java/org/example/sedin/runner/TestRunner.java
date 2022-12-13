package org.example.sedin.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
        (
                features = "src/test/resources/features",
                glue = {"org.example.sedin.stepDefinition","org.example.sedin.hooks"},
                tags = "@Test",
                plugin = {"pretty",
                        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                        "html:target/cucumber.html",
                        "json:target/cucumber.json"
                }
        )
public class TestRunner extends AbstractTestNGCucumberTests {
}
