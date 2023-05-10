import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson33 {

	
	@Test(dataProvider="getData")
	public void addBook(String isbnDP, String aisleDP)
	{
		RestAssured.baseURI="http://216.10.245.166";
		
		String response = given().log().all().header("Content-Type", "application/json")
		.body(payload.addBook(isbnDP, aisleDP))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String id = js.getString("ID");
		System.out.println(id);
		
		JsonPath js1 = new JsonPath(payload.addBook(isbnDP, aisleDP));
		String isbn = js1.getString("isbn");
		String aisle = js1.getString("aisle");
		String concatinatedId = isbn+aisle;
		
		Assert.assertEquals(id, concatinatedId);
		
		String response02 = given().log().all().header("Content-Type", "application/json")
				.body(payload.deleteBook(id))
				.when().post("Library/DeleteBook.php")
				.then().log().all().assertThat().statusCode(200).body("msg", equalTo("book is successfully deleted")).extract().response().asString();
		
		JsonPath js2 = new JsonPath(response02);
		String msgResponse = js2.getString("msg");
		System.out.println(msgResponse);
		
	}
	

	
	@DataProvider
	public Object[][] getData()
	{
		Object [][] dataProv = new Object[3][2];
		dataProv[0][0] = "Serbia";
		dataProv[0][1] = "33333";
		
		dataProv[1][0] = "Russia";
		dataProv[1][1] = "55555";
		
		dataProv[2][0] = "Greece";
		dataProv[2][1] = "11111";
		
		return dataProv;
	}
	
}
