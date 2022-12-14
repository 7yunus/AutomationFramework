package org.example.sedin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.example.sedin.configuration.DriverSetup.driver;

public class LoginPage {

    @FindBy(xpath = "//h3[@data-test='error']")
    public WebElement loginErrorMessage;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void clickLoginBtn() {
        driver.findElement(By.id("login-button")).click();
    }

    public boolean isProductPageOpened() {
        WebElement product = driver.findElement(By.xpath("//span[@class='title']"));
        return product.isDisplayed();
    }

    public String getLoginErrorMessage() {
        return loginErrorMessage.getText();
    }
}
