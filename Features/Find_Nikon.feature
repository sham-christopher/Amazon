Feature: Amazon.com tests
  This project will contain all tests needed to test the Amazon site

  Scenario: To check if a particular product exists
    Given I navigate to "https://www.amazon.com" using "Chrome" browser
    When I search for "Nikon" in the search box
    Then I sort by "Price: High to Low"
    Then I click on the no "2" item in the list
    And I check if the product displayed is "Nikon D3X"
 