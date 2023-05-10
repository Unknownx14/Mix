package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewPostPO {

	
	public WebDriver driver;
	
	 By articleTitle = By.xpath("//input[@placeholder='Article Title']");
	 By whatAbout = By.xpath("//input[contains(@placeholder, 'What')]");
	 By writeArticle = By.xpath("//textarea[contains(@placeholder, 'Write')]");
	 By tags = By.xpath("//input[@placeholder='Enter tags']");
	 By publishArticle = By.cssSelector("button[class*='btn-primary']");
	 By comment = By.className("form-control");
	 
	 public NewPostPO(WebDriver driver) {
		// TODO Auto-generated constructor stub
		 this.driver=driver;
	}

	public WebElement articleTitleMethod()
	 {
		 return driver.findElement(articleTitle);
	 }
	 
	 public WebElement whatAboutMethod()
	 {
		 return driver.findElement(whatAbout);
	 }
	 public WebElement writeArticle()
	 {
		 return driver.findElement(writeArticle);
	 }
	 public WebElement tagsMethod()
	 {
		 return driver.findElement(tags);
	 }
	 public WebElement publishArticleMethod()
	 {
		 return driver.findElement(publishArticle);
	 }
	 public boolean commentMethod()
	 {
		 return driver.findElement(comment).isDisplayed();
	 }
	 
}
