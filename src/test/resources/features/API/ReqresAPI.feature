@APITests @APIRegression
Feature: Reqres API validation

  Scenario: Verify create users POST API /api/users
    When [POST] create users API is called to create users
    Then [POST] API should return response code 201

  Scenario Outline: Json schema validation check for API /api/users
    When [POST] create users API is called to create users with request name "<name>" and job "<job>"
    Then [POST] validate the json schema of the response "<schemaFile>"
    Examples:
      | name  | job | schemaFile                                    |
      | Yunus | QA  | src/test/resources/schemas/createUsersJsonsShema.json |

  Scenario: Verify get users list GET API /api/users?page=2
    When [GET] users API is called to get the details of users
    Then [GET] API should return response code 200
      * users api response should not be empty
      * users api response should contain the first name "Michael"
      * users api response should match the schema "src/test/resources/schemas/getUsers.json"

  Scenario Outline: Verify register users POST API /api/register
    When [POST] register users API is called to register the users with username "<username>" and password "<password>"
    Then [POST] auth token should be returned in response
    Examples:
      | username           | password |
      | eve.holt@reqres.in | pistol   |