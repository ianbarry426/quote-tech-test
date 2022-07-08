package com.tech.test.stepdefinitions;

import static com.tech.test.utils.TestConstants.*;
import static com.tech.test.utils.TestUtils.buildHeaders;
import static com.tech.test.utils.TestUtils.buildHeadersWithUserToken;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tech.test.model.CreateSessionRequest;
import com.tech.test.utils.TestUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class CucumberHooks extends Base {

  @Before(value = "@CreateSession", order = 1)
  public void createSession() throws JsonProcessingException {
    RestAssured.baseURI = favQuotesBaseUrl + SESSION;
    CreateSessionRequest sessionRequest = CreateSessionRequest.builder()
            .user(TestUtils.buildUser())
            .build();

    String payload = objectMapper.writeValueAsString(sessionRequest);

    scenarioContext.addParam(HTTP_RESPONSE, restAssuredClient.post(RestAssured.baseURI,
            buildHeaders(accessToken), payload));

    String userToken = scenarioContext.getResponse().then().extract().body().path(USER_TOKEN);
    scenarioContext.addParam(USER_TOKEN, userToken);
  }

  @Before(order = 0)
  public void resetContext() {
    scenarioContext.refreshContext();
  }

  @After(value = "@DestroySession", order = 0)
  public void destroySession() {
    restAssuredClient.delete(RestAssured.baseURI,
            buildHeadersWithUserToken(accessToken, scenarioContext));
  }

}
