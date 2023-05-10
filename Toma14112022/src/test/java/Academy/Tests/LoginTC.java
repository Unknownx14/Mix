package Academy.Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		WebDriverManager.chromedriver().setup(); // WebDriver manager for invoking the chromedriver that is not on our local machine
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://react-redux.realworld.io/#/login");
		
		String userEmail = "jovan.kovacevic@hotmail.com";
		String userPassword = "glupavipassword";
		String userName = "JovanK";
		
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(userEmail);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(userPassword);
		driver.findElement(By.cssSelector(".btn-lg")).click();
		
		Boolean yourFeed = driver.findElement(By.xpath("//a[text()='Your Feed']")).isDisplayed();
		Assert.assertTrue(yourFeed);
		
		String userPortalName = driver.findElement(By.xpath("(//a[@class='nav-link'])[4]")).getText();
		Assert.assertEquals(userPortalName, userName);
		System.out.println(userPortalName);
		
	}

}
