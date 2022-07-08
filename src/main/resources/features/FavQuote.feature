@FavQuote
@CreateSession
@DestroySession
Feature: Mark a quote as a users favourite

#  To get a non-favorite quote, each scenario will get a list of quotes (from listQuotes endpoint),
#  save back to a list of Quote objects
#  then stream that list and extract the first quote where favourites_count = 0

  @Smoke
  Scenario: Successfully mark an existing quote as a users favourite
    Given a non-favourite quote exists
    When a request is sent to mark the quote as a favourite
    Then the response code is 200
    And the response contains updated favCount 1 and favorite true details

  @Smoke
  Scenario: Successfully unmark an existing quote as a users favourite
    Given a non-favourite quote exists
    And a request is sent to mark the quote as a favourite
    And the response code is 200
    When a request is sent to unmark the quote as a favourite
    Then the response code is 200
    And the response contains updated favCount 0 and favorite false details

  Scenario: Unsuccessfully attempt to mark a non-existent quote as a users favourite
    Given an attempt to mark a non-existent quote as a users favourite
    Then the response code is 404
    And the response contains the following message
      | "status":404,"error":"Not Found" |