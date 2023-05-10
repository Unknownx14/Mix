package Academy.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class LandingPagePO extends AbstractComponent {

	WebDriver driver;
	
	//Here driver doesn't have a life so we create a Constructor
	public LandingPagePO(WebDriver driver)
	{
		super(driver); //This is for sending this driver from a child class to a parent class (child extends parent)
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//This is the usual way of doing this
/*	WebElement userEmailJK = driver.findElement(By.id("userEmail"));
	WebElement userPasswordJK = driver.findElement(By.id("userPassword"));
	WebElement loginButtonJK = driver.findElement(By.id("login"));*/
	
	//PageFactory is the way of declaring a WebElement
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	By errorMessageBy = By.cssSelector("[class*='flyInOut']");
	
	//Action methods
	public void LoginApplicationAction(String userEmailString, String userPasswordString)
	{
		userEmail.sendKeys(userEmailString);
		userPassword.sendKeys(userPasswordString);
		loginButton.click();
	}
	
	public void goTo()
	{
		driver.get("https://www.rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementtoAppear(errorMessage);
		String errorMessageText = errorMessage.getText();
		return errorMessageText;
	}
	
	
}
