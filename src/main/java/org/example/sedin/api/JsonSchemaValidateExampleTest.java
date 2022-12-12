package org.example.sedin.api;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.sedin.data.reqres.PostData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class JsonSchemaValidateExampleTest {

    private static final Logger LOG = LogManager.getLogger(JsonSchemaValidateExampleTest.class);
    private static final String URL = "https://reqres.in";

    @DataProvider(name = "postData")
    public Iterator<Object[]> postData() {
        final List<Object[]> postData = new ArrayList<>();
        postData.add(new Object[]{"Mohammed", "QA"});
        postData.add(new Object[]{"Yunus", "Sr.QA"});
        postData.add(new Object[]{"John", "Dev"});
        postData.add(new Object[]{"Johnny", "Product Manager"});
        return postData.iterator();
    }

    @Test(dataProvider = "postData")
    public void testPostRequests(final String name, final String job) {
        InputStream createUsersJsonSchema = getClass().getClassLoader()
                .getResourceAsStream("createusersjsonschema.json");
        final PostData postData = new PostData(name, job);
        String response = given().contentType(ContentType.JSON)
                .body(postData)
                .when()
                .post(URL + "/api/users")
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(createUsersJsonSchema))
                .statusCode(201)
                .and()
                .assertThat()
                .body(
                        "name", equalTo(name),
                        "job", equalTo(job),
                        "id", notNullValue(),
                        "createdAt", notNullValue()
                ).extract().response().asString();
        LOG.info(response);

    }
}

//Schema created online using  https://extendsclass.com/json-schema-validator.html