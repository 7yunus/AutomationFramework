@UITest @Regression
Feature: Validate the price with product listing with product individual page

  Scenario: Verify the product price of each product in product listing and product details
    Given User enters username "standard_user" and password "secret_sauce"
    When User clicks on Login button
    And User enters the products webpage
    Then validates if price of product listing and product individual page are in sync