import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class StaticJsonFromFile38 {

	
	@Test
	public void jsonFromFile() throws IOException
	{
		//First define baseURI
				RestAssured.baseURI="https://rahulshettyacademy.com";
				
				//This is for AddPlace API
				//Now connect the flow given->when->then
				String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String( Files.readAllBytes(Paths.get("C:\\Users\\joko2909\\Desktop\\Prntscr\\addBook.json")))) //Using this json from a file
				.when().post("maps/api/place/add/json")
				.then()/*.log().all()*/.assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)")
				.extract().response().asString();
				
				System.out.println(response);
				
				String something = "Really something";
				//This class JsonPath is used for parsing our response json
				JsonPath js = new JsonPath(response);
				String placeId = js.getString("place_id");
				System.out.println(placeId);
				
				Assert.assertEquals(placeId, something);
	}
	
	
	
	
	
}
