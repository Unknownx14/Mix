package Academy.Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req; //With this amends regarding static and if(req==null) loop, now in the logging.txt for every TC logs will be presented, no overwritten responses from TCs
	
	public RequestSpecification requestSpecificationMethod() throws IOException
	{
		if(req==null)
		{
		PrintStream log = new PrintStream(new FileOutputStream("LoggingJK.txt")); //This will create this .txt file where all the logging will be placed
			
		//Request Spec Builder
		 req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL")).addQueryParam("key", "qaclick123") //.setBaseUri("https://rahulshettyacademy.com")
				 .addFilter(RequestLoggingFilter.logRequestTo(log)) //This is added for logging a Request
				 .addFilter(ResponseLoggingFilter.logResponseTo(log)) //This is added for logging a Response
				 .setContentType(ContentType.JSON).build();
		// RequestSpecification req for RequestSpecBuilder()
		 
		 return req;
		}
		return req;
	}
	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\joko2909\\SeleniumTraining\\APIFramework1412\\src\\test\\java\\Academy\\Resources\\GlobalProperty.properties");
		//FileInputStream is for reading from that file, FileOutputStream is for writing in the stated file
		prop.load(fis);
		
		prop.getProperty(key);
		
		return prop.getProperty(key);
		
	}
	
	
}
