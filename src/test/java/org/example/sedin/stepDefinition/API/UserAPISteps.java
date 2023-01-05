package org.example.sedin.stepDefinition.API;

import com.google.gson.Gson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.example.sedin.configuration.APIConfig;
import org.example.sedin.data.reqres.AuthenticationToken;
import org.example.sedin.model.CreateUsersResponse;
import org.example.sedin.model.RegisterUsersAuthResponse;
import org.example.sedin.model.listUsersResponse.ListUsersResponse;
import org.example.sedin.pages.API.AuthAPIController;
import org.example.sedin.pages.API.UserAPIController;
import org.testng.Assert;

import java.io.File;

import static org.hamcrest.Matchers.*;

public class UserAPISteps extends APIConfig {
  Response response;
  UserAPIController userApiController = new UserAPIController();
  AuthAPIController authAPIController = new AuthAPIController();
  CreateUsersResponse createUsersResponse = new CreateUsersResponse();
  ListUsersResponse listUsersResponse = new ListUsersResponse();
  RegisterUsersAuthResponse registerUsersAuthResponse = new RegisterUsersAuthResponse();
  private Gson gson = new Gson();

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

  @When("[POST] create users API is called to create users with request name {string} and job {string}")
  public void postCreateUsersAPIIsCalledToCreateUsersWithRequestNameAndJob(String name,
      String job) {
    //        listUsersResponse = gson.fromJson(userApiController.
    //                testPostRequests(name, job).asString(), ListUsersResponse.class);
    //        listUsersResponse.getData().get(0).getAvatar();

    response = userApiController.testPostRequests(name, job);
  }

  @Then("[POST] validate the json schema of the response {string}")
  public void postValidateTheJsonSchemaOfTheResponse(String schemaFile) {
    response.then().body(JsonSchemaValidator.matchesJsonSchema(new File(schemaFile)));
  }

  @When("[GET] users API is called to get the details of users")
  public void getUsersAPIIsCalledToGetTheDetailsOfUsers() {
    response = userApiController.getRequestTestwithRestAssuredConfig();
  }

  @Then("[GET] API should return response code {int}")
  public void getAPIShouldReturnResponseCode(int statusCode) {
    response.then().statusCode(statusCode);
  }

  @Then("users api response should match the schema {string}")
  public void usersApiResponseShouldMatchTheSchema(String schemaFile) {
    response.then().body(JsonSchemaValidator.matchesJsonSchema(new File(schemaFile)));
  }

  @Then("users api response should contain the first name {string}")
  public void usersApiResponseShouldContainTheFirstName(String firstName) {
    response.then().assertThat().body("data.first_name[0]", equalTo(firstName));
  }

  @Then("users api response should not be empty")
  public void usersApiResponseShouldNotBeEmpty() {
    response.then().body("$", is(not(empty())));
  }


  @When("[POST] register users API is called to register the users with username {string} and password {string}")
  public void postRegisterUsersAPIIsCalledToRegisterTheUsersWithUsernameAndPassword(String username,
      String password) {
    registerUsersAuthResponse =
        gson.fromJson(authAPIController.getAuthenticationToken(username, password).asString(),
            RegisterUsersAuthResponse.class);
    new AuthenticationToken().setToken(registerUsersAuthResponse.getToken()); //auth token and can use it in other APIs
  }

  @Then("[POST] auth token should be returned in response")
  public void postAuthTokenShouldBeReturnedInResponse() {
    Assert.assertNotNull(registerUsersAuthResponse.getToken());
  }

  @When("the API request is sent")
  public void theAPIRequestIsSent() {
    createUsersResponse = gson.fromJson(userApiController.postTestUsingBuilderPattern().asString(),
        CreateUsersResponse.class);
  }

  @Then("response code should be {int}")
  public void responseCodeShouldBe(int responseCode) {
    Assert.assertNotNull(createUsersResponse.getJob());
  }

  @When("the API request is sent with name {string} and job {string}")
  public void theAPIRequestIsSentWithNameAndJob(String name, String job) {
    //        createUsersResponse = gson.fromJson(userApiController.testPostRequests(name,job).asString(), CreateUsersResponse.class);
    response = userApiController.testPostRequests(name, job);
  }

}
