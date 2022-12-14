package org.example.sedin.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.sedin.configuration.DriverSetup;
import org.example.sedin.runner.ListenerClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeClass;

import java.io.ByteArrayInputStream;
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

			try {
				Allure.attachment("Failed screenshot: " + scenario.getName(), new ByteArrayInputStream(((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.BYTES)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		if (scenario.isFailed()) {
////			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
////			scenario.attach(screenshot, "image/png", "sample");
//
//			Allure.addAttachment("Any text", new ByteArrayInputStream(((TakesScreenshot) driver).
//					getScreenshotAs(OutputType.BYTES)));
//			logger.info("screenshot is taken for failed test case"+" " + scenario.getName());
//		}
		driver.quit();
		logger.info("quitting browser");
	}
}
