package org.example.sedin.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
        (
                features = {"@target/rerun.txt"},
                glue = {"org.example.sedin.stepDefinition",
                        "org.example.sedin.hooks"},
                plugin = {"json:target/cucumber.json"
//                        "json:target/cucumber-rerun.json",
//                        "html:target/cucumber-reports.html",
//                        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
//                        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
                },
                monochrome = true
        )

public class FailedTestRunner extends AbstractTestNGCucumberTests {

}
