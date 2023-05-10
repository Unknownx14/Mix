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

public class YourOrdersPOAGAIN extends AbstractComponent {

	WebDriver driver;
	
	public YourOrdersPOAGAIN(WebDriver driver)
	{
		
		super(driver); //This way we connect a child to a parent
		this.driver = driver; //The left driver is from this PO class, the right driver is from StandAloneTestAGAIN148
		PageFactory.initElements(driver, this); //The method for PageFactory to knows about the driver
	}
	
	//List<WebElement> allOrderNames = driver.findElements(By.xpath("//tbody//td[2]"));
	
	
	//PageFactory
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> allOrderNames;
	
	@FindBy(xpath="//tbody//th")
	List<WebElement> allOrderIds;
	
	@FindBy(xpath="//button[contains(@routerlink, 'myorders')]")
	WebElement ordersButton;
	
	
	

	
	
	//By
	By h3By = By.cssSelector("h3");

	
	
	
	//Action Methods
	public List<WebElement> getAllOrdersNames()
	{

		return allOrderNames;
	}
	
	public List<WebElement> getAllOrdersIDs()
	{
		return allOrderIds;
	}
	
	
	
	public void goToOrdersPage() throws InterruptedException
	{
		ordersButton.click();
		Thread.sleep(1000);
	}
	
	public boolean getOrderNameInMyOrders(String wantedProduct)
	{
		boolean orderNameInMyOrders = getAllOrdersNames().stream().anyMatch(myOrder -> myOrder.getText().equalsIgnoreCase(wantedProduct));
		return orderNameInMyOrders;
	}
	
	public boolean getOrderIDInMyOrders(String orderIDReadableFormat)
	{
		boolean orderIdInMyOrders = getAllOrdersIDs().stream().anyMatch(myOrder -> myOrder.getText().equals(orderIDReadableFormat));
		return orderIdInMyOrders;
		
		
	}
	
	
	
	
	

	

	

	
	
}
