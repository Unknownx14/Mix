package Academy.Toma;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.HomePagePO;
import pageObject.LandingPagePO;
import pageObject.NewPostPO;
import pageObject.SettingsPO;
import resources.Base;

//import Academy.Toma.NewPost4.UserInfo;

public class Something extends Base {

	
	@Test(dataProvider="getUserInfo")
	public void homePageLoginAndPublishArticles(UserInfoClass[] userInfoList) throws IOException, InterruptedException
	{
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));

		HomePagePO hppo = new HomePagePO(driver);
		LandingPagePO lppo = new LandingPagePO(driver);
		NewPostPO nppo = new NewPostPO(driver);
		SettingsPO sppo = new SettingsPO(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		for(UserInfoClass ui : userInfoList)
		{
			hppo.emailMethod().sendKeys(ui.email);
			hppo.password().sendKeys(ui.password);
			hppo.submitMethod().click();
			Assert.assertEquals(lppo.usernameMethod(), ui.username);
			Thread.sleep(1000);
			
			for(ArticlesClass singleArticle : ui.allArticles )
			{
				lppo.newPostMethod().click();
				Thread.sleep(1000);
				
				nppo.articleTitleMethod().sendKeys(singleArticle.title+System.currentTimeMillis());
				nppo.whatAboutMethod().sendKeys(singleArticle.h1);
				nppo.writeArticle().sendKeys(singleArticle.h2);
				nppo.tagsMethod().sendKeys(singleArticle.tag);
				nppo.publishArticleMethod().click();
				Thread.sleep(1000);
				Assert.assertTrue(nppo.commentMethod());
				Thread.sleep(1000);
				
			}
			
			sppo.settingsMethod().click();
			//JS for scroll down
			
			js.executeScript("window.scrollBy(0, 500)");
			Thread.sleep(1000);
			
			sppo.logoutMethod().click();
			Assert.assertTrue(sppo.signinMethod());
		}
		
		
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
	
	/*public List<ArticlesClass> somethingMethod()
	{
		List<ArticlesClass> articlesList = new ArrayList<>();
		articlesList.add(new ArticlesClass("Samo", "Sloga", "Srbina", "Spasava"));
		articlesList.add(new ArticlesClass("Sveti", "Sava", "Skolska", "Slava"));
		return articlesList;
	}*/
	
	public static class UserInfoClass
	{
		
		String email;
		String password;
		String username;
		List<ArticlesClass> allArticles;
		
		public UserInfoClass(String email, String password, String username, List<ArticlesClass> allArticles) {
			// TODO Auto-generated constructor stub
			this.email=email;
			this.password=password;
			this.username=username;
			this.allArticles=allArticles;
		}
	}
	
	@DataProvider
	public UserInfoClass[] getUserInfo()
	{
		List<UserInfoClass> userInfoList = new ArrayList<>();
		List<ArticlesClass> articlesList = new ArrayList<>();
		articlesList.add(new ArticlesClass("Samo", "Sloga", "Srbina", "Spasava"));
		articlesList.add(new ArticlesClass("Sveti", "Sava", "Skolska", "Slava"));
		userInfoList.add(new UserInfoClass("testuser@iptiq.com", "test1234", "testuser@iptiq.com", articlesList));
		
		
		return userInfoList.toArray(new UserInfoClass[userInfoList.size()]);
		
		
		
	}
	
	
}
