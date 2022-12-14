package org.example.sedin.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.example.sedin.data.Data;
import org.example.sedin.pages.LoginPage;
import org.example.sedin.runner.ListenerClass;
import org.testng.Assert;
import org.testng.annotations.Listeners;

@Listeners({ListenerClass.class })
public class LoginSteps {
    LoginPage loginPage = new LoginPage();

    @Step("Entering username and password")
    @When("User enters username {string} and password {string}")
    public void userEntersUsernameAndPassword(String username, String password) {
        loginPage.login(username,password);
    }

    @And("User clicks on Login button")
    public void userClicksOnLoginButton() {
        loginPage.clickLoginBtn();
    }

    @Then("User enters the products webpage")
    public void userEntersTheProductsWebpage() {
        Assert.assertTrue(loginPage.isProductPageOpened());
    }

    @Then("error message {string} should be displayed to the user")
    public void errorMessageShouldBeDisplayedToTheUser(String errorMessage) {
        Assert.assertTrue(loginPage.getLoginErrorMessage().contains(errorMessage));
    }
}
