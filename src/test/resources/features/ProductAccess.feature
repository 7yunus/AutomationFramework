@Test
Feature: To test product access via Search and applying Filter

  Scenario: Access a Product via category after applying multiple filters
    Given user navigates to sub category page "Cell phones & accessories" from "Electronics" category
    When user clicks the final sub category "Cell Phones & Smartphones" in the left hand side of navigation section
    And user clicks See All option that appears under "Shop by Network"
    And applies filters appearing in the pop-up
      | Screen Size   |
      | Price         |
      | Item Location |
    Then the filter tags should be applied

  Scenario Outline: Access a Product via Search
    Given user searches '<searchTerm>' in search bar
    When user changes the Search category to '<category>'
    Then user waits for page to load completely
    And first result name matches with the searched term '<searchTerm>'
    Examples:
      | searchTerm | category                       |
      | Macbook    | Computers/Tablets & Networking |


