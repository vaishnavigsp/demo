Feature: Automate the SFS auth token
 
  @tag1
  Scenario: Verify the details of a request from the token generation and passing into variable.
    Given Generate the token through the SFS API
    When Extract the token from the response arrived
    When Pass the valid token into a variable of another API
    And Fetch the details(any) of the response