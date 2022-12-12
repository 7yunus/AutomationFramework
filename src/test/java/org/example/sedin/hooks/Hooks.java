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

	Logger logger = LogManager.getLogger(DriverSetup.class);

	@Before
	public void setUp() throws IOException {
		initialisation();
	}

	@After
	public void takeScreenshot(Scenario scenario) {
		if (scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			// scenario.embed(screenshot, "image/png");
			scenario.attach(screenshot, "image/png", "sample");
			logger.info("screenshot is taken for failed test case"+" " + scenario.getName());
		}
		driver.quit();
		logger.info("quitting browser");
	}
}
