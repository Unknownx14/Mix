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

public class ProductCatalogPOAGAIN extends AbstractComponent {

	WebDriver driver;
	
	public ProductCatalogPOAGAIN(WebDriver driver)
	{
		
		super(driver); //This way we connect a child to a parent
		this.driver = driver; //The left driver is from this PO class, the right driver is from StandAloneTestAGAIN148
		PageFactory.initElements(driver, this); //The method for PageFactory to knows about the driver
	}
	
	//List<WebElement> allProducts = driver.findElements(By.cssSelector(".mb-3"));
	
	
	//PageFactory
	@FindBy(css=".mb-3")
	List<WebElement> allProducts;
	
	@FindBy(css=".toast-title")
	WebElement toastTitle;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(css=".ng-animating")
	WebElement animating;
	
	
	//By
	By productsBy = By.cssSelector(".mb-3");
	By toastTitleBy = By.cssSelector(".toast-title");
	By addToCartBy = By.cssSelector(".card-body button:last-of-type");
	By toastContainerBy = By.cssSelector("#toast-container");
	
	
	//Action Methods
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return allProducts;
	}
	
	
	public String getToastTitleMethod()
	{
		waitForElementToAppear(toastTitleBy);
		String loginMessage = toastTitle.getText();
		System.out.println(loginMessage);
		return loginMessage;
	}
	
	public WebElement getProductByName(String wantedProduct)
	{
		WebElement singleProduct = getProductList().stream().filter(oneProduct -> oneProduct.findElement(By.cssSelector("b")).getText().equals(wantedProduct))
				.findFirst().orElse(null);
		return singleProduct;
				
	}
	
	public void addProductToCart(String wantedProduct)
	
	{
		WebElement singleProduct = getProductByName( wantedProduct);
		singleProduct.findElement(addToCartBy).click();
		waitForElementToAppear(toastContainerBy);
		waitForElementToDisppear(animating);
		cartButton.click();
	}
	

	

	
	
}
