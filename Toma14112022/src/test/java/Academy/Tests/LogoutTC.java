package Academy.Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LogoutTC {

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
		
	/*	
		//Publish an Article
		
		String articleTitle = "Title01";
		String articleAbout = "About01";
		String articleText = "Text01";
		String articleTag = "Tag01";
		
		driver.findElement(By.cssSelector("a[href='#editor']")).click();
		driver.findElement(By.cssSelector("input[placeholder='Article Title']")).sendKeys(articleTitle);
		driver.findElement(By.xpath("//input[contains (@placeholder, 'this article about?')]")).sendKeys(articleAbout);
		driver.findElement(By.cssSelector("textarea[placeholder='Write your article (in markdown)']")).sendKeys(articleText);
		driver.findElement(By.cssSelector("input[placeholder='Enter tags']")).sendKeys(articleTag);
		driver.findElement(By.cssSelector(".btn-lg")).click();
		
		//Explicit wait - define the object of the class
				WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
				w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
		
		Boolean deleteArticleButton = driver.findElement(By.cssSelector(".btn-outline-danger")).isDisplayed();
		Assert.assertTrue(deleteArticleButton); */
		
		
		//Logout
		String pHeadline = "A place to share your knowledge.";
		
		driver.findElement(By.cssSelector("a[href='#settings']")).click();
				
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//This will scroll the page till the element is found		
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.cssSelector(".btn-outline-danger")));
				
		driver.findElement(By.cssSelector(".btn-outline-danger")).click();
				
		Boolean loggedOut = driver.findElement(By.cssSelector("a[href='#login']")).isDisplayed();
		Assert.assertTrue(loggedOut);
		
		Boolean pTag = driver.findElement(By.xpath("//div[@class='banner']//p")).isDisplayed();
		Assert.assertTrue(pTag);
		
		String pTagtext = driver.findElement(By.xpath("//div[@class='banner']//p")).getText();
		Assert.assertEquals(pTagtext, pHeadline);

	}

}
