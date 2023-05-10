package Academy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class PublishArticlePO extends AbstractComponent {

	
WebDriver driver;
	
	public PublishArticlePO(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); //The method for PageFactory to knows about the driver
	}
	
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	
	
	//PageFactory
	@FindBy(css="a[href='#editor']")
	WebElement newPostButton;
	
	@FindBy(css="input[placeholder='Article Title']")
	WebElement articleTitleField;
	
	@FindBy(xpath="//input[contains (@placeholder, 'this article about?')]")
	WebElement articleAboutField;
	
	@FindBy(css="textarea[placeholder='Write your article (in markdown)']")
	WebElement articleTextField;
	
	@FindBy(css="input[placeholder='Enter tags']")
	WebElement articleTagField;
	
	@FindBy(css=".btn-lg")
	WebElement publishArticleButton;
	
	
	//Action methods
	public void postNewArticle()
	{
		newPostButton.click();
	}
	
	public void populateTitle(String articleTitle)
	{
		articleTitleField.sendKeys(articleTitle);
	}
	
	public void populateAbout(String articleAbout)
	{
		articleAboutField.sendKeys(articleAbout);
	}
	
	public void populateText(String articleText)
	{
		articleTextField.sendKeys(articleText);
	}
	
	public void populateTag(String articleTag)
	{
		articleTagField.sendKeys(articleTag);
	}
	
	public void publishArticle()
	{
		publishArticleButton.click();
	}
	
	
	
	
	
}
