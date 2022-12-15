package org.example.sedin.stepDefinition.API;

import com.google.gson.Gson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.sedin.configuration.APIConfig;
import org.example.sedin.model.CreateUsersResponse;
import org.example.sedin.model.RegisterUsersAuthResponse;
import org.example.sedin.model.listUsersResponse.ListUsersResponse;
import org.example.sedin.pages.API.AuthAPIController;
import org.example.sedin.pages.API.UserAPIController;
import org.testng.Assert;

public class UserAPISteps extends APIConfig {
    private Gson gson = new Gson();
    Response response;
    UserAPIController userApiController = new UserAPIController();
    AuthAPIController authAPIController = new AuthAPIController();
    CreateUsersResponse createUsersResponse = new CreateUsersResponse();
    ListUsersResponse listUsersResponse = new ListUsersResponse();
    RegisterUsersAuthResponse registerUsersAuthResponse = new RegisterUsersAuthResponse();


    @Given("the API request data is set")
    public void theAPIRequestDataIsSet() {
    }

    @When("the API request is sent")
    public void theAPIRequestIsSent() {
        createUsersResponse = gson.fromJson(userApiController.postTestUsingBuilderPattern().asString(), CreateUsersResponse.class);
    }

    @Then("response code should be {int}")
    public void responseCodeShouldBe(int responseCode) {
        Assert.assertNotNull(createUsersResponse.getJob());
    }

    @When("the API request is sent with name {string} and job {string}")
    public void theAPIRequestIsSentWithNameAndJob(String name, String job) {
        createUsersResponse = gson.fromJson(userApiController.testPostRequests(name,job).asString(), CreateUsersResponse.class);
    }

    @Then("validate the json schema of the response")
    public void validateTheJsonSchemaOfTheResponse() {
        Assert.assertNotNull(createUsersResponse.getId());
    }

    @When("the GET API request is sent")
    public void theGETAPIRequestIsSent() {
        listUsersResponse = gson.fromJson(userApiController.getRequestTestwithRestAssuredConfig().asString(), ListUsersResponse.class);
    }

    @Then("GET response code should be {int}")
    public void getResponseCodeShouldBe(int arg0) {
//        Assert.assertNotNull(listUsersResponse.getData().get(0).getAvatar());
    }

    @When("the Auth API request is sent")
    public void theAuthAPIRequestIsSent() {
        registerUsersAuthResponse = gson.fromJson(authAPIController.getAuthenticationToken().asString(), RegisterUsersAuthResponse.class);
        Assert.assertNotNull(registerUsersAuthResponse.getToken());

    }
}
