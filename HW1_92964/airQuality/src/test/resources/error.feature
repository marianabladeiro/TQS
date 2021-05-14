Feature: Test error trigger
  Scenario: Search for invalid URL
    When I navigate to "http://localhost:8080/doesntexist"
    Then I should be shown results including "Ups... Something went wrong"
