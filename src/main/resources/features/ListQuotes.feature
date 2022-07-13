@ListQuotes
Feature: GET a list of quotes

  Background:
    Given we use quotes base url with "quotes" endpoint

  @Smoke
  Scenario: Verify that user can request a list of quotes
    When a request is sent to get a list of quotes
    Then the response code is 200
    And verify that response body matches the "listQuotes" schema

  Scenario: Verify that 25 quotes are returned per page
    When a request is sent to get a list of quotes
    Then the response code is 200
    And 25 quotes are returned on page 1
    And verify that response body matches the "listQuotes" schema

  Scenario: Verify that user can specify which page of quotes is returned
    When a request is sent to get list of quotes on page 2
    Then the response code is 200
    And 25 quotes are returned on page 2
    And verify that response body matches the "listQuotes" schema

  @Smoke @Filter
  Scenario Outline: Verify that list of quotes can be filtered by type author
    When a request is sent to get a list of quotes with filter
      | <param1> | <value1> |
      | <param2> | <value2> |
    Then the response code is 200
    And the author is "<author>" for each returned entry
    And verify that response body matches the "listQuotes" schema

    Examples:
      | param1 | value1         | param2 | value2 | author         |
      | filter | Mark+Twain     | type   | author | Mark Twain     |
      | filter | Nelson+Mandela | type   | author | Nelson Mandela |

  @Filter
  Scenario Outline: Verify that list of quotes can be filtered by tags
    When a request is sent to get a list of quotes with filter
      | <param1> | <value1> |
      | <param2> | <value2> |
    Then the response code is 200
    And the list of tags contains "<tag>" for each returned entry
    And verify that response body matches the "listQuotes" schema

    Examples:
      | param1 | value1 | param2 | value2 | tag   |
      | filter | funny  | type   | tag    | funny |

  @Filter
  Scenario: Attempt to filter by non-existent author
    When a request is sent to get a list of quotes with filter
      | filter | Jane+Doe356 |
      | type   | author      |
    Then the response code is 200
    And user is informed that no quotes have been found