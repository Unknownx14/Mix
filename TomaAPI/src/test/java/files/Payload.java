package files;

public class Payload {

	
	public static String postBodyMethod(String name, String job)
	{
		
		String body = "{\r\n"
		+ "    \"name\": \""+name+"\",\r\n"
		+ "    \"job\": \""+job+"\"\r\n"
		+ "}";
		
		return body;
		
	}
	
}
