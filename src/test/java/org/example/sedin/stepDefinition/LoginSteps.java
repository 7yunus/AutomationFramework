package org.example.sedin.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.sedin.data.Data;
import org.example.sedin.pages.LoginPage;
import org.testng.Assert;

public class LoginSteps {

    LoginPage loginPage = new LoginPage();
    Data data = new Data();

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


//    @When("User clicks on Login button")
//    public void user_clicks_on_Login_button() throws InterruptedException {
//        WebElement LoginBtn=driver.findElement(By.id("login-button"));
//        Thread.sleep(5000);
//        LoginBtn.click();
//    }
//    @Then("User enters the products webpage")
//    public void user_enters_the_products_webpage() {
//        String ExpTitle="PRODUCTS";
//        WebElement product=driver.findElement(By.xpath("//span[@class='title']"));
//        String actual=product.getText();
//        Assert.assertEquals(ExpTitle, actual);
//    }
//    @Then("User finds an error message {string}")
//    public void user_finds_an_error_message(String ExpMsg) {
//        WebElement ErrorMsg = driver.findElement(By.xpath("//h3[contains(text(),'Epic sadface')]"));
//        String ActualMsg = ErrorMsg.getText();
//        Assert.assertEquals(ActualMsg, ExpMsg);
//    }
}
