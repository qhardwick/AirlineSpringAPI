Feature: Register a new User account

  Scenario: Make a PUT request to a specified URL along with a JSON body containing
  the User firstName and lastName to create a new account
    Given url 'http://localhost:8080/users/quentin'
    And request { firstName: 'Quentin', lastName: 'Hardwick' }
    When method put
    Then status 200
    And match response contains { username: 'quentin', firstName: 'Quentin', lastName: 'Hardwick' }
