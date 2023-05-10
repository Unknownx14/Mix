package Academy.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Academy.PageObjects.ArticlePagePO;
import Academy.PageObjects.HomePagePO;
import Academy.PageObjects.LandingPagePO;
import Academy.PageObjects.LoggedOutPagePO;
import Academy.PageObjects.PublishArticlePO;
import Academy.PageObjects.SettingsPagePO;
import Academy.TestComponents.BaseTest02;
import Academy.TestComponents.BaseTest03;
import io.github.bonigarcia.wdm.WebDriverManager;

public class E2ETCPO6 extends BaseTest03 {

	String articleTitle = "Title01"+System.currentTimeMillis();
		
	@Test(dataProvider="getData", groups= {"Publish"})	
	public void loginPublishLogoutTestCase(HashMap<String, String> input ) throws IOException
	{
		
/*		String userEmail = "jovan.kovacevic@hotmail.com";
		String userPassword = "glupavipassword";
		String userName = "JovanK";*/ //This is now in the DataProvider and in the () of this TC
		
		//Login on the Landing page
		//LandingPagePO lppo = launchApplication(); //Not needed because of the @BeforeMethod
		
		lppo.loginToApplication(input.get("userEmail"), input.get("userPassword")); //(userEmail, userPassword)
		
		//Assertions on the Home page
		HomePagePO hppo = new HomePagePO(driver);
		
		Assert.assertTrue(hppo.feedDisplayed());
		
		Assert.assertEquals(hppo.getUserPortalName(), input.get("userName"));
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
	
	
	
	@Test(dataProvider="getData", dependsOnMethods = {"loginPublishLogoutTestCase"})	
	public void verifyingGlobalFeedTab(HashMap<String, String> input) throws IOException, InterruptedException
	{
		
	/*	String userEmail = "jovan.kovacevic@hotmail.com";
		String userPassword = "glupavipassword";
		String userName = "JovanK";*/
		
		//Login on the Landing page
		//LandingPagePO lppo = launchApplication(); //Not needed because of the @BeforeMethod
		
		lppo.loginToApplication(input.get("userEmail"), input.get("userPassword"));
		
		//Assertions on the Home page
		HomePagePO hppo = new HomePagePO(driver);
		
		Assert.assertTrue(hppo.feedDisplayed());
		
		Assert.assertEquals(hppo.getUserPortalName(), input.get("userName"));
		System.out.println(hppo.getUserPortalName());
		
	
		//Verify that the Article from the previous TC is publish and can be found under the Global Feed tab
		hppo.goToGlobalFeedTab();
		
		hppo.getOneArticle(input.get("userName"));
		
		Thread.sleep(3000);
		ArticlePagePO appo = new ArticlePagePO(driver);
		
		System.out.println(appo.getArticleTitleH1());
		Assert.assertEquals(appo.getArticleTitleH1(), articleTitle);
		
		//driver.close(); //Not needed because of the @AfterMethod
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		
		List<HashMap<String, String>> data = getJsonDataToHashMap(System.getProperty("user.dir") + "\\src\\test\\java\\Academy\\Data\\PublishArticle.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}

}