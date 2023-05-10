package Academy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class SettingsPagePO extends AbstractComponent {

	
WebDriver driver;
	
	public SettingsPagePO(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); //The method for PageFactory to knows about the driver
	}
	
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	
	//PageFactory
	@FindBy(css="a[href='#settings']")
	WebElement settingsButton;
	
	@FindBy(css=".btn-outline-danger")
	WebElement logoutButton;
	
	
	//By
	By logoutButtonBy = By.cssSelector(".btn-outline-danger");

	
	
	
	//Action methods
/*	public void goToSettingsPage()
	{
		settingsButton.click();
	}*/
	
	public void logoutFromApplication()
	{
		goToSettingsPageAbstract();
		scrollPageUntillElementFound(logoutButtonBy);
		logoutButton.click();
	}
	
	
	
}
