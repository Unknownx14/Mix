package Academy.Toma;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.HomePagePO;
import pageObject.LandingPagePO;
import pageObject.NewPostPO;
import pageObject.SettingsPO;
import resources.Base;

public class NewPost4 extends Base {

	@Test(dataProvider = "getUserInfo")
	public void homePageLoginValidation(UserInfo[] userInfo) throws IOException, InterruptedException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));

		HomePagePO hppo = new HomePagePO(driver);

		LandingPagePO lppo = new LandingPagePO(driver);
		NewPostPO nppo = new NewPostPO(driver);
		SettingsPO sppo = new SettingsPO(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		for(UserInfo ui : userInfo)
		{
			
			hppo.emailMethod().sendKeys(ui.email);
			hppo.password().sendKeys(ui.password);
			hppo.submitMethod().click();

			Assert.assertEquals(lppo.usernameMethod(), ui.username);
			Thread.sleep(1000);

			for (Article article : ui.allArticles) {
				lppo.newPostMethod().click();
				Thread.sleep(1000);

				nppo.articleTitleMethod().sendKeys(article.title);
				nppo.whatAboutMethod().sendKeys(article.h1);
				nppo.writeArticle().sendKeys(article.h2);
				nppo.tagsMethod().sendKeys(article.tag);
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
	

	/*@AfterTest
	public void teardown() {
		driver.close();
	}*/



	public static class Article {
		String title;
		String h1;
		String h2;
		String tag;

		public Article(String title, String h1, String h2, String tag) {
			this.title = title;
			this.h1 = h1;
			this.h2 = h2;
			this.tag = tag;
		}
	}

	//@DataProvider
	/*public List<Article> getDataJR() {
		List<Article> articles = new ArrayList<>();
		articles.add(new Article("title", "h1", "h2", "tag"));
		articles.add(new Article("title01", "h101", "h201", "tag01"));
		return articles;
	}*/

	public static class UserInfo {
		String email;
		String password;
		String username;
		List<Article> allArticles;

		/*
		 * public UserInfo(String email, String password, String username, List<Article>
		 * allArticles) {
		 * 
		 * this.email = email; this.password = password; this.username = username;
		 * this.allArticles = allArticles; }
		 * 
		 * 
		 * public UserInfo() { // TODO Auto-generated constructor stub }
		 */

	}

	@DataProvider
	public UserInfo[] getUserInfo() {
		List<UserInfo> userInfo = new ArrayList<>();
		List<Article> articles = new ArrayList<>();
		articles.add(new Article("title"+System.currentTimeMillis(), "h1", "h2", "tag"));
		articles.add(new Article("title01" +System.currentTimeMillis(), "h101", "h201", "tag01"));
		/* userInfo.add(new UserInfo("email", "pasword", "username", articles)); */
		UserInfo u = new UserInfo();
		u.email = "testuser@iptiq.com";
		u.password = "test1234";
		u.username = "testuser@iptiq.com";
		u.allArticles = articles;
		userInfo.add(u);
		return userInfo.toArray(new UserInfo[userInfo.size()]);

	}

}
