Feature: Test Air Quality Web App
  Scenario: Seek for Air Quality Web App and get cache details
    When I navigate to "http://localhost:8080/"
    And I click "Cache"
    Then I should be shown results including "REQUESTS"

  Scenario: Seek for Air Quality Web App and get valid city air quality details
    When I navigate to "http://localhost:8080/"
    And I type "Viseu" in search bar
    Then I should be shown results including "AQI"

  Scenario: Seek for Air Quality Web App and get invalid city air quality details
    When I navigate to "http://localhost:8080/"
    And I type "doesntexist" in search bar
    Then I should be shown results including "Sorry, that city is not recognized."


