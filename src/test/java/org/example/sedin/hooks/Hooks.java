package org.example.sedin.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.sedin.utilities.PropertiesReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static org.example.sedin.configuration.DriverManager.*;

public class Hooks {

  private static final Logger LOG = LogManager.getLogger(Hooks.class);
  private WebDriver driver;
  private String browser;

  @Before("@BeforeAPI")
  public void before() {
  }

  @After("@AfterAPI")
  public void after(Scenario scenario) {
  }


  @Before("@BeforeUI")
  public void driverSetUp() {
    String browser = null;
    try {
      browser = PropertiesReader.getPropertiesFileValue("browser");
    } catch (IOException e) {
      e.printStackTrace();
    }
    createDriver(browser);
  }

  @After("@AfterUI")
  public void takeScreenshot(Scenario scenario) {
    if (scenario.isFailed()) {
      try {
        final byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
      } catch (Exception e) {
        LOG.info(e);
      }
    }
    quitDriver();
  }
}
