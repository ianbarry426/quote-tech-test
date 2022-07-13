package com.tech.test.utils;

import static com.tech.test.utils.TestConstants.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.test.model.Quote;
import com.tech.test.model.User;
import com.tech.test.stepdefinitions.Base;
import java.util.List;
import lombok.experimental.UtilityClass;
import java.util.Map;

@UtilityClass
public class TestUtils extends Base {

  public static Map<String, String> buildHeaders(String accessToken) {
    return Map.of(
            CONTENT_TYPE, APPLICATION_JSON,
            AUTHORIZATION, "Token token=" + accessToken);
  }

  public static Map<String, String> buildHeadersWithUserToken(
          String accessToken, ScenarioContext scenarioContext) {
    return Map.of(
            CONTENT_TYPE, APPLICATION_JSON,
            AUTHORIZATION, "Token token=" + accessToken,
            USER_TOKEN, scenarioContext.getParam(USER_TOKEN).toString());
  }

  public static User buildUser() {
    return User.builder()
            .login(LOGIN_CREDENTIALS.get(LOGIN))
            .password(LOGIN_CREDENTIALS.get(PASSWORD))
            .build();
  }

  public static List<Quote> getListOfReturnedQuotes(
          ObjectMapper objectMapper, ScenarioContext scenarioContext) {
    return objectMapper.convertValue(
            scenarioContext.getResponse().jsonPath().get(QUOTES),
              new TypeReference<List<Quote>>() {});
  }
}
