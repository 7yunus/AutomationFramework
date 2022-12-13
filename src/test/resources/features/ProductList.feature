@Test
Feature: To validate if desired product is present in product listing page

  Scenario Outline: Validate if item is present in product listing page
    Given User enters username "standard_user" and password "secret_sauce"
    When User clicks on Login button
    And User enters the products webpage
    Then product "<productName>" should be displayed in product listing page
    Examples:
      | productName             |
      | Sauce Labs Bolt T-Shirt |


