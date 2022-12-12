package org.example.sedin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static org.example.sedin.configuration.DriverSetup.driver;


public class SearchPage {

    public static Logger logger = LogManager.getLogger(SearchPage.class);

    @FindBy(xpath = "//input[contains(@value, 'Search')]")
    public WebElement btnSearch;

    @FindBy(xpath = "//input[@id='gh-ac']")
    public WebElement textBoxSearch;

    @FindBy(xpath = "//select[@id='gh-cat']")
    public WebElement textBoxDropDown;

    public SearchPage() {
        PageFactory.initElements(driver, this);
    }

    public void search(String searchTerm) {
        System.out.println(driver.getCurrentUrl());
        textBoxSearch.sendKeys(searchTerm, Keys.ENTER);
    }

    public void selectCategory(String category) {
        Select categoryDropDown = new Select(textBoxDropDown);
        categoryDropDown.selectByVisibleText(category);
        btnSearch.click();
    }

    public void waitForPageLoad() {
        /* JavaScript Executor to check ready state not required here as page load time is set in DriverSetup class
        but still just one other aproach to wait if needed */
        JavascriptExecutor j = (JavascriptExecutor) driver;
        if (j.executeScript("return document.readyState").toString().equals("complete")) {
            logger.info("FilterPage has loaded");
        }
    }

    public boolean isSearchSuccess(String category) {
        return driver.findElement(By.xpath("//span[contains(text(), '" + category + "')]")).isDisplayed();
    }
}
