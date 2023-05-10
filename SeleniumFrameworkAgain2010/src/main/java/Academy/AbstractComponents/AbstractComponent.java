package Academy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	
WebDriver driver;
	
	public AbstractComponent(WebDriver driver) 
	{
		this.driver=driver;
		
	}

	public void waitForElementToAppear(By findBy)
	{
		//Explicit wait - define the object of the class
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy)); //By.cssSelector(".toast-title")
		
	}
	
	
	public void waitForElementToDisppear(WebElement webelement)
	{
		//Explicit wait - define the object of the class
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOf(webelement)); //This way is more quick
		
	}
	
	public Actions actionsMethodJK()
	{
		Actions a = new Actions(driver);
		return a;
	}
	
}
