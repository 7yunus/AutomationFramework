package org.example.sedin.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.sedin.utilities.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.time.Duration;

public class DriverSetup {
    private static final Logger LOG = LogManager.getLogger(DriverSetup.class);
    public static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public void driverInitialisation() throws IOException {
        String browser = PropertiesReader.getPropertiesFileValue("browser"); //get browser value from config.prop
        switch (browser) {
            case "chrome":
			/*
			We don’t need to manage any executable driver file or use Bonigarcia Webdrivermanager as in latest selenium
			v4.6.0, Beta 1 of Selenium Manager will configure the browser drivers for Chrome, Firefox, and Edge if
			they are not present on the path
			*/
                driver = new ChromeDriver();
                LOG.info(browser + "browser is launched!");
                break;
            case "firefox":
                driver = new FirefoxDriver();
                LOG.info(browser + "browser is launched!");
                break;
            default:
                System.out.println("Entered browser not present in config.properties file");
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().deleteAllCookies();
        driver.get(PropertiesReader.getPropertiesFileValue("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}















//    WebDriverWait wait = new WebDriverWait(driver,30);
//    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'COMPOSE')]")));