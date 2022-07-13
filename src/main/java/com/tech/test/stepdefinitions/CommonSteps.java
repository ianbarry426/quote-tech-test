package com.tech.test.stepdefinitions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static java.nio.charset.Charset.defaultCharset;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

public class CommonSteps extends Base {

  @Given("we use quotes base url with {string} endpoint")
  public void useQuotesBaseUrl(String endpoint) {
    RestAssured.baseURI = favQuotesBaseUrl + endpoint;
  }

  @Then("the response code is {int}")
  public void verifyResponseCode(int responseCode) {
    assertThat(scenarioContext.getResponse().getStatusCode(), equalTo(responseCode));
  }

  @And("verify that response body matches the {string} schema")
  public void verifyResponseBodyMatchesSchema(String schemaType) throws IOException {
    String schema = IOUtils
            .toString(this.getClass().getResourceAsStream("/schemas/" + schemaType + ".json"),
                    defaultCharset());
    scenarioContext.getResponse().then().assertThat().body(matchesJsonSchema(schema));
  }
}
