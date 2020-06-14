package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.ApiResources;
import resources.TestDataBinder;
import resources.Utils;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class StepDefinitions extends Utils {
	RequestSpecification requestspecification;
	Response response;
	public static String userid;
	TestDataBinder data=new TestDataBinder();
	@Given("register Api payload with {string} {string} {string}")
	public void register_Api_payload_with(String firstname, String lastName, String mobile){
		//.header("X-Session-Token",session())
		requestspecification= RestAssured.given().spec(requestSpecification().header("X-Session-Token",session()))
				.body(data.registerUserPayloads(firstname,lastName,mobile));

	}

	@When("user calls {string} with http {string} request")
	public void user_calls_with_http_request(String resource, String httpsMethod) {
		ApiResources apiresource=ApiResources.valueOf(resource.toLowerCase());
		if(httpsMethod.equalsIgnoreCase("post")){
			String s=apiresource.getResource();
			response=requestspecification.when().post(apiresource.getResource());
			getlogger().info("Request Data "+requestspecification.log().all().toString());
		}
	}

	@Then("Api Call got sucess with status code {int}")
	public void api_Call_got_sucess_with_status_code(Integer int1) {
		response.then().assertThat().statusCode(int1).extract().response();
		getlogger().info("Response is"+response.asString());
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedvalue) {

		String actual=getResponseValue(response, key);
		getlogger().info("Excpected is : "+expectedvalue+" Actual is :"+actual);
		assertEquals(expectedvalue.toString(),actual);
	} 
	@Then("verify login with {string} using {string}")
	public void verify_login_with_using(String mobile, String resource) {
		requestspecification= RestAssured.given().spec(requestSpecification().header("X-Session-Token",session()))
				.body(data.authenticateUserPayload(mobile+"@gmail.com", "valyoo123"));
		user_calls_with_http_request(resource,"POST");
		String username=getResponseValue(response, "result.username");
		assertEquals(mobile+"@gmail.com", username);
		userid=Utils.getResponseValue(response, "result.username");
	}

	@Given("removeAccount Api payload with {string}")
	public void removeaccount_Api_payload_with(String Email) {
		requestspecification=RestAssured.given().spec(requestSpecification().header("Content-Type", "application/x-www-form-urlencoded").header("X-Session-Token",session()))
				.formParams("email",base64Code(Email),"emailtoken", "manualremove");
	}
	@Given("Product of {string} and Product id {string}  and package {string} and User {string} and password {string}")
	public void product_of_and_Product_id_and_package_and_User_and_password(String PowerType, String Productid, String PackageId, String Email, String Pass) {
		String sessionId=Utils.userSession(Email, Pass);
		int cartstatus=Utils.addPowerProductToCart(PowerType, Productid, PackageId, sessionId);
		int addressStatus=addAddress(Email, "878787", "9999999999", "Haryana", sessionId);
		if (sessionId!=null && cartstatus==200 &&addressStatus==200){
			requestspecification=RestAssured.given().spec(Utils.requestSpecification()).header("x-session-token",sessionId).body(TestDataBinder.orderpaymentPayloads(getGlobalValue("ClientType")));
			System.out.println(requestspecification.log());
		}
	}
	@Then("Print Orderid in the file {string}")
	public void print_Orderid_in_the_file(String PowerType) {
		String orderid= Utils.getResponseValue(response, "result.order.id");
		Utils.writeInFile("Oeder id of"+PowerType+" "+orderid);
		getlogger().info(" order writen  in file "+orderid);   
	}

}
