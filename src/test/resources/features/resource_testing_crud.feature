@active
  Feature: Resources testing

    @Smoke
    Scenario: Get the List of Resources
      Given there are registered resources in the system
      When I send a GET request to view all the resources
      Then the response of resources should have a status code of 200
      And validates the response with the resource list JSON schema