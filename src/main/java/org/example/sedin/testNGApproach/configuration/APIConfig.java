package org.example.sedin.testNGApproach.configuration;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

public class APIConfig {


  @BeforeClass
  public void setup() {

    RequestSpecification request =
        new RequestSpecBuilder().addHeader("Content-Type", "application/json")
            .setBaseUri("https://reqres.in/")
            .addHeader("Accept", "application/json")
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            //                .log(LogDetail.URI)
            .build();
    //
    ResponseSpecification response =
        new ResponseSpecBuilder().log(LogDetail.BODY).expectResponseTime(lessThan(5000L)).build();

    RestAssured.requestSpecification = request;
    RestAssured.responseSpecification = response;
  }

}