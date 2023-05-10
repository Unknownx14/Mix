package Academy.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class ProductCataloguePO extends AbstractComponent {

	WebDriver driver;
	
	//Here driver doesn't have a life so we create a Constructor
	public ProductCataloguePO(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//This is the usual way of doing this
/*	List<WebElement> allProductsJK = driver.findElements(By.cssSelector(".mb-3"));*/
	
	//PageFactory is the way of declaring a WebElement
	@FindBy(css=".mb-3")
	List<WebElement> allProducts;
	
	@FindBy(css=".ng-animating")
	WebElement ngAnimating;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(css=".toast-message")
	WebElement toastMessage;
	
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.cssSelector("#toast-container");
	By toastMessageBy = By.cssSelector(".toast-message");
	
	
	//Action methods
	public List<WebElement> getProductList()
	{
		waitForElementtoAppear(productsBy);
		return allProducts;
	}
	
	public WebElement getProductByName(String wantedProductName) //getProductList() action method used instead of allProducts
	{
		WebElement prod = getProductList().stream().filter(singleProduct->singleProduct.findElement(By.cssSelector("b")).getText().equals(wantedProductName))
				.findFirst().orElse(null);
		
		return prod;
	}
	
	public String addProductToCart(String wantedProductName)
	{
		WebElement prod = getProductByName( wantedProductName);
		prod.findElement(addToCart).click(); //PageFactory cannot be applied to the findElement within a WebElement, that's why this is done this way
		waitForElementtoAppear(toastContainer);
		System.out.println(toastMessage.getText());
		String something = toastMessage.getText();
		waitForElementToDisappear(ngAnimating);
		return something;
		
		
	}
	
	public void clickCartButton()
	{
		cartButton.click();
	}
	
	

	
}
