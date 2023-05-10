package Academy.Toma;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.HomePagePO;
import pageObject.LandingPagePO;
import pageObject.NewPostPO;
import resources.Base;

public class NewPost3 extends Base {

	
	//This is only if we have parallel execution of these test in testng.xml file (video 202)
			public WebDriver driver;
			
	//Creating this Log object for this LogManager API
	public static Logger log = LogManager.getLogger(Base.class.getName());
	
	
	@Test(dataProvider="getData")
	public void homePageLoginValidation(String email, String password, String username, String articleA, String articleB, String articleC, String articleD, int numberArticles ) throws IOException, InterruptedException
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
		
		/*lppo.newPostMethod().click();
		Thread.sleep(1000);
		
		NewPostPO nppo = new NewPostPO(driver);
		
		nppo.articleTitleMethod().sendKeys(articleA);
		nppo.whatAboutMethod().sendKeys(articleB);
		nppo.writeArticle().sendKeys(articleC);
		nppo.tagsMethod().sendKeys(articleD);
		nppo.publishArticleMethod().click();
		Thread.sleep(1000);
		System.out.println(numberArticles);
		Assert.assertTrue(nppo.commentMethod());
		Thread.sleep(1000);*/
		
		int i=0;
		if(i<numberArticles)
		{
		do
		{
			lppo.newPostMethod().click();
			Thread.sleep(1000);
			
			NewPostPO nppo = new NewPostPO(driver);
			
			nppo.articleTitleMethod().sendKeys(articleA+System.currentTimeMillis());
			nppo.whatAboutMethod().sendKeys(articleB);
			nppo.writeArticle().sendKeys(articleC);
			nppo.tagsMethod().sendKeys(articleD);
			nppo.publishArticleMethod().click();
			Thread.sleep(1000);
			Assert.assertTrue(nppo.commentMethod());
			Thread.sleep(1000);
			log.info("The user with the email - "+email+" and the password - "+password+"has published the article with index number "+i);
			i++;
		}while (i<numberArticles);
		}
	}
	
	@AfterTest
	public void teardown()
	{
		driver.close();
	}
	
	@DataProvider
	public Object[][] getData()
	{
		int j=2; //How many articles you would like to publish
		int k=2; //How many users would you like to use

		Object[][] dataProv = new Object [k][8];
		dataProv[0][0] = "testuser@iptiq.com";
		dataProv[0][1] = "test1234";
		dataProv[0][2] = "testuser@iptiq.com";
		dataProv[0][3] = "Article Title TU";
		dataProv[0][4] = "What's this article about?";
		dataProv[0][5] = "Write your article (in markdown)";
		dataProv[0][6] = "implementations";
		dataProv[0][7] = j;
		
		
		dataProv[1][0] = "jovan.kovacevic@hotmail.com";
		dataProv[1][1] = "glupavipassword";
		dataProv[1][2] = "JovanK";
		dataProv[1][3] = "Article Title JK";
		dataProv[1][4] = "What's this article about?";
		dataProv[1][5] = "Write your article (in markdown)";
		dataProv[1][6] = "implementations";
		dataProv[1][7] = j;
		
		return dataProv;
	}
	
}
