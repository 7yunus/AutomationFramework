package org.example.sedin.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.sedin.pages.LoginPage;
import org.testng.Assert;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();

    @When("User enters username {string} and password {string}")
    public void userEntersUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @And("User clicks on Login button")
    public void userClicksOnLoginButton() {
        loginPage.clickLoginBtn();
    }

    @Then("User enters the products webpage")
    public void userEntersTheProductsWebpage() {
        Assert.assertTrue(loginPage.isProductPageOpened(), "Product listing page is not loading...");
    }

    @Then("error message {string} should be displayed to the user")
    public void errorMessageShouldBeDisplayedToTheUser(String errorMessage) {
        Assert.assertTrue(loginPage.getLoginErrorMessage().contains(errorMessage),
                "Error message is not displayed for invalid login credentials");
    }
}
