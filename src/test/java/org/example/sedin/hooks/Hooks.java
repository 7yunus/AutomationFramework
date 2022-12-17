package org.example.sedin.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.sedin.configuration.DriverSetup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;

public class Hooks extends DriverSetup {

    private static final Logger LOG = LogManager.getLogger(DriverSetup.class);

    @Before("@BeforeAPI")
    public void before() throws IOException {
    }

    @After("@AfterAPI")
    public void after(Scenario scenario) {
    }

    @Before("@BeforeUI")
    public void driverSetUp() throws IOException {
        driverInitialisation();
    }

    @After("@AfterUI")
    public void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            } catch (Exception e) {
            }
        }
        LOG.info("Quit browser!");
        driver.quit();
    }



}
