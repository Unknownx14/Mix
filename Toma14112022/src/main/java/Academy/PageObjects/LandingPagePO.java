package Academy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class LandingPagePO extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPagePO(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); //The method for PageFactory to knows about the driver
	}
	
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	
	//PageFactory
	@FindBy(xpath="//input[@type='email']")
	WebElement userEmail;
	
	@FindBy(xpath="//input[@type='password']")
	WebElement userPassword;
	
	@FindBy(css=".btn-lg")
	WebElement signInButton;
	
	@FindBy(xpath="(//li) [4]")
	WebElement invalidMessage;
	
	
	//By
		By invalidMessageBy = By.xpath("(//li) [4]");
	
	
	//Action methods
	public void loginToApplication(String Email, String password)
	{
		userEmail.sendKeys(Email);
		userPassword.sendKeys(password);
		signInButton.click();
	}
	
	public void goTo()
	{
		driver.get("https://react-redux.realworld.io/#/login");
	}
	
	public String getInvalidCredentialsMessage()
	{
		waitForElementToAppear(invalidMessageBy);
		String invalidCredentialsMessage = invalidMessage.getText();
		return invalidCredentialsMessage;
	}
	

}
