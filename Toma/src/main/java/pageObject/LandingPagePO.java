package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPagePO {

	public WebDriver driver;
	
	 By username = By.cssSelector("a[href*='@']");
	 By newPost = By.cssSelector("a[href*='editor']");
	 
	 
	 public LandingPagePO(WebDriver driver) {
		// TODO Auto-generated constructor stub
		 this.driver = driver;
	}


	public String usernameMethod()
	 {
		 return driver.findElement(username).getText(); 
	 }
	
	public WebElement newPostMethod()
	 {
		 return driver.findElement(newPost); 
	 }
	
}
