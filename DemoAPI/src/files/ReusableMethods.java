package files;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {

	
	public static JsonPath rawToJson(String responseFromTC)
	{
		//This class JsonPath is used for parsing our response json
		JsonPath js1 = new JsonPath(responseFromTC);
		return js1;
	}
}
