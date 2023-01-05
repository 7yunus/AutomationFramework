package org.example.sedin.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = {
    "org.example.sedin.stepDefinition", "org.example.sedin.hooks"}, tags = "@APIRegression",
    //                tags ="@APIRegression or @UIRegression",
    monochrome = true, plugin = {"pretty",
    //                        "timeline:<report folder>", //For a visual representation of threads, add the timeline report in parallel run
    "json:target/cucumber-report/cucumber.json", "rerun:target/cucumber-report/rerun.txt",
    "html:target/cucumber-report/cucumber-reports.html",
    "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})


public class CucumberRunner extends AbstractTestNGCucumberTests {

  //Uncomment below code for parallel run
  //https://cucumber.io/docs/guides/parallel-execution/?lang=java

  //    @Override
  //    @DataProvider(parallel = true)
  //    public Object[][] scenarios() {
  //        return super.scenarios();
  //    }
}