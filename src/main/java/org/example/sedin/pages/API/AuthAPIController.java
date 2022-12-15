package org.example.sedin.pages.API;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.sedin.configuration.APIConfig;
import org.example.sedin.data.reqres.AuthenticationPojo;

import static io.restassured.RestAssured.given;

public class AuthAPIController extends APIConfig {
    private static final Logger LOG = LogManager.getLogger(AuthAPIController.class);

    public Response getAuthenticationToken(String username, String password) {
        AuthenticationPojo requestBody = new AuthenticationPojo(username, password);

        return given().contentType(ContentType.JSON)
                .spec(getRequestSpecification())
                .body(requestBody)
                .when()
                .post("/api/register")
                .then()
                .spec(getResponseSpecification())
                .extract().response();
    }
}
