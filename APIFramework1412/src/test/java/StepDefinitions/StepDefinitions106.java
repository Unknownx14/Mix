package StepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import Academy.POJO.AddPlace71;
import Academy.POJO.Location;
import Academy.Resources.APIResourcesENUM112;
import Academy.Resources.TestDataBuild106;
import Academy.Resources.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinitions106 extends Utils {

	RequestSpecification req;
	ResponseSpecification res;
	RequestSpecification response01;
	Response response02;
	TestDataBuild106 tdb = new TestDataBuild106();
	static String placeID;
	RequestSpecification response03;
	Response response04;
	JsonPath jp;
	RequestSpecification response05;
	Response response06;
	
	
/*	@Given("^The AddPlace Payload is prepared$")
    public void the_addplace_payload_is_prepared() throws Throwable {
				
				
		AddPlace71 ap = tdb.addPlacePayload();
		
		RequestSpecification req = requestSpecificationMethod();
					
				
				//RestAssured.baseURI = "https://rahulshettyacademy.com"; //Not needed when we have RequestSpecification req
				
				 response01 = given().log().all().spec(req) //.spec(req) instead of baseURI, QueryParam, header ContentType
				.body(ap); //Place the java object as a body (json, payload)
    }*/
	
	@When("^A user calls the \"([^\"]*)\" with the \"([^\"]*)\" http request$")
    public void a_user_calls_the_something_with_the_something_http_request(String resource, String httpMethod) throws Throwable {
        
		//Response Spec Builder
		 res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		 //ENUM
		 APIResourcesENUM112 resourceAPI = APIResourcesENUM112.valueOf(resource); //In the Feature file the "AddPlaceAPI" is stated, that comes here as the String resource  
		 										//in the ENUM it finds the "AddPlaceAPI" method and uses that API resource
		 										 
		 
		 
		 response02 = response01.when().post(resourceAPI.getResource()) // Call this method resourceAPI.getResource(), used to be this post("maps/api/place/add/json")
				.then().log().all().spec(res).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response();
				
				System.out.println(response02);
    }

    @Then("^Verify that the new places is added successfully$")
    public void verify_that_the_new_places_is_added_successfully() throws Throwable {
        
    	response02.getStatusCode();
    	Assert.assertEquals(response02.getStatusCode(), 200);
    	
    }

    @And("^The response has the \"([^\"]*)\" of \"([^\"]*)\"$")
    public void the_response_has_the_something_of_something(String key, String value) throws Throwable {
        
    	String response02String = response02.asString();
    	 jp = new JsonPath(response02String);
    	String status = jp.get("status");
    	Assert.assertEquals(status, "OK");
    	
    	
    }
    
    
    @Given("^The AddPlace Payload is prepared with (.+) and (.+) and (.+)$")
    public void the_addplace_payload_is_prepared_with_and_and(String name, String language, String address) throws Throwable {
        
    	AddPlace71 ap = tdb.addPlacePayload(name, language, address);
		
		 req = requestSpecificationMethod();
							
		response01 = given().log().all().spec(req) //.spec(req) instead of baseURI, QueryParam, header ContentType
		.body(ap); //Place the java object as a body (json, payload)
    	
    }
    
    
    @And("^Verify that the place_id corresponds to the (.+) using the \"([^\"]*)\"$")
    public void verify_that_the_placeid_corresponds_to_the_using_the_something(String name, String resource) throws Throwable {
        
    	
    	placeID = jp.get("place_id");
    	System.out.println(placeID);
    	
    	//This is the RequestSpecification
    	response03 = given().log().all().spec(req).queryParam("place_id", placeID);
    	
    	//Response Spec Builder
		 res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		 //ENUM
		 APIResourcesENUM112 resourceAPI = APIResourcesENUM112.valueOf(resource); //In the Feature file the "AddPlaceAPI" is stated, that comes here as the String resource  
		 										//in the ENUM it finds the "AddPlaceAPI" method and uses that API resource
		 										 
		 
		 
		 response04 = response03.when().get(resourceAPI.getResource()) // Call this method resourceAPI.getResource(), used to be this post("maps/api/place/add/json")
				.then().log().all().spec(res).extract().response();
				
				System.out.println(response04);
				
				String response04String =  response04.asString();
				JsonPath jp04 = new JsonPath(response04String);
				String nameFromJson = jp04.get("name");
				System.out.println(nameFromJson);
				System.out.println(name);
				Assert.assertEquals(nameFromJson, name);
    	
    }

	
    
    
    //DeletePlaceAPI
    @Given("^The AddPlace functionality has been successfully completed$")
    public void the_addplace_functionality_has_been_successfully_completed() throws Throwable {
        //No code here
    }
    
    
    @When("^A user calls the \"([^\"]*)\" with the DELETE http request$")
    public void a_user_calls_the_something_with_the_delete_http_request(String resource) throws Throwable {
    	
    	req = requestSpecificationMethod();
    	
    	//This is the RequestSpecification
    	response05 = given().log().all().spec(req).body(tdb.deletePlacePayload(placeID));
    	
    	//Response Spec Builder
		 res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		 //ENUM
		 APIResourcesENUM112 resourceAPI = APIResourcesENUM112.valueOf(resource); //In the Feature file the "AddPlaceAPI" is stated, that comes here as the String resource  
		 										//in the ENUM it finds the "AddPlaceAPI" method and uses that API resource
		 										 
		 
		 
		 response06 = response05.when().post(resourceAPI.getResource()) // Call this method resourceAPI.getResource(), used to be this post("maps/api/place/add/json")
				.then().log().all().spec(res).extract().response();
				
				System.out.println(response06);
    	
    }
    
    
    @Then("^Verify that the place is deleted successfully with the statusCode \"([^\"]*)\" OK$")
    public void verify_that_the_place_is_deleted_successfully_with_the_statuscode_something_ok(String statusCode) throws Throwable {
        
    	response06.getStatusCode();
    	Assert.assertEquals(response06.getStatusCode(), 200);
    	
    }
    
    
    @And("^The response in the body has the \"([^\"]*)\" of \"([^\"]*)\"$")
    public void the_response_in_the_body_has_the_something_of_something(String status, String value) throws Throwable {
        
    	String response06String =  response06.asString();
		JsonPath jp06 = new JsonPath(response06String);
		String statusFromJson = jp06.get(status);
		Assert.assertEquals(statusFromJson, value);
    	
    }
	
	
}
