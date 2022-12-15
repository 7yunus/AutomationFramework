package org.example.sedin.pages.API;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
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

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserAPIController extends APIConfig {
    private static final Logger LOG = LogManager.getLogger(UserAPIController.class);
//    RequestSpecification request = new RequestSpecBuilder().addHeader("Content-Type", "application/json")
//            .setBaseUri("https://reqres.in/")
//            .addHeader("Accept", "application/json")
//            .log(LogDetail.URI)
//            .build();


    public Response postTestUsingBuilderPattern() {
        UserData userData = userDataBuilder();
       Response response =  given()
//               .spec(request)
               .body(userData)
                .when()
                .post("/api/users");
//                .then()
//                .statusCode(201)
//                .and()
//                .assertThat()
//                .body("name", equalTo(userData.getName()))
//                .body("job", equalTo(userData.getJob()));

        //just for example purpose
//        userDataBuilder2();
//        userDataBuilder3();
//        userDataBuilder4();

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
                .getResourceAsStream("createusersjsonschema.json");
        final PostData postData = new PostData(name, job);
        return given().contentType(ContentType.JSON)
                .spec(getRequestSpecification())
                .body(postData)
                .when()
                .post("/api/users")
                .then()
                .spec(getResponseSpecification())
                .assertThat()
                .body("data.first_name[0]", equalTo("Michael"))
                .body(JsonSchemaValidator.matchesJsonSchema(createUsersJsonSchema))
//                .statusCode(201)
//                .and()
//                .assertThat()
//                .body(
//                        "name", equalTo(name),
//                        "job", equalTo(job),
//                        "id", notNullValue(),
//                        "createdAt", notNullValue()
//                )
                .extract().response();
    }


    public Response getRequestTestwithRestAssuredConfig() {
        return given().when()
                .queryParam("page", 2)
                .get("/api/users")
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("data.first_name[0]", equalTo("Michael"))
                .extract().response();

//        ListUsersResponse listUsersResponse = new Gson().fromJson(response, ListUsersResponse.class);
//        LOG.info("avatar: " + listUsersResponse.getData().get(0).getAvatar());
//        LOG.info("support text: " + listUsersResponse.getSupport().getText());
//
//        Assert.assertTrue(listUsersResponse.getData().get(0).getAvatar().contains("reqres"));
    }
}
