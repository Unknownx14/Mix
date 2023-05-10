import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; //This import is for given()
import static org.hamcrest.Matchers.*; //This import is for equalTo()

import org.testng.Assert;

import files.ReusableMethods;
import files.payload;

public class Basics15 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//First define baseURI
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//This is for AddPlace API
		//Now connect the flow given->when->then
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(payload.AddPlace()) //Calling a static Method from this class
		.when().post("maps/api/place/add/json")
		.then()/*.log().all()*/.assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)")
		.extract().response().asString();
		
		System.out.println(response);
		
		//This class JsonPath is used for parsing our response json
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		
		
		//This is for UpdatePlace API
		String newAddress = "314 Maple road, Wellington";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")).header("Server", "Apache/2.4.41 (Ubuntu)");
		
		
		
		//This is for GetPlace API
		String GetPlace = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).body("address", equalTo(newAddress)).header("Server", "Apache/2.4.41 (Ubuntu)")
		.extract().response().asString();
		
		//This class JsonPath is used for parsing our response json
				//JsonPath js1 = new JsonPath(GetPlace); //Not needed if we have this ReusableMethods class
				JsonPath js1 = ReusableMethods.rawToJson(GetPlace);
				String address = js1.getString("address");
				System.out.println(address);
				Assert.assertEquals(address, newAddress);
		
	}

	
	
}
