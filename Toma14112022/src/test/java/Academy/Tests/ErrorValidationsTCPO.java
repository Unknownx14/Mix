package Academy.Tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
import Academy.TestComponents.BaseTest03;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTCPO extends BaseTest03 {

		
	@Test(groups = {"ErrorHandling"})	
	public void loginWrongCredentialsTestCase() throws IOException
	{
		
		String userEmail = "jovan.kovacevic@hotmail.com";
		String userPassword = "glupavipassword01";
		String userName = "JovanK";
		String errorMessage = "email or password is invalid";
		
		//Login on the Landing page
		//LandingPagePO lppo = launchApplication(); //Not needed because of the @BeforeMethod
		
		lppo.loginToApplication(userEmail, userPassword);
		System.out.println(lppo.getInvalidCredentialsMessage());
		Assert.assertEquals(lppo.getInvalidCredentialsMessage(), errorMessage);
		
		
		//driver.close(); //Not needed because of the @AfterMethod
	}
	
	
	
	@Test/*(retryAnalyzer=Academy.TestComponents.Retry.class)	*/(groups = {"ErrorHandling"})
	public void loginWrongUsernameDisplayedTestCase() throws IOException
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
		
		Assert.assertNotEquals(hppo.getUserPortalName(), userName);
		System.out.println(hppo.getUserPortalName());
		
		
		//driver.close(); //Not needed because of the @AfterMethod
	}
	
	
	@Test(groups = {"ErrorHandling"})
	public void loginWrongUsernameDisplayedTestCaseTransformer() throws IOException
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
		
		Assert.assertNotEquals(hppo.getUserPortalName(), userName);
		System.out.println(hppo.getUserPortalName());
		
		
		//driver.close(); //Not needed because of the @AfterMethod
	}


}
