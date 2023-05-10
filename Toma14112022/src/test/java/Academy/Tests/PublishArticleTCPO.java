package Academy.Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Academy.PageObjects.ArticlePagePO;
import Academy.PageObjects.HomePagePO;
import Academy.PageObjects.LandingPagePO;
import Academy.PageObjects.PublishArticlePO;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PublishArticleTCPO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		WebDriverManager.chromedriver().setup(); // WebDriver manager for invoking the chromedriver that is not on our local machine
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		String userEmail = "jovan.kovacevic@hotmail.com";
		String userPassword = "glupavipassword";
		String userName = "JovanK";
		
		//Login on the Landing page
		LandingPagePO lppo = new LandingPagePO(driver); //Once a constructor has been created, create an Object of this PO class
		
		lppo.goTo();
		lppo.loginToApplication(userEmail, userPassword);
		
		//Assertions on the Home page
		HomePagePO hppo = new HomePagePO(driver);
		
		Assert.assertTrue(hppo.feedDisplayed());
		
		Assert.assertEquals(hppo.getUserPortalName(), userName);
		System.out.println(hppo.getUserPortalName());
		
	
		//Publish an Article
		
		String articleTitle = "Title01"+System.currentTimeMillis();
		String articleAbout = "About01"+System.currentTimeMillis();
		String articleText = "Text01"+System.currentTimeMillis();
		String articleTag = "Tag01";
		
		PublishArticlePO papo = new PublishArticlePO(driver);
		
		papo.postNewArticle();
		papo.populateTitle(articleTitle);
		papo.populateAbout(articleAbout);
		papo.populateText(articleText);
		papo.populateTag(articleTag);
		papo.publishArticle();

		//Article page
		ArticlePagePO appo = new ArticlePagePO(driver);
		
		Assert.assertTrue(appo.verifyArticleIsPublished()); 


		
		
	}

}
