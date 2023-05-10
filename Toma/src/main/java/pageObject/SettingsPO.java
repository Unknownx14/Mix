package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SettingsPO {

	public WebDriver driver;
	
		By settings = By.cssSelector("a[href*='settings']");
		By logout = By.cssSelector(".btn.btn-outline-danger");
		By signin = By.cssSelector("a[href*='login']");
		
		public SettingsPO(WebDriver driver) {
			// TODO Auto-generated constructor stub
			this.driver=driver;
		}

		public WebElement settingsMethod()
		{
			return driver.findElement(settings);
		}
		
		public WebElement logoutMethod()
		{
			return driver.findElement(logout);
		}
		
		public boolean signinMethod()
		{
			return driver.findElement(signin).isDisplayed();
		}
	
	
}
