package Academy.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Academy.PageObjects.ArticlePagePO;
import Academy.PageObjects.HomePagePO;
import Academy.PageObjects.LandingPagePO;
import Academy.PageObjects.LoggedOutPagePO;
import Academy.PageObjects.PublishArticlePO;
import Academy.PageObjects.SettingsPagePO;
import Academy.TestComponents.BaseTest02;
import io.github.bonigarcia.wdm.WebDriverManager;

public class E2ETCPO2 extends BaseTest02 {

	String articleTitle = "Title01"+System.currentTimeMillis();
		
	@Test	
	public void loginPublishLogoutTestCase() throws IOException
	{
		
		String userEmail = "jovan.kovacevic@hotmail.com";
		String userPassword = "glupavipassword";
		String userName = "JovanK";
		
		//Login on the Landing page
		//LandingPagePO lppo = launchApplication(); //Not needed because of the @BeforeMethod
		
		lppo.loginToApplication(userEmail, userPassword);
		
		//Assertions on the Home page
		HomePagePO hppo = new HomePagePO(driver);
		
		Assert.assertTrue(hppo.feedDisplayed());
		
		Assert.assertEquals(hppo.getUserPortalName(), userName);
		System.out.println(hppo.getUserPortalName());
		
	
		//Publish an Article
		
		
		String articleAbout = "About01"+System.currentTimeMillis();
		String articleText = "Text01"+System.currentTimeMillis();
		String articleTag = "Tag01";
		
		PublishArticlePO papo = new PublishArticlePO(driver);
		
		papo.postNewArticle();
		papo.populateTitle(articleTitle);
		System.out.println(articleTitle); //This is just for the following TC
		papo.populateAbout(articleAbout);
		papo.populateText(articleText);
		papo.populateTag(articleTag);
		papo.publishArticle();

		//Article page
		ArticlePagePO appo = new ArticlePagePO(driver);
		
		Assert.assertTrue(appo.verifyArticleIsPublished()); 

		//Logout
				String pHeadline = "A place to share your knowledge.";
				
				SettingsPagePO sppo = new SettingsPagePO(driver);
				
				//sppo.goToSettingsPage();
				sppo.logoutFromApplication();
				
				LoggedOutPagePO loopo = new LoggedOutPagePO(driver);
				
				Assert.assertTrue(loopo.verifyLoggedOut());
				Assert.assertTrue(loopo.verifyPTag());
				Assert.assertEquals(loopo.getPText(), pHeadline);
				System.out.println(loopo.getPText());
		
		//driver.close(); //Not needed because of the @AfterMethod
	}
	
	
	
	@Test(dependsOnMethods = {"loginPublishLogoutTestCase"})	
	public void verifyingGlobalFeedTab() throws IOException, InterruptedException
	{
		
		String userEmail = "jovan.kovacevic@hotmail.com";
		String userPassword = "glupavipassword";
		String userName = "JovanK";
		
		//Login on the Landing page
		//LandingPagePO lppo = launchApplication(); //Not needed because of the @BeforeMethod
		
		lppo.loginToApplication(userEmail, userPassword);
		
		//Assertions on the Home page
		HomePagePO hppo = new HomePagePO(driver);
		
		Assert.assertTrue(hppo.feedDisplayed());
		
		Assert.assertEquals(hppo.getUserPortalName(), userName);
		System.out.println(hppo.getUserPortalName());
		
	
		//Verify that the Article from the previous TC is publish and can be found under the Global Feed tab
		driver.findElement(By.linkText("Global Feed")).click();
		
		List<WebElement> allArticles = driver.findElements(By.cssSelector(".article-preview"));
		
		WebElement wantedAuthor = allArticles.stream().filter(oneArticle->oneArticle.findElement(By.cssSelector(".author")).getText().equalsIgnoreCase(userName))
		.findFirst().orElse(null);
		
		wantedAuthor.findElement(By.xpath("(//span) [2]")).click();
		Thread.sleep(3000);
		String articleTitleH1 = driver.findElement(By.tagName("h1")).getText();
		System.out.println(articleTitleH1);
		Assert.assertEquals(articleTitleH1, articleTitle);
		
		//driver.close(); //Not needed because of the @AfterMethod
	}

}