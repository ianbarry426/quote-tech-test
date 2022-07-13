package com.tech.test.client;

import static io.restassured.RestAssured.given;

import com.tech.test.stepdefinitions.Base;
import io.restassured.response.Response;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class RestAssuredClient extends Base {

  //POST Requests
  public Response post(String url, Map<String, String> headers, String payload) {
    return given()
            .log().body()
            .headers(headers)
            .body(payload)
            .post(url)
            .then()
            .extract().response()
            .prettyPeek();
  }

  // GET Requests
  public Response get(String url, Map<String, String> headers) {
    return given()
            .headers(headers)
            .when()
            .get(url)
            .then()
            .extract().response()
            .prettyPeek();
  }

  public Response get(String url, Map<String, String> headers, Map<String, String> queryParams) {
    return given()
            .headers(headers)
            .params(queryParams)
            .when()
            .get(url)
            .then()
            .extract().response()
            .prettyPeek();
  }

  // PUT Requests
  public Response put(String url, Map<String, String> headers) {
    return given()
            .log().body()
            .headers(headers)
            .put(url)
            .then()
            .extract().response()
            .prettyPeek();
  }

  // DELETE Requests
  public Response delete(String url, Map<String, String> headers) {
    return given()
            .headers(headers)
            .when()
            .delete(url)
            .then()
            .extract().response()
            .prettyPeek();
  }

}
