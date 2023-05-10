package Academy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class LandingPagePOAGAIN extends AbstractComponent {

	WebDriver driver;
	
	public LandingPagePOAGAIN(WebDriver driver)
	{
		super(driver);
		this.driver = driver; //The left driver is from this PO class, the right driver is from StandAloneTestAGAIN148
		PageFactory.initElements(driver, this); //The method for PageFactory to knows about the driver
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement incorrectMessage;
	
	//By
	By incorrectMessageBy = By.cssSelector("[class*='flyInOut']");
	
	
	
	//Action Methods
	public void loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
	}
	

	public void goTo()
	{
		driver.get("https://www.rahulshettyacademy.com/client");
	}
	
	
	public String incorrectEmailPassword()
	{
		waitForElementToAppear(incorrectMessageBy);
		String incorrectToastMessage = incorrectMessage.getText();
		return incorrectToastMessage;
	}
	
}
