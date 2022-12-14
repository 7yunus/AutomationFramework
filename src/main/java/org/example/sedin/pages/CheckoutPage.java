package org.example.sedin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

import static org.example.sedin.configuration.DriverSetup.driver;

public class CheckoutPage {
    private static final Logger LOG = LogManager.getLogger(CheckoutPage.class);

    @FindBy(xpath = "//button[@id='checkout']")
    WebElement checkoutButton;

    @FindBy(xpath = "//input[@id='first-name']")
    WebElement firstNameField;
    @FindBy(xpath = "//input[@id='last-name']")
    WebElement lastNameField;
    @FindBy(xpath = "//input[@id='postal-code']")
    WebElement zipField;

    @FindBy(xpath = "//input[@id='continue']")
    WebElement continueButton;

    @FindBy(xpath = "//div[@class='inventory_item_desc']")
    WebElement itemDesc;
    @FindBy(xpath = "//div[@class='inventory_item_price']")
    WebElement itemPrice;

    @FindBy(xpath = "//button[@id='finish']")
    WebElement finishButton;

    @FindBy(xpath = "//h2[@class='complete-header']")
    WebElement thankYouMessageText;

    public CheckoutPage() {
        PageFactory.initElements(driver, this);
    }

    public void clickOnCheckoutButton() {
        LOG.info("Clicking on checkout button");
        checkoutButton.click();
    }

    public void fillCheckoutForm(String firstName, String lastName, String zipPostalCode) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        zipField.sendKeys(zipPostalCode);
    }

    public void clickOnContinueButton() {
        LOG.info("Clicking on continue button after filling form");
        continueButton.click();
    }

    public ArrayList<Object> getCheckoutProductDetails() {
        ArrayList<Object> productDetails = new ArrayList<>();
        productDetails.add(Double.parseDouble(itemPrice.getText().replace("$","")));
        productDetails.add(itemDesc.getText().trim());
        LOG.info("productDetails: "+productDetails);
        return productDetails;
    }


    public void clickOnFinishButton() {
        LOG.info("Clicking on checkout button");
        finishButton.click();
    }

    public String getThankYouMessage() {
       return thankYouMessageText.getText();
    }

}
