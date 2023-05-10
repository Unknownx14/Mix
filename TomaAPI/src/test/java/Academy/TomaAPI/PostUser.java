package Academy.TomaAPI;

import static io.restassured.RestAssured.given;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.GetUserPojo;
import pojo.dataForGetUser;
import pojo.supportForGetUser;

import static org.junit.Assert.*;

public class PostUser extends Payload {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//First define baseURI
				RestAssured.baseURI="https://reqres.in";
		
				
		//POST user		
				String desiredName = "John";
				
				String response = given().log().all().header("Content-Type", "application/json")
				.body(Payload.postBodyMethod(desiredName, "basketballer"))
				.when().post("/api/user")
				.then().log().all().assertThat().statusCode(201).extract().response().asString();
				
				JsonPath js01 = new JsonPath(response);
				String nameFromResponse = js01.getString("name");
				
				assertEquals(nameFromResponse, desiredName);
				
				
		//GET user
				
				dataForGetUser dfgu = new dataForGetUser();
				dfgu.setId(2);
				dfgu.setEmail("janet.weaver@reqres.in");
				dfgu.setFirst_name("Janet");
				dfgu.setLast_name("Weaver");
				dfgu.setAvatar("https://reqres.in/img/faces/2-image.jpg");
				
				supportForGetUser sfgu = new supportForGetUser();
				sfgu.setUrl("https://reqres.in/#support-heading");
				sfgu.setText("To keep ReqRes free, contributions towards server costs are appreciated!");
				
				GetUserPojo gup = new GetUserPojo();
				gup.setData(dfgu);
				gup.setSupport(sfgu);
				
				
				int idFromResponse = 2;
				String response02 = given().log().all().pathParam("id", idFromResponse)
				.when().get("/api/users/")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
				

	}

}
