Feature: Order Placement Flow


Scenario Outline: verify order getting placed or not 
Given Product of "<PowerType>" and Product id "<ProductId>"  and package "<packageId>" and User "<Userid>" and password "<Password>" 
When  user calls "orderpayment" with http "Post" request
Then Api Call got sucess with status code 200
And "status" in response body is "200"
And Print Orderid in the file "<PowerType>" 

Examples:

 |PowerType    |ProductId |packageId|Userid|Password|
 |single_vision|133015|58a29608e4b0861d59419617|lenskart.test52@gmail.com|valyoo123|
 #|sunglasses|108541|573eb04755206af4266f2820|lenskart.test65@gmail.com|valyoo123|
 
 
@Test 
 Scenario Outline:: verify CL orderplacement flow
 Given Product id "<productId>" and quantity "<qty>" username "<userNmae>" and PowerType as "<powerType>" leftbox "<left_box>" and left sph "<left_sph>" and righttbox "<right_box>" and right sph "<right_sph>" and User "<Userid>" and password "<Password>"
 When  user calls "orderpayment" with http "Post" request
 Then Api Call got sucess with status code 200
 And "status" in response body is "200"
 And Print Orderid in the file "<PowerType>" 

Examples:
|productId|qty|userNmae |powerType   |left_box|left_sph|right_box|right_sph|Userid                   |Password |
|38726    |2  |Test     |CONTACT_LENS|1       |-0.50   |1        |-0.50    |lenskart.test52@gmail.com|valyoo123|