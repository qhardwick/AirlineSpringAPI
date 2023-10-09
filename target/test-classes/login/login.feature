Feature: Log in to a user account

  Scenario: Send a post request with a username to log in to an account
    Given url loginUrl
    And request { username: 'quentin' }
    When method post
    Then status 200
    And match response contains { username: 'quentin', currency: '#notnull' }
    And match responseCookies contains { SESSION: '#notnull' }
    And def sessionCookie = responseCookies.SESSION
