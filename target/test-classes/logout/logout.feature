Feature: Logout of a session

  Scenario: While logged in, user sends DELETE request to invalidate session
  
  Background:
  	* def signin = call read('classpath:login/login.feature')
  	
    Given url loginUrl
    When method delete
    Then status 204