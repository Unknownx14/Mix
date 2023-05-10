package Academy.Toma;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.HomePagePO;
import pageObject.LandingPagePO;
import pageObject.NewPostPO;
import resources.Base;

public class NewPost extends Base {

	@Test(dataProvider="getData")
	public void homePageLoginValidation(String email, String password, String username, String articleA, String articleB, String articleC, String articleD, int numberArticles ) throws IOException, InterruptedException
	{
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		
		HomePagePO hppo = new HomePagePO(driver);
		
		hppo.emailMethod().sendKeys(email);
		hppo.password().sendKeys(password);
		hppo.submitMethod().click();
		
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
			
			nppo.articleTitleMethod().sendKeys(articleA);
			nppo.whatAboutMethod().sendKeys(articleB);
			nppo.writeArticle().sendKeys(articleC);
			nppo.tagsMethod().sendKeys(articleD);
			nppo.publishArticleMethod().click();
			Thread.sleep(1000);
			Assert.assertTrue(nppo.commentMethod());
			Thread.sleep(1000);
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
		int p=j*k;
		int m = 7;
		int n = m*100;
		Object[][] dataProv = new Object [p][8];
		dataProv[0][0] = "testuser@iptiq.com";
		dataProv[0][1] = "test1234";
		dataProv[0][2] = "testuser@iptiq.com";
		dataProv[0][3] = "Article Title JK" + " " + m;
		dataProv[0][4] = "What's this article about?" + " " + m;
		dataProv[0][5] = "Write your article (in markdown)" + " " + m;
		dataProv[0][6] = "implementations";
		dataProv[0][7] = j;
		/*dataProv[0][8] = "Article Title JK" + " " + n;
		dataProv[0][9] = "What's this article about?" + " " + n;
		dataProv[0][10] = "Write your article (in markdown)" + " " + n;
		dataProv[0][11] = "implementations";*/
		
		dataProv[1][0] = "testuser@iptiq.com";
		dataProv[1][1] = "test1234";
		dataProv[1][2] = "testuser@iptiq.com";
		dataProv[1][3] = "Article Title JK" + " " + n;
		dataProv[1][4] = "What's this article about?" + " " + n;
		dataProv[1][5] = "Write your article (in markdown)" + " " + n;
		dataProv[1][6] = "implementations";
		dataProv[1][7] = j;
		
		dataProv[2][0] = "jovan.kovacevic@hotmail.com";
		dataProv[2][1] = "glupavipassword";
		dataProv[2][2] = "JovanK";
		dataProv[2][3] = "Article Title JK" + " " + m;
		dataProv[2][4] = "What's this article about?" + " " + m;
		dataProv[2][5] = "Write your article (in markdown)" + " " + m;
		dataProv[2][6] = "implementations";
		dataProv[2][7] = j;
		/*dataProv[1][8] = "Article Title JK" + " " + n;
		dataProv[1][9] = "What's this article about?" + " " + n;
		dataProv[1][10] = "Write your article (in markdown)" + " " + n;
		dataProv[1][11] = "implementations";*/
		
		dataProv[3][0] = "jovan.kovacevic@hotmail.com";
		dataProv[3][1] = "glupavipassword";
		dataProv[3][2] = "JovanK";
		dataProv[3][3] = "Article Title JK" + " " + n;
		dataProv[3][4] = "What's this article about?" + " " + n;
		dataProv[3][5] = "Write your article (in markdown)" + " " + n;
		dataProv[3][6] = "implementations";
		dataProv[3][7] = j;
		
		return dataProv;
	}
	
}
