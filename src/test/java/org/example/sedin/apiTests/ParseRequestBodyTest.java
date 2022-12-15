package org.example.sedin.apiTests;

import com.github.javafaker.Faker;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.sedin.apiTests.configuration.APIConfig;
import org.example.sedin.data.reqres.UserData;
import org.example.sedin.model.CreateUserRequest;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("APITests")
@Feature("How do you parse the request body using the request builder")
public class ParseRequestBodyTest extends APIConfig {

    private static final Logger LOG = LogManager.getLogger(ParseRequestBodyTest.class);

    @Test
    public void postTestUsingBuilderPattern() {
        UserData userData = userDataBuilder();
        given().body(userData)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .and()
                .assertThat()
                .body("name", equalTo(userData.getName()))
                .body("job", equalTo(userData.getJob()));

        //just for example purpose
        userDataBuilder2();
        userDataBuilder3();
        userDataBuilder4();
    }

    //1st option
    private UserData userDataBuilder() {
        Faker faker = Faker.instance();
        return UserData.builder()
                .name(faker.name()
                        .firstName())
                .job(faker.company()
                        .profession())
                .build();
    }

    //2nd option: to create request object and use above, given().body (userDataBuilder2().toString())
    private JSONObject userDataBuilder2() {
        JSONObject reqJson = new JSONObject();
        reqJson.put("name", "Yunus");
        reqJson.put("job", "QA");
        LOG.info(reqJson);
        return reqJson;
    }

    //3rd option: create request pojo class and set the values
    private CreateUserRequest userDataBuilder3() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setName("Yunus");
        createUserRequest.setJob("QA");
        LOG.info(createUserRequest);
        return createUserRequest;
    }

    //4th option: create request using HashMap
    private JSONObject userDataBuilder4() {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("name", "Yunus");
        requestMap.put("job", "QA");
        JSONObject reqJson = new JSONObject(requestMap);
        LOG.info(reqJson);
        return reqJson;
    }

    //5th option: Directly pass as string but not recommended

}