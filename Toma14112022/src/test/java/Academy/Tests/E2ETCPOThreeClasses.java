package Academy.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
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
import io.github.bonigarcia.wdm.WebDriverManager;

public class E2ETCPOThreeClasses extends BaseTest02 {

	//String articleTitle = "Title01"+System.currentTimeMillis();
		
	@Test(dataProvider="getData")	
	public void loginPublishLogoutTestCase(userInfoClass[] userList) throws IOException
	{
		
		//***************Two Classes
		
		HomePagePO hppo = new HomePagePO(driver);
		PublishArticlePO papo = new PublishArticlePO(driver);
		ArticlePagePO appo = new ArticlePagePO(driver);
		SettingsPagePO sppo = new SettingsPagePO(driver);
		LoggedOutPagePO loopo = new LoggedOutPagePO(driver);
		
		
		for(userInfoClass a1 : userList)
		{
			//Login on the Landing page
			lppo.loginToApplication(a1.Email, a1.Password);
			
			
			//Assertions on the Home page		
			Assert.assertTrue(hppo.feedDisplayed());
			
			Assert.assertEquals(hppo.getUserPortalName(), a1.Nick);
			System.out.println(hppo.getUserPortalName());
			
				for(articlesClass b1 : a1.completeArticle)
				{
					
					//Publish an Article
					
					papo.postNewArticle();
					papo.populateTitle(b1.Title+System.currentTimeMillis());
					System.out.println(b1.Title+System.currentTimeMillis()); //This is just for the following TC
					papo.populateAbout(b1.About);
					papo.populateText(b1.Txt);
					papo.populateTag(b1.Tag);
					papo.publishArticle();

					//Article page					
					Assert.assertTrue(appo.verifyArticleIsPublished()); 
					
				}
				
			//Logout
				String pHeadline = "A place to share your knowledge.";
							
				//sppo.goToSettingsPage();
				sppo.logoutFromApplication();
						
				Assert.assertTrue(loopo.verifyLoggedOut());
				Assert.assertTrue(loopo.verifyPTag());
				Assert.assertEquals(loopo.getPText(), pHeadline);
				System.out.println(loopo.getPText());
		
		//driver.close(); //Not needed because of the @AfterMethod
			
		}
		

	}
	
	
	
public static class articlesClass
{
	String Title;
	String About;
	String Txt;
	String Tag;
	
	public articlesClass(String title, String about, String txt, String tag) {
		
		this.Title = title;
		this.About = about;
		this.Txt = txt;
		this.Tag = tag;
	}
		
}

public static class userInfoClass
{
	String Email;
	String Password;
	String Nick;
	List<articlesClass> completeArticle;
	
	public userInfoClass(String email, String password, String nick, List<articlesClass> completeArticle) {
		//super();
		this.Email = email;
		this.Password = password;
		this.Nick = nick;
		this.completeArticle = completeArticle;
	}
	
}


@DataProvider
public userInfoClass[] getData()
{
	
	List<articlesClass> articlesList = new ArrayList<>();
	List<userInfoClass> userList = new ArrayList<>();
	
	articlesList.add(new articlesClass("Ten", "Two", "Three", "Four"));
	articlesList.add(new articlesClass("Fifteen", "Six", "Seven", "Eight"));
	userList.add(new userInfoClass("testuser@iptiq.com", "test1234", "testuser@iptiq.com", articlesList));
	userList.add(new userInfoClass("jovan.kovacevic@hotmail.com", "glupavipassword", "JovanK", articlesList));
	
	return userList.toArray(new userInfoClass[userList.size()]);
	
	}

	
/*	
public static class urlClass
{
	
	String url;
	List<userInfoClass> completeUsers;
	
	public urlClass(String url, List<userInfoClass> completeUsers) {
		
		this.url = url;
		this.completeUsers = completeUsers;
	}
	
}


@DataProvider
public urlClass[] getData()
{
	
	List<articlesClass> articlesList = new ArrayList<>();
	List<userInfoClass> userList = new ArrayList<>();
	List<urlClass> urlList = new ArrayList<>();
	
	articlesList.add(new articlesClass("One", "Two", "Three", "Four"));
	articlesList.add(new articlesClass("Five", "Six", "Seven", "Eight"));
	userList.add(new userInfoClass("testuser@iptiq.com", "test1234", "testuser@iptiq.com", articlesList));
	userList.add(new userInfoClass("jovan.kovacevic@hotmail.com", "glupavipassword", "JovanK", articlesList));
	urlList.add(new urlClass("https://react-redux.realworld.io/#/?_k=ft3r8c", userList));
	urlList.add(new urlClass("https://react-redux.realworld.io/#/?_k=ft3r8c", userList));
	
	return urlList.toArray(new urlClass[urlList.size()]);
	
	}*/


}