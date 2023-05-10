package Academy.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;

import Academy.AbstractComponents.AbstractComponent;

public class MyCartPO extends AbstractComponent {

WebDriver driver;
	
	//Here driver doesn't have a life so we create a Constructor
	public MyCartPO(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//PageFactory is the way of declaring a WebElement
	@FindBy(css=".cartWrap")
	List<WebElement> allInCart;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkout;
	
	
	public List<WebElement> getProductInCartList()
	{
		return allInCart;
	}
	
	public WebElement getProductInCartByName(String wantedProductName)
	{
		WebElement prodInCart = getProductInCartList().stream().filter(singleInCart->singleInCart.findElement(By.cssSelector("h3")).getText().equals(wantedProductName))
				.findFirst().orElse(null);
		return prodInCart;
	}
	
	public Boolean verifyH3text(String wantedProductName)
	{
		//WebElement prodInCart = getProductInCartByName( wantedProductName);
		Boolean matchJK = getProductInCartList().stream().anyMatch(singleInCart->singleInCart.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(wantedProductName));
		System.out.println(matchJK);
		return matchJK;
		
	}
	
	
	public void clickCheckoutButton()
	{
		checkout.click();
	}
	

	
}
