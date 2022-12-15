@APITest
Feature: Parse request

  @API1
  Scenario: Parse request body
    Given the API request data is set
    When the API request is sent
    Then response code should be 200

    @API2
  Scenario Outline: Json schema validation
    Given the API request data is set
    When the API request is sent with name "<name>" and job "<job>"
    Then validate the json schema of the response
    Examples:
    |name|job|
    |  Yunus  |  QA |

      @API3
  Scenario: GET API
    Given the API request data is set
    When the GET API request is sent
    Then GET response code should be 200

  @API4
  Scenario: GET API4
    Given the API request data is set
    When the Auth API request is sent
    Then GET response code should be 200