package org.example.sedin.apiTests;

import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.sedin.data.reqres.AuthenticationPojo;
import org.example.sedin.data.reqres.AuthenticationToken;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthTokenPassingExampleTest {

    private static final String URL = "https://reqres.in";
    private static final Logger LOG = LogManager.getLogger(AuthTokenPassingExampleTest.class);

    @Test
    public void getToken() {
        AuthenticationPojo requestBody = new AuthenticationPojo("eve.holt@reqres.in", "pistol");
        AuthenticationToken authenticationToken = given().contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .log().uri()
                .post(URL + "/api/register")
                .then()
                .assertThat()
                .statusCode(200)
                .log().body()
                .body("id", notNullValue())
                .and()
                .body("token", notNullValue())
                .and()
                .extract().as(AuthenticationToken.class);

        LOG.info(authenticationToken.getToken()); //take the token/auth from one request and pass to next request

    }

}