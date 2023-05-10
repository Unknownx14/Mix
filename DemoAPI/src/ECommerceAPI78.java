import static io.restassured.RestAssured.given;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.CreateOrderRequestPOJO;
import pojo.LoginRequestEcommPOJO;
import pojo.LoginResponseEcommPOJO;
import pojo.OrdersPOJO;

public class ECommerceAPI78 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		//Request Spec builder
		RequestSpecification reqSB = new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com").addHeader("Content-Type", "application/json").build();
		// setContentType(ContentType.JSON) instead of addHeader()
		
		//Response Spec builder
		//ResponseSpecification resSB = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		LoginRequestEcommPOJO lreqPOJO = new LoginRequestEcommPOJO();
		lreqPOJO.setUserEmail("unknownxjk@gmail.com");
		lreqPOJO.setUserPassword("Zemun1Taurunum1!");
		
		//RestAssured.baseURI="https://www.rahulshettyacademy.com";
		
		//01.LoginRequest
		RequestSpecification respLoginRequest = given().log().all().spec(reqSB).body(lreqPOJO); //Due to the Request Spec builder this is now divided
		
		LoginResponseEcommPOJO finalLoginRequestResponse = respLoginRequest.when().post("/api/ecom/auth/login")
		.then().log().all().assertThat().statusCode(200).extract().response()/*.asString()*/.as(LoginResponseEcommPOJO.class);
		
	/*	JsonPath js01 = new JsonPath(respLoginRequest);
		String token = js01.getString("token");
		String userId = js01.getString("userId");*/
		
		
		String token = finalLoginRequestResponse.getToken();
		String userId = finalLoginRequestResponse.getUserId();
		
		System.out.println(token);
		System.out.println(userId);
		
		
		//02.CreateProduct
		
		//Request Spec builder
				RequestSpecification CreateProductreqSB = new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com")
										.addHeader("Authorization", token).build();
		
				RequestSpecification respCreateProductRequest = given().log().all().spec(CreateProductreqSB).param("productName", "jersey")
						.param("productAddedBy", userId)
						.param("productCategory", "fashion")
						.param("productSubCategory", "shirts")
						.param("productPrice", "114")
						.param("productDescription", "Reebok originals")
						.param("productFor", "men")
						.multiPart("productImage", new File("C:\\Users\\joko2909\\Desktop\\Prntscr\\nba.png")); //This is for attached file, under given()
						
						
				String finalCreateProductResponse = respCreateProductRequest.when().post("/api/ecom/product/add-product")
				.then().log().all().assertThat().statusCode(201).extract().response().asString();
				
				JsonPath js02 = new JsonPath(finalCreateProductResponse);
				String productId = js02.getString("productId");
		
		
				
		//03.CreateOrder
				
				OrdersPOJO orders = new OrdersPOJO();
				orders.setCountry("Yugoslavia");
				orders.setProductOrderedId(productId);
				
				List<OrdersPOJO> myList = new ArrayList<>();
				myList.add(orders);
				
				CreateOrderRequestPOJO coReqPOJO = new CreateOrderRequestPOJO();
				coReqPOJO.setOrders(myList);
				
				
				RequestSpecification CreateOrderreqSB = new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com")
						.setContentType(ContentType.JSON)
						.addHeader("Authorization", token).build();
				
				RequestSpecification respCreateOrderRequest = given().urlEncodingEnabled(false).log().all().spec(CreateOrderreqSB).body(coReqPOJO);
				
				String finalCreateOrderResponse = respCreateOrderRequest.when().post("/api/ecom/order/create-order")
				.then().log().all().assertThat().statusCode(201).extract().response().asString();
				
				
				JsonPath js03 = new JsonPath(finalCreateOrderResponse);
				String ordersId = js03.getString("orders");
				System.out.println(ordersId);
				
				
				String[] splitted01 = ordersId.split("\\[");
				String[] splitted02 = splitted01[1].split("\\]");
				String ordersIdSplitted = splitted02[0].trim();
				System.out.println(ordersIdSplitted);
				
				
				
			//04. OrderDetails
				
				RequestSpecification OrderDetailsreqSB = new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com")
				.addHeader("Authorization", token).build();
				
				RequestSpecification respOrderDetailsRequest = given().relaxedHTTPSValidation().log().all().spec(OrderDetailsreqSB).queryParam("id", ordersIdSplitted);
				
				String finalOrderDetailsResponse = respOrderDetailsRequest.when().get("/api/ecom/order/get-orders-details")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
				
				JsonPath js04 = new JsonPath(finalOrderDetailsResponse);
				System.out.println(js04.getString("data.orderBy"));
				
					
				
				
			//05. DeleteProduct
				
				RequestSpecification DeleteProductreqSB = new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com")
						.addHeader("Authorization", token).build();
				
				RequestSpecification respDeleteProductRequest = given().relaxedHTTPSValidation().log().all().spec(DeleteProductreqSB).pathParam("productId", productId);
				
				String finalDeleteOrderResponse = respDeleteProductRequest.when().delete("/api/ecom/product/delete-product/{productId}")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
				
				JsonPath js05 = new JsonPath(finalDeleteOrderResponse);
				String message = js05.getString("message");
				
				String expectedMessage = "Product Deleted Successfully";
				
				Assert.assertEquals(message, expectedMessage);
	}

}
