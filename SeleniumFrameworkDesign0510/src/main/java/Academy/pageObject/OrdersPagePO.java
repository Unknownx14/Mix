package Academy.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class OrdersPagePO extends AbstractComponent {

	
WebDriver driver;
	
	//Here driver doesn't have a life so we create a Constructor
	public OrdersPagePO(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory is the way of declaring a WebElement
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> allNamesInTable;
	
	
	
	//Action methods
	public List<WebElement> getAllNamesInTable()
	{
		return allNamesInTable;
	}
	
	
	public Boolean matchYourOrder(String wantedProductName)
	{
		Boolean matchJK = getAllNamesInTable().stream().anyMatch(singleName->singleName.getText().equalsIgnoreCase(wantedProductName));
		return matchJK;
	}
	
	
	public void verifyOrderDisplay()
	{
		clickOrdersButtonAbstract();
		
	}
	
	
}
