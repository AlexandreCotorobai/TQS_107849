Feature: Buy Trip

    Scenario: Buy a trip from Philadelphia to Rome
        Given I am on the BlazeDemo homepage
        When I select
            | Field    | Value        |
            | fromPort | Philadelphia |
            | toPort   | Rome         |
        And I click on the 'Find Flights' button
        Then I should be in "BlazeDemo - reserve"
        And I select the 43 flight
        Then I should be in "BlazeDemo Purchase"
        And I select
            | Field            | Value            |
            | inputName        | Alex             |
            | address          | Aveiro Street    |
            | city             | Aveiro           |
            | state            | Portugal         |
            | zipCode          | 3810             |
            | cardType         | American Express |
            | creditCardNumber | 9999             |
            | creditCardMonth  | 12               |
            | creditCardYear   | 2025             |
            | nameOnCard       | John             |
        And I click on the 'Purchase Flight' button
        Then I should be in "BlazeDemo Confirmation"
