import static io.restassured.RestAssured.given;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.restassured.path.json.JsonPath;

public class OAuthTest56 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// get getCode
		
	/*	System.setProperty("webdriver.chrome.driver", "C:\\JK\\chromedriver_win32\\chromedriver.exe");
		//webdriver.chrome.driver is a key value, a property
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("unknownxjk");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("input[type='password']")).sendKeys("kojikurac123");
		driver.findElement(By.xpath("input[type='password']")).sendKeys(Keys.ENTER);
		
		String currentURL = driver.getCurrentUrl();*/
		
		
		
		String getCode = 
		"https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qhvUVdHMgyreFaoGKXEFPd0i9N1pJQ1V3GIkjcApP9hy6r4M7W39pGMTgscA64K_w&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		
		String[] splitted01 = getCode.split("=");
		String[] splitted02 = splitted01[1].split("&");
		String codeJK = splitted02[0].trim();
		System.out.println(codeJK);
	
		
		// post exchangecode
		String exchangeCode = given().log().all().urlEncodingEnabled(false) //This is to prevent REST assured to encode special characters
		.queryParams("code", codeJK)
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type", "authorization_code")
		.when().post("https://www.googleapis.com/oauth2/v4/token")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js02 = new JsonPath(exchangeCode);
		String wantedAccessToken = js02.getString("access_token");
		
		
		// get actualrequest
		String response = given().log().all().queryParam("access_token", wantedAccessToken)
		.when().get("https://www.rahulshettyacademy.com/getCourse.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);
		
	}

}
