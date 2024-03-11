@active
  Feature: Resources testing

    @Smoke
    Scenario: Get the List of Resources
      Given there are registered resources in the system
      When I send a GET request to view all the resources
      Then the response should have a status code of 200
      And validates the response with the resource list JSON schema


    @Smoke
    Scenario: Update the Last Resource
      Given there are registered resources in the system
      And I retrieve the details of the latest resource
      When I send a PUT request to update the latest resource
      """
      {
        "name": "NewName",
        "trademark": "NewTradeMark",
        "stock": 1000,
        "price":99.99,
        "description": "description",
        "tags": "NewTag"
        "active": true
      }
      """
      Then the response should have a status code of 200
      And the response should have the following details:
        | name      | trademark      | stock| price | description   | tags     | active |
        | <newName> | <NewTrademark> | 1000 | 99.99 | <description> | <NewTag> | <true>|