package Academy.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class ThankYouPO extends AbstractComponent {

	
WebDriver driver;
	
	//Here driver doesn't have a life so we create a Constructor
	public ThankYouPO(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//PageFactory is the way of declaring a WebElement
	@FindBy(css=".hero-primary")
	WebElement thankyouMessage;
	
	public String validateThankYouMessage()
	{
		System.out.println(thankyouMessage.getText());
		return thankyouMessage.getText();
	}
	
	

	//Assert.assertEquals(validateThankYouMessage(), "THANKYOU FOR THE ORDER.");
	
}
