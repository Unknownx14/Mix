package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePagePO {

	public WebDriver driver;
	
	 By email = By.xpath("//input[@type='email']");
	 By password = By.xpath("//input[@type='password']");
	 By submit = By.xpath("//button[@type='submit']");
	 By signinOk = By.cssSelector("a[href*='login']");
	
	 
	 
	 public HomePagePO(WebDriver driver) {
		// TODO Auto-generated constructor stub
		 this.driver = driver;
	}

	public WebElement emailMethod()
	 {
		 return driver.findElement(email);
	 }
	 
	 public WebElement password()
	 {
		 return driver.findElement(password);
	 }
	 
	 public WebElement submitMethod()
	 {
		 return driver.findElement(submit);
	 }
	 public WebElement signinOkMethod()
	 {
		 return driver.findElement(signinOk);
	 }
}
