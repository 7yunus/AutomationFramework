package org.example.sedin.stepDefinition.API;

import com.google.gson.Gson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.example.sedin.configuration.APIConfig;
import org.example.sedin.model.CreateUsersResponse;
import org.example.sedin.model.RegisterUsersAuthResponse;
import org.example.sedin.model.listUsersResponse.ListUsersResponse;
import org.example.sedin.pages.API.AuthAPIController;
import org.example.sedin.pages.API.UserAPIController;
import org.testng.Assert;

import java.io.File;

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

    @When("[POST] create users API is called to create users")
    public void postCreateUsersAPIIsCalledToCreateUsers() {
        response = userApiController.postTestUsingBuilderPattern();
//        createUsersResponse = gson.fromJson(userApiController.postTestUsingBuilderPattern().asString(), CreateUsersResponse.class);
    }

    @Then("[POST] API should return response code {int}")
    public void postAPIShouldReturnResponseCode(int responseCode) {
        response.then().assertThat().statusCode(responseCode);
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
//        createUsersResponse = gson.fromJson(userApiController.testPostRequests(name,job).asString(), CreateUsersResponse.class);

        response = userApiController.testPostRequests(name,job);
    }

    @Then("validate the json schema of the response {string}")
    public void validateTheJsonSchemaOfTheResponse(String schemaFile) {
        response.then().body(JsonSchemaValidator
                .matchesJsonSchema(new File(schemaFile)));
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
