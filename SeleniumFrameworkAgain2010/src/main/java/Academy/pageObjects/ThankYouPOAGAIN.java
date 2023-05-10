package Academy.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Academy.AbstractComponents.AbstractComponent;

public class ThankYouPOAGAIN extends AbstractComponent {

	WebDriver driver;
	
	public ThankYouPOAGAIN(WebDriver driver)
	{
		
		super(driver); //This way we connect a child to a parent
		this.driver = driver; //The left driver is from this PO class, the right driver is from StandAloneTestAGAIN148
		PageFactory.initElements(driver, this); //The method for PageFactory to knows about the driver
	}
	
	//List<WebElement> allCountries = driver.findElements(By.cssSelector(".ta-item"));
	
	
	//PageFactory
	@FindBy(css="label[class*='inserted']")
	WebElement orderId;
	
	@FindBy(css=".hero-primary")
	WebElement thankYou;

	
	
	//By
	By resultsBy = By.cssSelector(".ta-results");


	
	//Action Methods
	public String getThankYouMessage()
	{
		String thankYouMessage = thankYou.getText();
		return thankYouMessage;
	}
	
	public String getOrderId()
	{
		String orderID = orderId.getText();
		return orderID;
	}
			
	
	
	
	

	

	

	
	
}
