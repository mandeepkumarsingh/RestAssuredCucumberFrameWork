package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@deleteUser")
	public void beforeScenario(){
		StepDefinitions step=new StepDefinitions();
		if(StepDefinitions.userid==null){
			step.register_Api_payload_with("Test","User","9111111009");
			step.user_calls_with_http_request("regiterApi", "POST");
			step.verify_login_with_using("9111111009","authenticate");
		}
	}

}
