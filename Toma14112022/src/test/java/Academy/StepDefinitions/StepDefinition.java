package Academy.StepDefinitions;


import org.testng.Assert;

import Academy.PageObjects.ArticlePagePO;
import Academy.PageObjects.HomePagePO;
import Academy.PageObjects.LandingPagePO;
import Academy.PageObjects.LoggedOutPagePO;
import Academy.PageObjects.PublishArticlePO;
import Academy.PageObjects.SettingsPagePO;
import Academy.TestComponents.BaseTest03;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefinition extends BaseTest03 {

	public LandingPagePO lppo;
	public HomePagePO hppo;
	public PublishArticlePO papo;
	public ArticlePagePO appo;
	public SettingsPagePO sppo;
	public LoggedOutPagePO loopo;

	
	
    @Given("^A user has landed on the Conduit landing page$")
    public void a_user_has_landed_on_the_conduit_landing_page() throws Throwable {
    	
    	lppo = launchApplication();
    }
    
    
    @Given("^A user has logged in with a username (.+) and a password (.+)$")
    public void a_user_has_logged_in_with_a_username_and_a_password(String useremail, String userpassword) throws Throwable {
        
    	lppo.loginToApplication(useremail, userpassword);
    }
    
    
    @And("^Verify the user has a landed on the Home page with a valid name (.+)$")
    public void verify_the_user_has_a_landed_on_the_home_page_with_a_valid_name(String username) throws Throwable {
        
    	 hppo = new HomePagePO(driver);
    	 Assert.assertTrue(hppo.feedDisplayed());
 		
 		Assert.assertEquals(hppo.getUserPortalName(), username);
 		System.out.println(hppo.getUserPortalName());
    }
    
    
    @When("^A user populates all the mandatory fields (.+) (.+) (.+) (.+) and publishes an article$")
    public void a_user_populates_all_the_mandatory_fields_and_publishes_an_article(String title, String about, String text, String tag) throws Throwable {
        
    	 papo = new PublishArticlePO(driver);
    	 
    	 papo.postNewArticle();
 		papo.populateTitle(title+System.currentTimeMillis());
 		System.out.println(title+System.currentTimeMillis()); //This is just for the following TC
 		papo.populateAbout(about);
 		papo.populateText(text);
 		papo.populateTag(tag);
 		papo.publishArticle();
    
    }
    
    
    @And("^Verify that the article has been published$")
    public void verify_that_the_article_has_been_published() throws Throwable {
        
    	appo = new ArticlePagePO(driver);
		
		Assert.assertTrue(appo.verifyArticleIsPublished());
    }
    
    
    @Then("^A user loggs out from the application$")
    public void a_user_loggs_out_from_the_application() throws Throwable {
        
    	 sppo = new SettingsPagePO(driver);
		
		//sppo.goToSettingsPage();
		sppo.logoutFromApplication();
    }
    
    
    @And("^A headline \"([^\"]*)\" is displayed$")
    public void a_headline_something_is_displayed(String strArg1) throws Throwable {
        
    	 loopo = new LoggedOutPagePO(driver);
		
		Assert.assertTrue(loopo.verifyLoggedOut());
		Assert.assertTrue(loopo.verifyPTag());
		Assert.assertEquals(loopo.getPText(), strArg1);
		System.out.println(loopo.getPText());
    }
    
    
    @And("^A browser is closed$")
    public void a_browser_is_closed() throws Throwable {
        
    	tearDown();
    }
    
    //ErrorValidations
    @Given("^A user has logged in with a wrong username (.+) and/or password (.+)$")
    public void a_user_has_logged_in_with_a_wrong_username_andor_password(String useremail, String userpassword) throws Throwable {
        
    	lppo.loginToApplication(useremail, userpassword);
    }
    
    
    @When("^Verify the user has not landed on the Home page$")
    public void verify_the_user_has_not_landed_on_the_home_page() throws Throwable {
        
    	System.out.println(lppo.getInvalidCredentialsMessage());
    }
    
    
    @Then("^A message \"([^\"]*)\" is displayed$")
    public void a_message_something_is_displayed(String strArg1) throws Throwable {
        
    	Assert.assertEquals(lppo.getInvalidCredentialsMessage(), strArg1);
    }
    
    
    //Another ErrovValidations
    @Given("^A user has logged in with a correct username (.+) and password (.+)$")
    public void a_user_has_logged_in_with_a_correct_username_and_password(String useremail, String userpassword) throws Throwable {
        
    	lppo.loginToApplication(useremail, userpassword);
    }
    
    
    @Then("^Verify that the user's (.+) is not as expected$")
    public void verify_that_the_users_is_not_as_expected(String username) throws Throwable {
        
    	 hppo = new HomePagePO(driver);
		
		Assert.assertTrue(hppo.feedDisplayed());
		
		Assert.assertNotEquals(hppo.getUserPortalName(), username);
		System.out.println(hppo.getUserPortalName());
    }
    

}