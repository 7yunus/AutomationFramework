@APITest
Feature: Parse request

  @API1
  Scenario: Parse request body
    Given the API request data is set
    When [POST] create users API is called to create users
    Then [POST] API should return response code 201

    @API2
  Scenario Outline: Json schema validation
    Given the API request data is set
    When the API request is sent with name "<name>" and job "<job>"
    Then validate the json schema of the response "<schemaFile>"
    Examples:
    |name|job| schemaFile|
    |  Yunus  |  QA |src/test/resources/createusersjsonschema.json|

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