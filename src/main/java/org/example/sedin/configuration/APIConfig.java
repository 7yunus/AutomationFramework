package org.example.sedin.configuration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

public class APIConfig {

  @BeforeClass
  public static RequestSpecification getRequestSpecification() { //public static
    return new RequestSpecBuilder().setBaseUri("https://reqres.in/")
        .addHeader("Accept", "application/json")
        //                .addFilter(new RequestLoggingFilter())
        //                .addFilter(new ResponseLoggingFilter())
        .log(LogDetail.URI)
        .build();
  }

  @AfterClass
  public static ResponseSpecification getResponseSpecification() {
    return new ResponseSpecBuilder().log(LogDetail.BODY)
        .expectResponseTime(lessThan(5000L))
        .expectContentType(ContentType.JSON)
        .build();
  }
}