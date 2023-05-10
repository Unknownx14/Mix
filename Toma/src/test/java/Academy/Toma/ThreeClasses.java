package Academy.Toma;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import Academy.Toma.Something02.UserInfoClass;
import pageObject.HomePagePO;
import pageObject.LandingPagePO;
import pageObject.NewPostPO;
import pageObject.SettingsPO;
import resources.Base;

public class ThreeClasses extends Base {

	// This is only if we have parallel execution of these test in testng.xml file
	// (video 202)
	public WebDriver driver;

//Creating this Log object for this LogManager API
	public static Logger log = LogManager.getLogger(Base.class.getName());

@Test(dataProvider="getAllThreeClasses")
public void ThreeClasseValidation(AndUrlClass[] allUrls) throws IOException, InterruptedException
{
driver = initializeDriver();


HomePagePO hppo = new HomePagePO(driver);
LandingPagePO lppo = new LandingPagePO(driver);
NewPostPO nppo = new NewPostPO(driver);
SettingsPO sppo = new SettingsPO(driver);
JavascriptExecutor js = (JavascriptExecutor) driver;
int i=1;
	
	for(AndUrlClass auc : allUrls)
	{

		driver.get(auc.urls);
		log.info("The driver has been initialized and the url is reached");
		
		for(UserInfoClass ui : auc.allUsers)
		{
			hppo.signinOkMethod().click();
			Thread.sleep(1000);
			hppo.emailMethod().sendKeys(ui.email);
			hppo.password().sendKeys(ui.password);
			hppo.submitMethod().click();
			Assert.assertEquals(lppo.usernameMethod(), ui.username);
			Thread.sleep(1000);
			log.info("The user with the email - "+ui.email+" and the password - "+ui.password+"has successfully logged into the application");
			
				for(ArticlesClass ac : ui.allArticles)
				{
					lppo.newPostMethod().click();
					Thread.sleep(1000);
					
					nppo.articleTitleMethod().sendKeys(ac.title+System.currentTimeMillis());
					nppo.whatAboutMethod().sendKeys(ac.h1);
					nppo.writeArticle().sendKeys(ac.h2);
					nppo.tagsMethod().sendKeys(ac.tag);
					nppo.publishArticleMethod().click();
					
					Thread.sleep(1000);
					Assert.assertTrue(nppo.commentMethod());
					Thread.sleep(1000);
					log.info("The user with the email - "+ui.email+" and the password - "+ui.password+"has published the article number "+i);
					i++;
				}
				sppo.settingsMethod().click();
				//JS for scroll down
				
				js.executeScript("window.scrollBy(0, 500)");
				Thread.sleep(1000);
				
				sppo.logoutMethod().click();
				Assert.assertTrue(sppo.signinMethod());
			
		}
		
			
	}
		
		
		
		
		
		}
	
@AfterTest
public void teardown()
{
	driver.close();
}




	public static class ArticlesClass
	{
		String title;
		String h1;
		String h2;
		String tag;
		
		public ArticlesClass(String title, String h1, String h2, String tag) {
			
			this.title = title;
			this.h1 = h1;
			this.h2 = h2;
			this.tag = tag;
		}
		
	}
	
	public static class UserInfoClass
	{
		String email;
		String password;
		String username;
		List<ArticlesClass> allArticles;
		
		public UserInfoClass(String email, String password, String username, List<ArticlesClass> allArticles) {
			super();
			this.email = email;
			this.password = password;
			this.username = username;
			this.allArticles = allArticles;
		}
		
	}
	
	
	public static class AndUrlClass
	{
		String urls;
		List<UserInfoClass> allUsers;
		
		public AndUrlClass(String urls, List<UserInfoClass> allUsers) {
			
			this.urls = urls;
			this.allUsers = allUsers;
		}
		
	}
	
	@DataProvider
	public AndUrlClass[] getAllThreeClasses()
	{
		List<UserInfoClass> userInfoList = new ArrayList<>();
		List<ArticlesClass> articlesList = new ArrayList<>();
		List<AndUrlClass> allUrls = new ArrayList<>();
		articlesList.add(new ArticlesClass("One", "Two", "Three", "Four"));
		articlesList.add(new ArticlesClass("Five", "Six", "Seven", "Eight"));
		userInfoList.add(new UserInfoClass("testuser@iptiq.com", "test1234", "testuser@iptiq.com", articlesList));
		userInfoList.add(new UserInfoClass("jovan.kovacevic@hotmail.com", "glupavipassword", "JovanK", articlesList));
		allUrls.add(new AndUrlClass("https://react-redux.realworld.io/#/?_k=ft3r8c", userInfoList));
		allUrls.add(new AndUrlClass("https://react-redux.realworld.io/#/?_k=ft3r8c", userInfoList));
		
		return allUrls.toArray(new AndUrlClass[allUrls.size()]);
	}

}
