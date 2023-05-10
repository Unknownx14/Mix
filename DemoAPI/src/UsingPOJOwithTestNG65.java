import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.GetCourse;

public class UsingPOJOwithTestNG65 {

	
	@Test
	public void myMethod()
	{
		
		
		String getCode = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qiAefFgwc6D0sZV279MiciSpCUx9sx4YLr-A3YXWQ4IXSMOEWZrLIEENHlqbonaKg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		
		String[] splitted01 = getCode.split("=");
		String[] splitted02 = splitted01[1].split("&");
		String codeJK = splitted02[0].trim();
		System.out.println(codeJK);
	
		
		// post exchangecode
		String exchangeCode = given().log().all().urlEncodingEnabled(false) //This is to prevent REST assured to encode special characters
		.queryParams("code", codeJK)
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type", "authorization_code")
		.when().post("https://www.googleapis.com/oauth2/v4/token")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js02 = new JsonPath(exchangeCode);
		String wantedAccessToken = js02.getString("access_token");
		
		
		// get actualrequest
		GetCourse response = given().log().all().queryParam("access_token", wantedAccessToken).expect().defaultParser(Parser.JSON) // to declare a format of the expected response .expect().defaultParser(Parser.JSON)
		.when().get("https://www.rahulshettyacademy.com/getCourse.php").as(GetCourse.class); //.asClass, use the parent class and change the type or "response" to the same class
		//There cannot be .log().all() in .when() if we do things like this
		
		System.out.println(response);
		
		String linkedInResponse = response.getLinkedIn();
		System.out.println(linkedInResponse);
		
		
		//Get the price of the wanted course title
		String wantedCourseTitle = "SoapUI Webservices testing";
		int allAPI = response.getCourses().getApi().size();
		for(int i=0; i<allAPI; i++)
		{
			String allAPITitles = response.getCourses().getApi().get(i).getCourseTitle();
				if(allAPITitles.equals(wantedCourseTitle))
				{
					String price = response.getCourses().getApi().get(i).getPrice();
					System.out.println(price);
				}
				
		}
		
		
		//Get all course titles for WebAutomation
		String[] courseTitlesFromJSON = {"Selenium Webdriver Java", "Cypress", "Protractor"};
		List<String> myList = new ArrayList<>();
		int allWebAutomation = response.getCourses().getWebAutomation().size();
		for(int i=0; i<allWebAutomation; i++)
		{
			String courseTitleWebAutomation = response.getCourses().getWebAutomation().get(i).getCourseTitle();
			System.out.println(courseTitleWebAutomation);
			myList.add(courseTitleWebAutomation);
		}
		Assert.assertEquals(myList.toArray(), courseTitlesFromJSON);
		Assert.assertTrue(myList.equals(Arrays.asList(courseTitlesFromJSON))); //This is only for Lists to compare, assertTrue

}
		
		
		
	}
	
	
