package org.example.sedin.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.example.sedin.configuration.DriverManager.DRIVER;
import static org.example.sedin.configuration.DriverManager.getDriver;


public class LoginPage {

    @FindBy(xpath = "//h3[@data-test='error']")
    public WebElement loginErrorMessage;
    @FindBy(id = "user-name")
    public WebElement loginUsername;
    @FindBy(id = "password")
    public WebElement loginPassword;
    @FindBy(id = "login-button")
    public WebElement loginButton;
    @FindBy(xpath = "//span[@class='title']")
    public WebElement productCard;
    public LoginPage() {
        PageFactory.initElements(DRIVER.get(), this);
    }

    public void login(String username, String password) {
        getDriver().get("https://www.saucedemo.com/");
        loginUsername.sendKeys(username);
        loginPassword.sendKeys(password);
    }

    public void clickLoginBtn() {
        loginButton.click();
    }

    public boolean isProductPageOpened() {
        return productCard.isDisplayed();
    }

    public String getLoginErrorMessage() {
        return loginErrorMessage.getText();
    }
}
