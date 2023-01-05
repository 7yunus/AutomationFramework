package org.example.sedin.runner.cross_browser;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import static org.example.sedin.configuration.DriverManager.createDriver;
import static org.example.sedin.configuration.DriverManager.quitDriver;

@CucumberOptions(features = "src/test/resources/features", glue = {
    "org.example.sedin.stepDefinition"}, tags = "@UIRegression",
    //                tags ="@APIRegression or @UIRegression",
    monochrome = true, plugin = {"pretty",
    //                        "timeline:<report folder>", //For a visual representation of threads, add the timeline report in parallel run
    "json:target/cucumber-report/cucumber.json", "rerun:target/cucumber-report/rerun.txt",
    "html:target/cucumber-report/cucumber-reports.html",
    "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})


public class CrossBrowserParallelRunner extends AbstractTestNGCucumberTests {

  //Uncomment below code for parallel run
  //https://cucumber.io/docs/guides/parallel-execution/?lang=java

  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }

  @Parameters("browser")
  @BeforeMethod
  public void before(String browser) {
    createDriver(browser);
  }

  @AfterMethod
  public void after() {
    quitDriver();
  }
}