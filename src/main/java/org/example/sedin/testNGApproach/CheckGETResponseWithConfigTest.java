package org.example.sedin.testNGApproach;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.sedin.configuration.APIConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("APITests")
@Feature("How do you validate the data inside the array of array?")
public class CheckGETResponseWithConfigTest extends APIConfig {

    private static final Logger LOG = LogManager.getLogger(CheckGETResponseWithConfigTest.class);

    @Test
    @Description("getRequestTestwithRestAssuredConfig")
    public void getRequestTestwithRestAssuredConfig() {
        String response = given().when()
                .queryParam("page", 2)
                .get("/api/users")
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("data.first_name[0]", equalTo("Michael"))
                .extract().response().asString();

//        ListUsersResponse listUsersResponse = new Gson().fromJson(response, ListUsersResponse.class);
//        LOG.info("avatar: " + listUsersResponse.getData().get(0).getAvatar());
//        LOG.info("support text: " + listUsersResponse.getSupport().getText());

//        Assert.assertTrue(listUsersResponse.getData().get(0).getAvatar().contains("reqres"));
    }
}