@UITest @UIRegression @BeforeUI @AfterUI
Feature: Sauce Demo UI validation

  Scenario: Verify the checkout process and details of ordered product
    Given User enters username "standard_user" and password "secret_sauce"
    When User clicks on Login button
    And User enters the products webpage
    And User checks the product details and adds product to cart
    And opens the shopping cart
    And proceeds to checkout page
    And fills checkout form with first name "Mohammed", last name "Yunus" and zip code "123456" and submits
    And verifies the product details and clicks the finish button
    Then success order message "Thank you for your order" should be displayed to user

  Scenario Outline: Validate if item is present in product listing page
    Given User enters username "standard_user" and password "secret_sauce"
    When User clicks on Login button
    And User enters the products webpage
    Then product "<productName>" should be displayed in product listing page
    Examples:
      | productName             |
      | Sauce Labs Bolt T-Shirt |

  Scenario: Verify the product price of each product in product listing and product details
    Given User enters username "standard_user" and password "secret_sauce"
    When User clicks on Login button
    And User enters the products webpage
    Then validates if price of product listing and product individual page are in sync

    @q1
  Scenario Outline: Validating error message for invalid login credentials
    Given User enters username "<username>" and password "<password>"
    When User clicks on Login button
    Then error message "<message>" should be displayed to the user
    Examples:
      | username   | password     | message                                                     |
      | wrong_user | wrong_secret | Username and password do not match any user in this service1 |



