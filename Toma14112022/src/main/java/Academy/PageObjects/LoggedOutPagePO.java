package Academy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class LoggedOutPagePO extends AbstractComponent {

	
WebDriver driver;
	
	public LoggedOutPagePO(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); //The method for PageFactory to knows about the driver
	}
	
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	
	//PageFactory
	@FindBy(css="a[href='#login']")
	WebElement signInButton;
	
	@FindBy(xpath="//div[@class='banner']//p")
	WebElement pText;
	
	
	//Action methods
	public Boolean verifyLoggedOut()
	{
		Boolean loggedOut = signInButton.isDisplayed();
		return loggedOut;
	}
	
	public Boolean verifyPTag()
	{
		Boolean pTag = pText.isDisplayed();
		return pTag;
	}
	
	public String getPText()
	{
		String pTagtext = pText.getText();
		return pTagtext;
	}
	
	
}
