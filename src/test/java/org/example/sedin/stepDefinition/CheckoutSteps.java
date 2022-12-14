package org.example.sedin.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.example.sedin.data.Data;
import org.example.sedin.pages.CheckoutPage;
import org.testng.Assert;

public class CheckoutSteps {
    CheckoutPage checkoutPage = new CheckoutPage();
    Data data = new Data();

    @And("proceeds to checkout page")
    public void proceedsToCheckoutPage() {
        checkoutPage.clickOnCheckoutButton();
    }

    @And("fills checkout form with first name {string}, last name {string} and zip code {string} and submits")
    public void fillsCheckoutFormWithFirstNameLastNameAndZipCodeAndSubmits
            (String firstName, String lastName, String zipCode) {
        checkoutPage.fillCheckoutForm(firstName,lastName,zipCode);
        checkoutPage.clickOnContinueButton();
    }

    @And("verifies the product details and clicks the finish button")
    public void verifiesTheProductDetailsAndClicksTheFinishButton() {
        System.out.println(data.getItemDescription());
        Assert.assertTrue(checkoutPage.getCheckoutProductDetails().contains(data.getItemDescription()));
        Assert.assertTrue(checkoutPage.getCheckoutProductDetails().contains(data.getPriceValue()));
        checkoutPage.clickOnFinishButton();
    }

    @Then("success order message {string} should be displayed to user")
    public void successOrderMessageShouldBeDisplayedToUser(String message) {
        Assert.assertTrue(checkoutPage.getThankYouMessage().equalsIgnoreCase(message));
    }
}
