@UITest
Feature: To validate the error message in login page

  Scenario Outline: Validating error message for invalid login credentials
    Given User enters username "<username>" and password "<password>"
    When User clicks on Login button
    Then error message "<message>" should be displayed to the user
    Examples:
      | username   | password     | message                                                     |
      | wrong_user | wrong_secret | Username and password do not match any user in this service |
