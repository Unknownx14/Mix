package Academy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	
WebDriver driver;
	
	public AbstractComponent(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this); //The method for PageFactory to knows about the driver
	}
	
	
	
	//PageFactory
		@FindBy(css="a[href='#settings']")
		WebElement settingsButton;
	
	
	public void waitForElementToAppear(By findBy)
	{
		//Explicit wait - define the object of the class
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy)); //By.cssSelector("button[type='submit']")
		
	}
	
	
	public void scrollPageUntillElementFound(By findby)
	{
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//This will scroll the page till the element is found		
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(findby)); // By.cssSelector(".btn-outline-danger")
		
	}
	
	public void goToSettingsPageAbstract()
	{
		settingsButton.click();
	}
	
	
	
}
