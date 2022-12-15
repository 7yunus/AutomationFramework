package org.example.sedin.pages.API;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.sedin.configuration.APIConfig;
import org.example.sedin.data.reqres.PostData;
import org.example.sedin.data.reqres.UserData;
import org.example.sedin.model.CreateUserRequest;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserAPIController extends APIConfig {
    private static final Logger LOG = LogManager.getLogger(UserAPIController.class);

    public Response postTestUsingBuilderPattern() {
        UserData userData = userDataBuilder();
        Response response = given()
                .spec(getRequestSpecification())
                .body(userData)
                .when()
                .post("/api/users")
                .then().
                spec(getResponseSpecification())
                .extract().response();
        return response;
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


    public Response testPostRequests(final String name, final String job) {
        InputStream createUsersJsonSchema = getClass().getClassLoader()
                .getResourceAsStream("createUsersJsonSchema.json");
        final PostData postData = new PostData(name, job);
        return given().contentType(ContentType.JSON)
                .spec(getRequestSpecification())
                .body(postData)
                .when()
                .post("/api/users")
                .then()
                .spec(getResponseSpecification())
                .extract().response();
    }


    public Response getRequestTestwithRestAssuredConfig() {
        return given()
                .spec(getRequestSpecification())
                .when()
                .queryParam("page", 2)
                .get("/api/users")
                .then()
                .spec(getResponseSpecification())
                .extract().response();
    }
}
