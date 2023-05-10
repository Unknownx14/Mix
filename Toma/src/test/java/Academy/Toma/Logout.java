package Academy.Toma;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.HomePagePO;
import pageObject.LandingPagePO;
import pageObject.SettingsPO;
import resources.Base;

public class Logout extends Base {

	//This is only if we have parallel execution of these test in testng.xml file (video 202)
		public WebDriver driver;
	
	//Creating this Log object for this LogManager API
			public static Logger log = LogManager.getLogger(Base.class.getName());
	
	@Test(dataProvider="getData")
	public void LogoutValidation(String email, String password, String username) throws IOException, InterruptedException
	{
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		log.info("The driver has been initialized and the url is reached");
		
		HomePagePO hppo = new HomePagePO(driver);
		
		hppo.emailMethod().sendKeys(email);
		hppo.password().sendKeys(password);
		hppo.submitMethod().click();
		log.info("The user with the email - "+email+" and the password - "+password+"has successfully logged into the application");
		
		LandingPagePO lppo = new LandingPagePO(driver);
		
		Assert.assertEquals(lppo.usernameMethod(), username);
		Thread.sleep(1000);
		
		SettingsPO sppo = new SettingsPO(driver);
		
		sppo.settingsMethod().click();
		//JS for scroll down
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 500)");
		Thread.sleep(1000);
		
		sppo.logoutMethod().click();
		Assert.assertTrue(sppo.signinMethod());
		log.info("The user with the email - "+email+" and the password - "+password+"has successfully logged out from the applciation");
		
	}
	
	@AfterTest
	public void teardown()
	{
		driver.close();
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] dataProv = new Object [2][3];
		dataProv[0][0] = "testuser@iptiq.com";
		dataProv[0][1] = "test1234";
		dataProv[0][2] = "testuser@iptiq.com";
		
		dataProv[1][0] = "jovan.kovacevic@hotmail.com";
		dataProv[1][1] = "glupavipassword";
		dataProv[1][2] = "JovanK";
		
		return dataProv;
	}
	
}
