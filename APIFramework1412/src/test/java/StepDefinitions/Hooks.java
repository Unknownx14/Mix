package StepDefinitions;

import io.cucumber.java.Before;

public class Hooks {

	StepDefinitions106 sd106 = new StepDefinitions106();
	
	@Before("@DeletePlaceTag")
	public void beforeScenario() throws Throwable
	{
		
		//Here we need a code for getting a placeID
		//And Execute this code only when a placeID is null
		if(sd106.placeID==null)
		{
		
		sd106.the_addplace_payload_is_prepared_with_and_and("Jovan", "French", "Nelson Mandela avenue 101");
		sd106.a_user_calls_the_something_with_the_something_http_request("AddPlaceAPI", "post");
		sd106.the_response_has_the_something_of_something("anything", "something");
		sd106.verify_that_the_placeid_corresponds_to_the_using_the_something("Jovan", "GetPlaceAPI");
		}
		
	}
	
}
