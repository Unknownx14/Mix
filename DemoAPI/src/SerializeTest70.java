import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import files.payload;
import io.restassured.RestAssured;
import pojo.AddPlace;
import pojo.Location;

public class SerializeTest70 {

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
		
		
		String response = given().log().all()/*.header("Content-Type", "application/json")*/.queryParam("key", "qaclick123")
		.body(ap)
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);

		
		
	}

}
