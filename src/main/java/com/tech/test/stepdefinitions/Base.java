package com.tech.test.stepdefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.test.client.RestAssuredClient;
import com.tech.test.utils.ScenarioContext;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public abstract class Base {

  @Autowired
  protected String favQuotesBaseUrl;

  @Autowired
  protected String accessToken;

  @Autowired
  protected ScenarioContext scenarioContext;

  @Autowired
  protected RestAssuredClient restAssuredClient;

  protected ObjectMapper objectMapper = new ObjectMapper();
}
