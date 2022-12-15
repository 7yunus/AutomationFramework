package org.example.sedin.hooks.uiHooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.sedin.configuration.DriverSetup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class UIHooks extends DriverSetup {

    private static final Logger LOG = LogManager.getLogger(DriverSetup.class);

    @Before
    public void setUp() throws IOException {
        driverInitialisation();
    }

    @After
    public void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                Allure.attachment("Failed screenshot: " + scenario.getName(), new ByteArrayInputStream(((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES)));
            } catch (Exception e) {
            }
        }
        LOG.info("Quit browser!");
        driver.quit();
    }
}
