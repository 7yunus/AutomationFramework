package org.example.sedin.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.sedin.utilities.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.openqa.selenium.remote.Browser.CHROME;

public class DriverManager {
    public static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final String GRID_URL = "/wd/hub";
    private static final String HUB_URL = "http://localhost:4444/wd/hub";
    private static final Logger LOG = LogManager.getLogger("DriverManager.class");

    private DriverManager() {
    }

    public static void createDriver(String browser) {
        LOG.info("Selected browser: "+browser);
//        browser = "remote-chrome";
        switch (browser) {
            case "firefox":
                setupFirefoxDriver();
                break;
            case "remote-chrome":
                setupRemoteChrome();
                break;
            case "remote-firefox":
                setupRemoteFirefox();
                break;
            case "chrome":
            default:
                setupChromeDriver();
        }
        setupBrowserTimeouts();
    }

    public static <D extends WebDriver> D getDriver() {
        return (D) DriverManager.DRIVER.get();
    }

    private static void setDriver(final WebDriver driver) {
        DriverManager.DRIVER.set(driver);
    }

    public static void quitDriver() {
        if (null != DRIVER.get()) {
            LOG.info("Closing the driver...");
            getDriver().quit();
            DRIVER.remove();
        }
    }

    private static void setupBrowserTimeouts() {
        LOG.info("Setting Browser Timeouts....");
        getDriver().manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage()
                .timeouts()
                .pageLoadTimeout(Duration.ofSeconds(10));
        getDriver().manage()
                .timeouts()
                .scriptTimeout(Duration.ofSeconds(10));
    }

    private static void setupChromeDriver() {
        LOG.info("Setting up Chrome Driver....");

//        final boolean isHeadless = Boolean.parseBoolean (
//                Objects.requireNonNullElse (System.getProperty ("headless"), "true"));
//        final HashMap<String, Object> chromePrefs = new HashMap<> ();
//        chromePrefs.put ("safebrowsing.enabled", "true");
//        chromePrefs.put ("download.prompt_for_download", "false");
//        chromePrefs.put ("download.default_directory",
//                String.valueOf (Paths.get (System.getProperty ("user.home"), "Downloads")));

        final ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1050,600");
//        if (isHeadless) {
//            options.addArguments ("--headless");
//        }

        setDriver(WebDriverManager.chromedriver()
                .capabilities(options)
                .create());
        LOG.info("Chrome Driver created successfully!");
    }

    private static void setupFirefoxDriver() {
        LOG.info("Setting up Firefox Driver....");
        final FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1050,600");

//        options.addArguments("--headless");
        setDriver( new FirefoxDriver()
//                .capabilities(options)
//                .create()
                );
        LOG.info("Firefox Driver created successfully!");
    }


    private static void setupRemoteChrome() {
        try {
            LOG.info("Setting up Remote Chrome Driver....");
            final DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName(CHROME.browserName());
            setDriver(new RemoteWebDriver(new URL(HUB_URL), caps));
            LOG.info("Remote Chrome Driver created successfully!");
        } catch (final MalformedURLException e) {
            LOG.error("Error setting remote_chrome", e);
        }
    }

    private static void setupEdgeDriver() {
        LOG.info("Setting up Edge Driver....");
        setDriver(WebDriverManager.edgedriver()
                .create());
        LOG.info("Edge Driver created successfully!");
    }

    private static void setupRemoteEdge() {
        try {
            LOG.info("Setting up Remote Edge Driver....");
            final DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName("MicrosoftEdge");
            setDriver(new RemoteWebDriver(new URL(HUB_URL), caps));
            LOG.info("Remote Edge Driver created successfully!");
        } catch (final MalformedURLException e) {
            LOG.error("Error setting remote_edge", e);
        }
    }

    private static void setupRemoteFirefox() {
        try {
            LOG.info("Setting up Remote Firefox Driver....");
            final DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName("firefox");
            setDriver(new RemoteWebDriver(new URL(HUB_URL), caps));
            LOG.info("Remote Firefox Driver created successfully!");
        } catch (final MalformedURLException e) {
            LOG.error("Error setting remote_firefox", e);
        }
    }
}