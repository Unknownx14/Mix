package Academy.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class PlaceOrderPO extends AbstractComponent {

WebDriver driver;
	
	//Here driver doesn't have a life so we create a Constructor
	public PlaceOrderPO(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//PageFactory is the way of declaring a WebElement
		@FindBy(xpath="//*[@placeholder='Select Country']")
		WebElement selectCountry;
		
		@FindBy(css=".ta-item")
		List<WebElement> listedCountries;
		
		@FindBy(css=".action__submit")
		WebElement placeOrder;
		
		
		By fewCountryOptions = By.cssSelector(".ta-results");
	
	
	
	public void chooseCountry(String wantedCountryTypeIn)
	{
		actionsMethodJK().sendKeys(selectCountry, wantedCountryTypeIn).build().perform();
		
	}
	
	public List<WebElement> getCountryList()
	{
		waitForElementtoAppear(fewCountryOptions);
		return listedCountries;
		
	}
	
	public void selectYourCountry(String countryFromDropdown)
	{
		WebElement myCountry = getCountryList().stream().filter(wantedCountry->wantedCountry.findElement(By.cssSelector("span")).getText().equalsIgnoreCase(countryFromDropdown))
				.findFirst().orElse(null);
		myCountry.click();
	}
	
	public void clickPlaceOrderButton()
	{
		placeOrder.click();
	}

	
	

	
}
