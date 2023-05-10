package Academy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	
	
	WebDriver driver;
	
	//This constructor is used for this super(driver)
	public AbstractComponent(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory is the way of declaring a WebElement
	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersButton;
	

	public void waitForElementtoAppear(By findBy)
	{
			//Explicit wait - define the object of the class
			WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
			w.until(ExpectedConditions.visibilityOfElementLocated(findBy)); //By.cssSelector(".mb-3")
			
	}
	
	public void waitForWebElementtoAppear(WebElement webElem)
	{
			//Explicit wait - define the object of the class
			WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
			w.until(ExpectedConditions.visibilityOf(webElem)); //By.cssSelector(".mb-3")
			
	}
	
	public void waitForElementToDisappear(WebElement elem)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOf(elem));
	}

	public Actions actionsMethodJK()
	{
		Actions a = new Actions(driver);
		return a;
	}
	
	
	public void clickCartButtonAbstract()
	{
		cartButton.click();
	}
	
	public void clickOrdersButtonAbstract()
	{
		ordersButton.click();
	}
	
}
