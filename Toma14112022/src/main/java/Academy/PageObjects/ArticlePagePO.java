package Academy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class ArticlePagePO extends AbstractComponent {

	
WebDriver driver;
	
	public ArticlePagePO(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); //The method for PageFactory to knows about the driver
	}
	
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//Boolean deleteArticleButton = driver.findElement(By.cssSelector(".btn-outline-danger")).isDisplayed();
	
	
	//PageFactory
	@FindBy(css=".btn-outline-danger")
	WebElement deleteArticleButton;
	
	@FindBy(tagName="h1")
	WebElement articleTitleH1;
	
	
	
	//By
	By postCommentButtonBy = By.cssSelector("button[type='submit']");
	
	
	
	
	//Action methods
	public Boolean verifyArticleIsPublished()
	{
		waitForElementToAppear(postCommentButtonBy);
		Boolean deleteArticleButtonMethod = deleteArticleButton.isDisplayed();
		return deleteArticleButtonMethod;
		
		
	}
	
	public String getArticleTitleH1()
	{
		String articleTitleH1Text = articleTitleH1.getText();
		return articleTitleH1Text;
	}
	
	
	
}
