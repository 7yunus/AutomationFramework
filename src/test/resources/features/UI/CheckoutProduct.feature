@UITest
Feature: Checkout and validate the product details whether user ordered the correct product

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





