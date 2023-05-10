package Academy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class HomePagePO extends AbstractComponent {

	
WebDriver driver;
	
	public HomePagePO(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); //The method for PageFactory to knows about the driver
	}
	
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	
	//PageFactory
	@FindBy(xpath="//a[text()='Your Feed']")
	WebElement yourFeedTab;
	
	
	@FindBy(xpath="(//a[@class='nav-link'])[4]")
	WebElement userPortalName;
	
	
	@FindBy(linkText="Global Feed")
	WebElement globalFeedTab;
	
	
	@FindBy(css=".article-preview")
	List<WebElement> allArticles;
	
	
	//By
		By readMoreBy = By.xpath("(//span) [2]");
	
	
	
	//Action methods
	public Boolean feedDisplayed()
	{
		Boolean yourFeed = yourFeedTab.isDisplayed();
		return yourFeed;
	}
	
	public String getUserPortalName()
	{
		String userPortalNick = userPortalName.getText();
		return userPortalNick;
	}
	
	public void goToGlobalFeedTab()
	{
		globalFeedTab.click();
	}
	
	public List<WebElement> getAllArticles()
	{
		return allArticles;
	}
	
	public void getOneArticle(String userName)
	{
		WebElement wantedAuthor = getAllArticles().stream().filter(oneArticle->oneArticle.findElement(By.cssSelector(".author")).getText().equalsIgnoreCase(userName))
				.findFirst().orElse(null);
		wantedAuthor.findElement(readMoreBy).click();
	}
	
	
	
}
