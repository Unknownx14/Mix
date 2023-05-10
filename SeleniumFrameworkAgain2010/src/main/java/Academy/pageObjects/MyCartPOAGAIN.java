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

public class MyCartPOAGAIN extends AbstractComponent {

	WebDriver driver;
	
	public MyCartPOAGAIN(WebDriver driver)
	{
		
		super(driver); //This way we connect a child to a parent
		this.driver = driver; //The left driver is from this PO class, the right driver is from StandAloneTestAGAIN148
		PageFactory.initElements(driver, this); //The method for PageFactory to knows about the driver
	}
	
	//List<WebElement> allProductsInCart = driver.findElements(By.cssSelector(".cartWrap"));

	
	//PageFactory
	@FindBy(css=".cartWrap")
	List<WebElement> allProductsInCart;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkoutButton;
	
	
	

	
	
	//By
	By h3By = By.cssSelector("h3");
	By itemNumberBy = By.cssSelector(".itemNumber");
	
	
	
	//Action Methods
	public List<WebElement> getAllProductsInCart()
	{

		return allProductsInCart;
	}
	
	
	
	public WebElement getSingleProcutInCart(String wantedProduct)
	{
		WebElement singleProductInCart = getAllProductsInCart().stream().filter(oneProductInCart -> oneProductInCart.findElement(h3By).getText().equals(wantedProduct))
				.findFirst().orElse(null);
		return singleProductInCart;
	}
	
	public Boolean isPresentInCart(String wantedProduct)
	{
		Boolean presentInCart = getAllProductsInCart().stream().anyMatch(oneProductInCart -> oneProductInCart.findElement(h3By).getText().equalsIgnoreCase(wantedProduct));
		return presentInCart;
	}
	
	public String getItemNumber(String wantedProduct)
	{
		WebElement singleProductInCart = getSingleProcutInCart( wantedProduct);
		String itemNumber = singleProductInCart.findElement(itemNumberBy).getText();
		return itemNumber;
	}
	
	public void clickCheckoutButton()
	{
		checkoutButton.click();
	}

	

	

	
	
}
