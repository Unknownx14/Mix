import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.List;

import org.testng.Assert;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class JiraTest43 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI="http://localhost:8080";
		
		SessionFilter sessionF = new SessionFilter(); //This sessionF goes into the .filter() which goes before .when()
		
		//For logging into the Jira with my credentials in order to get this JSESSIONID
		String cookieBasedAuth = given().log().all().header("Content-Type", "application/json")
		.body("{ \"username\": \"jovan.kovacevic\", \"password\": \"kojikurac123\" }")
		.filter(sessionF) //Here we place this sessionF
		.when().post("/rest/auth/1/session")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		
		JsonPath js = new JsonPath(cookieBasedAuth);
		String jSessionId = js.getString("session.value");
		System.out.println(jSessionId);
		
		//For adding a comment
		
		String body = "Lorem ipsum dolor sit amet";
		
		String addingComment = given().log().all().header("Content-Type", "application/json")/*.header("Cookie", jSessionId)*/.pathParam("id", "10004")
		.body(payload.addComment(body))
		.filter(sessionF) //Here we use this sessionF
		.when().post("/rest/api/2/issue/{id}/comment") //This pathParam "id" is used here {id}
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js02 = new JsonPath(addingComment);
		String commentID = js02.getString("id");
		System.out.println(commentID);
		
		
		//For adding an attachment
		given().log().all().header("X-Atlassian-Token", "no-check").filter(sessionF).pathParam("id", "10004")
		.header("Content-Type", "multipart/form-data") //This is a header for multiPart
		.multiPart("file", new File("C:\\Users\\joko2909\\SeleniumTraining\\DemoAPI\\jiraAttachment.txt")) //This is for attached file, under given()
		.when().post("/rest/api/2/issue/{id}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
		
		
		//For getting an issue
		String issueDetails = given().log().all().filter(sessionF).pathParam("id", "10004")
				.queryParam("fields", "comment")
		.when().get("/rest/api/2/issue/{id}")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		//System.out.println(issueDetails);
		
		
		JsonPath js03 = new JsonPath(issueDetails);
		int countComments = js03.getInt("fields.comment.comments.size()");
		
		for(int i=0; i<countComments; i++)
		{
		 String singleCommentID = js03.get("fields.comment.comments["+i+"].id").toString();

		 	if(singleCommentID.equalsIgnoreCase(commentID))
		 	{
		 		System.out.println("The comment "+commentID+ " has been successfully added");
		 		String bodyOfComment = js03.get("fields.comment.comments["+i+"].body").toString();
		 		Assert.assertEquals(bodyOfComment, body);
		 	}
		 
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
