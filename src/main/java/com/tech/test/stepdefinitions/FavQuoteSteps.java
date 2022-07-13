package com.tech.test.stepdefinitions;

import static com.tech.test.utils.TestConstants.*;
import static com.tech.test.utils.TestUtils.buildHeaders;
import static com.tech.test.utils.TestUtils.buildHeadersWithUserToken;
import static com.tech.test.utils.TestUtils.getListOfReturnedQuotes;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tech.test.model.Quote;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import java.util.List;

public class FavQuoteSteps extends Base {

  @Given("a non-favourite quote exists")
  public void nonFavouriteQuoteExists() {
    String endpoint = favQuotesBaseUrl + QUOTES;
    scenarioContext.addParam(HTTP_RESPONSE,
            restAssuredClient.get(endpoint, buildHeaders(accessToken)));
    List<Quote> quotes = getListOfReturnedQuotes(objectMapper, scenarioContext);
    Quote quote = quotes.stream()
            .filter(q -> q.getFavourites_count() == 0)
            .findFirst()
            .orElseThrow(() -> new RuntimeException(
                    "All returned quotes currently fav'd"
            ));
    scenarioContext.addParam(QUOTE_ID, quote.getId());
  }

  @Given("an attempt to mark a non-existent quote as a users favourite")
  public void markNonExistentQuoteAsUsersFavourite() {
    String endpoint = favQuotesBaseUrl + format("quotes/%s/fav", 200000);
    scenarioContext.addParam(HTTP_RESPONSE,
            restAssuredClient.put(endpoint, buildHeadersWithUserToken(accessToken, scenarioContext)));
  }

  @And("the response contains the following message")
  public void verifyThatResponseContainsFollowingMessages(List<String> messages) {
    messages.forEach(
            message -> assertThat(format("String %s is not contained in the response", message),
                    scenarioContext.getResponse().getBody().asString(), containsString(message)));
  }

  @When("a request is sent to {quoteMark} the quote as a favourite")
  public void requestIsSentToFavouriteQuote(String quoteMark) {
    String fav = (MARK).equals(quoteMark) ? FAV : UNFAV;
    String endpoint = favQuotesBaseUrl + format("quotes/%s/%s",
            scenarioContext.getParam(QUOTE_ID), fav);
    scenarioContext.addParam(HTTP_RESPONSE, restAssuredClient.put(endpoint,
            buildHeadersWithUserToken(accessToken, scenarioContext)));
  }

  @And("^the response contains updated favCount (.+) and favorite (.+) details$")
  public void verifyUpdatedFavouriteDetails(int favCount, boolean favorite) {
    scenarioContext.getResponse().then().assertThat()
            .body(ID, equalTo(scenarioContext.getParam(QUOTE_ID)))
            .body(USER_DETAILS_FAVORITE, is(favorite));
  }
}
