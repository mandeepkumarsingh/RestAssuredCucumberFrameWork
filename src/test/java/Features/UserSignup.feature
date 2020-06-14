
Feature: User SignUp Flow

@registerUser
  Scenario Outline: verify User got registered Sucessfully using regiter Api
    Given register Api payload with "<firstName>" "<LastName>" "<Mobile>"
    When  user calls "regiterApi" with http "Post" request
    Then Api Call got sucess with status code 200
    And "status" in response body is "200"
    And verify login with "<Mobile>" using "authenticate"
    
    
Examples:

   |firstName|LastName|Mobile|
    |Test    |User    | 9111111009|
   # |Test    |User    | 9111111001|
   
   @deleteUser
Scenario:  verify User got deleted sucessfully using Delete Api
 Given removeAccount Api payload with "9111111009@gmail.com"
 When  user calls "removeAccount" with http "Post" request
 Then Api Call got sucess with status code 200
 And "result.code" in response body is "200"
 
 
    