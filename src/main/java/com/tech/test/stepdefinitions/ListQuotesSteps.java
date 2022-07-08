package com.tech.test.stepdefinitions;

import static com.tech.test.utils.TestConstants.HTTP_RESPONSE;
import static com.tech.test.utils.TestConstants.LAST_PAGE;
import static com.tech.test.utils.TestConstants.PAGE;
import static com.tech.test.utils.TestConstants.QUOTES_AUTHOR;
import static com.tech.test.utils.TestConstants.QUOTES_BODY;
import static com.tech.test.utils.TestConstants.QUOTES_DIALOGUE;
import static com.tech.test.utils.TestConstants.QUOTES_FAVORITE;
import static com.tech.test.utils.TestConstants.QUOTES_FAVORITES_COUNT;
import static com.tech.test.utils.TestConstants.QUOTES_ID;
import static com.tech.test.utils.TestConstants.QUOTES_TAGS;
import static com.tech.test.utils.TestUtils.buildHeaders;
import static com.tech.test.utils.TestUtils.getListOfReturnedQuotes;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import com.tech.test.model.Quote;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ListQuotesSteps extends Base {

  @When("a request is sent to get a list of quotes")
  public void requestListOfQuotes() {
    scenarioContext.addParam(HTTP_RESPONSE, restAssuredClient.get(RestAssured.baseURI,
            buildHeaders(accessToken)));
  }

  @When("a request is sent to get list of quotes on page {int}")
  public void requestListOfQuotesOnSpecificPage(int pageNum) {
    RestAssured.baseURI += String.format("?page=%s", pageNum);
    scenarioContext.addParam(HTTP_RESPONSE, restAssuredClient.get(RestAssured.baseURI,
            buildHeaders(accessToken)));
  }

  @Then("25 quotes are returned on page {int}")
  public void numberOfQuotesReturned(int pageNumber) {
    List<Quote> returnedQuotes = getListOfReturnedQuotes(objectMapper, scenarioContext);
    assertThat(returnedQuotes.size(), equalTo(25));
    scenarioContext.getResponse().then().assertThat()
            .body(PAGE, equalTo(pageNumber))
            .body(LAST_PAGE, equalTo(false));
  }

  @When("a request is sent to get a list of quotes with filter")
  public void getListOfQuotesWithFilter(Map<String, String> queryParams) {
    scenarioContext.addParam(HTTP_RESPONSE, restAssuredClient.get(RestAssured.baseURI,
            buildHeaders(accessToken), queryParams));
  }

  @And("the author is {string} for each returned entry")
  public void verifyCorrectReturnedAuthorOnResponse(String author) {
    scenarioContext.getResponse().then().assertThat()
            .body(QUOTES_AUTHOR, everyItem(is(author)));
  }

  @And("the list of tags contains {string} for each returned entry")
  public void tagListContainsKeyWord(String tag) {
    scenarioContext.getResponse().then().assertThat()
            .body("quotes.tags", everyItem(hasItem(tag)));
  }

  @And("user is informed that no quotes have been found")
  public void noQuotesFoundResponse() {
    scenarioContext.getResponse().then().assertThat()
            .body(QUOTES_ID, equalTo(0))
            .body(QUOTES_FAVORITES_COUNT, equalTo(0))
            .body(QUOTES_FAVORITE, is(false))
            .body(QUOTES_DIALOGUE, is(false))
            .body(QUOTES_BODY, is("No quotes found"))
            .body(QUOTES_TAGS, is(Collections.emptyList()));
  }
}
