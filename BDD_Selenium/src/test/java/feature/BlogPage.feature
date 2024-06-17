Feature: Blog Page of GlobalLogic

  Scenario: Verify navigation to Blog Page from nav bar
    Given User opens HomePage
    When User clicks on Blog button
    Then User verifies they are on Blog page
    And User verifies there are blogs present on site



  Scenario Outline: Verify filter functionality on Search Blog posts
    Given User opens HomePage
    When User clicks on Blog button
    And User tries to select on filter "<filter_name>" values: "<value_1>", "<value_2>", "<value_3>"
    Then User verifies selected filter values appear on page
    | <value_1> |
    | <value_2> |
    | <value_3> |
    When User clicks on Clear filters button
    Then filters will display their default values: "Wszystkie kategorie" and "Wszystkie branże"
    Examples:
    | filter_name | value_1 | value_2    | value_3    |
    | Category    | Cloud   | IoT        | Security   |
    | industry    | Media   | Automotive | Technology |