Feature: Search in Google

  Scenario: Seek for Selenium-Jupiter documentation
    When I navigate to "https://google.com"
    And I type "Selenium-Jupiter"
    And I press Enter
    Then I should be shown results including "Selenium"


    Scenario: Seek for BlazeDemo and select cities
      When I navigate to "https://blazedemo.com/"
      And I select "Portland" as the departure city
      And I select "Cairo" as the destination city
      And I click "Find Flights"
      Then I should be shown results including "Flights from Portland to Cairo"


      Scenario: Choose Flight
        When I navigate to "https://blazedemo.com/reserve.php"
        And I click "Choose This Flight" on row 2
        Then I should be shown results including "Your flight from TLV to SFO has been reserved."

        Scenario: Purchase Flight
          When I navigate to "https://blazedemo.com/purchase.php"
          And I type "Mariana" in the "inputName" field
          And I type "Rua 1" in the "address" field
          And I type "Aveiro" in the "city" field
          And I type "12345" in the "zipCode" field
          And I type "123456" in the "creditCardNumber" field
          And I type "12" in the "creditCardMonth" field
          And I type "2022" in the "creditCardYear" field
          And I type "Mariana" in the "nameOnCard" field
          And I click "Purchase Flight"
          Then I should be shown results including "Thank you for your purchase today!"

