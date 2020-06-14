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
 