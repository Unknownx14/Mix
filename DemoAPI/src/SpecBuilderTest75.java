import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class SpecBuilderTest75 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		List<String> myList = new ArrayList<>();
		myList.add("shoe park");
		myList.add("shop");
		
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		
		
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		ap.setLanguage("French-IN");
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("https://www.rahulshettyacademy.com");
		ap.setTypes(myList);
		ap.setLocation(loc);
		
		
		//Request Spec builder
		RequestSpecification reqSB = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.addHeader("Content-Type", "application/json").build();
		
		//Response SPec builder
		ResponseSpecification resSB = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		
		RequestSpecification response01 = given().log().all().spec(reqSB)
				.body(ap);
		
		/*String response = given().log().all().spec(reqSB)
		.body(ap)*/
		Response responseFinal = response01.when().post("/maps/api/place/add/json")
		.then().log().all().spec(resSB).extract().response();
		
		String responseString = responseFinal.asString();
		
		System.out.println(responseString);
		
		JsonPath js01 = new JsonPath(responseString);
		String status = js01.getString("status");
		String scope = js01.getString("scope");

		
		
	}

}
