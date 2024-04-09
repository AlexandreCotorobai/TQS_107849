Feature: Ticket purchase

    Scenario: User search for a ticket
        Given I am on homepage
        When I filter the origin to 'Aveiro'
        And I filter the destination to 'Porto'
        And I select the day of departure for '01-01-2024'
        And I select the currency 'AED'
        And I click on Submit
        Then I should see the ticket list
        When I click on the first ticket
        Then I should be in 'Buy Trip'
        When I fill the form with the following data:
            | Name | Email         | Phone     | Card         | Expire Date | CVV |
            | John | john@test.com | 917000111 | 444411110000 | 11/27       | 666 |
        When I click on confirm purchase
        Then I should be in 'Details'
        When I get my reservation code
        And I click on 'Back to homepage'
        Then I should be in 'Search Trip'
        When I type my reservation code
        Then I should be in 'Details'