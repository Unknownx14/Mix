package StepDefinitionsJK;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import Academy.POJO.AddPlace71;
import Academy.POJO.Location;
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

public class StepDefinitions {

	RequestSpecification req;
	ResponseSpecification res;
	RequestSpecification response01;
	Response response02;
	
	
	@Given("^The AddPlace Payload is prepared$")
    public void the_addplace_payload_is_prepared() throws Throwable {
        
		//This is the serialization process for creating a json file from a java object
				AddPlace71 ap = new AddPlace71();
				ap.setAccuracy(50);
				ap.setAddress("29, side layout, cohen 09");
				ap.setName("RSA");
				ap.setPhone_number("(+91) 983 893 3937");
				ap.setWebsite("https://www.rahulshettyacademy.com");
				ap.setLanguage("French-IN");
				
				List<String> myList = new ArrayList<String>();
				myList.add("shoe park");
				myList.add("shop");
				
				ap.setTypes(myList);
				
				Location l = new Location();
				l.setLat(-38.383494);
				l.setLng(33.427362);
				
				ap.setLocation(l);
				
				
				//Request Spec Builder
				 req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
				// RequestSpecification req for RequestSpecBuilder()
				
				//Response Spec Builder
				 res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
				
				
				//RestAssured.baseURI = "https://rahulshettyacademy.com"; //Not needed when we have RequestSpecification req
				
				 response01 = given().log().all().spec(req) //.spec(req) instead of baseURI, QueryParam, header ContentType
				.body(ap); //Place the java object as a body (json, payload)
    }
	
	@When("^A user calls the \"([^\"]*)\" with the POST http request$")
    public void a_user_calls_the_something_with_the_post_http_request(String resource) throws Throwable {
        
		
		 response02 = response01.when().post("maps/api/place/add/json")
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
    	JsonPath jp = new JsonPath(response02String);
    	String status = jp.get("status");
    	Assert.assertEquals(status, "OK");
    	
    }
	
	
	
}
