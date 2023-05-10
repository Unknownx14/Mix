package files;

public class payload {

	
	public static String AddPlace()
	{
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"RSA\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"https://www.rahulshettyacademy.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}";
	}
	
	public static String coursePrice()
	{
		return "{\r\n"
				+ "  \"dashboard\": {\r\n"
				+ "    \"purchaseAmount\": 910,\r\n"
				+ "    \"website\": \"rahulshettyacademy.com\"\r\n"
				+ "  },\r\n"
				+ "  \"courses\": [\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"Selenium Python\",\r\n"
				+ "      \"price\": 50,\r\n"
				+ "      \"copies\": 6\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"Cypress\",\r\n"
				+ "      \"price\": 40,\r\n"
				+ "      \"copies\": 4\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"RPA\",\r\n"
				+ "      \"price\": 45,\r\n"
				+ "      \"copies\": 10\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
	}
	
	
	public static String addBook(String isbn, String aisle)
	{
		String payloadAddBook = "{\r\n"
		+ "\r\n"
		+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
		+ "\"isbn\":\""+isbn+"\",\r\n"
		+ "\"aisle\":\""+aisle+"\",\r\n"
		+ "\"author\":\"John foe\"\r\n"
		+ "}\r\n"
		+ "";
		return payloadAddBook;
	}
	
	
	public static String deleteBook(String id)
	{
		String payloadDeleteBook = "{\r\n"
		+ " \r\n"
		+ "\"ID\" : \""+id+"\"\r\n"
		+ " \r\n"
		+ "} ";
		return payloadDeleteBook;
	}
	
	public static String addComment(String body)
	{
		
		String AddComment = "{\r\n"
		+ "    \"body\": \""+body+"\",\r\n"
		+ "    \"visibility\": {\r\n"
		+ "        \"type\": \"role\",\r\n"
		+ "        \"value\": \"Administrators\"\r\n"
		+ "    }\r\n"
		+ "}";
		
		return AddComment;
	}
	
	
	public static String googleMapsPost()
	{
		String body = "{\r\n"
		+ "&quot;location&quot;: {\r\n"
		+ "&quot;lat&quot;: -38.383494,\r\n"
		+ "&quot;lng&quot;: 33.427362\r\n"
		+ "},\r\n"
		+ "&quot;accuracy&quot;: 50,\r\n"
		+ "\r\n"
		+ "&quot;name&quot;: &quot;Frontline house&quot;,\r\n"
		+ "&quot;phone_number&quot;: &quot;(+91) 983 893 3937&quot;,\r\n"
		+ "&quot;address&quot;: &quot;29, side layout, cohen 09&quot;,\r\n"
		+ "&quot;types&quot;: [\r\n"
		+ "&quot;shoe park&quot;,\r\n"
		+ "&quot;shop&quot;\r\n"
		+ "],\r\n"
		+ "&quot;website&quot;: &quot;http://google.com&quot;,\r\n"
		+ "&quot;language&quot;: &quot;French-IN&quot;\r\n"
		+ "}";
		
		return body;
	}
	
	
	public static String ecomLogin()
	{
		String body = "{\r\n"
		+ "    \"userEmail\": \"unknownxjk@gmail.com\",\r\n"
		+ "    \"userPassword\": \"Zemun1Taurunum1!\"\r\n"
		+ "}";
		
		return body;
	}
	
}
