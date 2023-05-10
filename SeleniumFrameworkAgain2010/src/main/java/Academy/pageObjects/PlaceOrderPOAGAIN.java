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

public class PlaceOrderPOAGAIN extends AbstractComponent {

	WebDriver driver;
	
	public PlaceOrderPOAGAIN(WebDriver driver)
	{
		
		super(driver); //This way we connect a child to a parent
		this.driver = driver; //The left driver is from this PO class, the right driver is from StandAloneTestAGAIN148
		PageFactory.initElements(driver, this); //The method for PageFactory to knows about the driver
	}
	
	//List<WebElement> allCountries = driver.findElements(By.cssSelector(".ta-item"));
	
	
	//PageFactory
	@FindBy(css=".ta-item")
	List<WebElement> allCountries;
	
	@FindBy(css=".form-group input")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement placeOrderButton;
	

	
	
	//By
	By resultsBy = By.cssSelector(".ta-results");


	
	
	
	//Action Methods
	public List<WebElement> getAllCountries()
	{

		return allCountries;
	}
	
	public void enterCountry(String wantedCountryPartial)
	{
		actionsMethodJK().sendKeys(selectCountry, wantedCountryPartial).build().perform();
	}
	
	
	public void selectCountry(String wantedCountryFull )
	{
		waitForElementToAppear(resultsBy);
		WebElement singleCountry = getAllCountries().stream().filter(oneCountry -> oneCountry.findElement(By.cssSelector("span")).getText().equalsIgnoreCase(wantedCountryFull))
				.findFirst().orElse(null);
		singleCountry.click();
	}
	
	public void clickPlaceOrderButton()
	{
		placeOrderButton.click();
	}
	

			
	
	
	
	

	

	

	
	
}
