@active
Feature: Client testing

    @Smoke
    Scenario: View the list of clients
        Given there are registered clients in the system
        When I send a GET request to view all the clients
        Then the response should have a status code of 200
        And validates the response with client list JSON schema

