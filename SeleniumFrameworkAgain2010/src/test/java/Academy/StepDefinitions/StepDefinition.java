package Academy.StepDefinitions;

import org.testng.Assert;

import Academy.TestComponents.BaseTest;
import Academy.pageObjects.LandingPagePOAGAIN;
import Academy.pageObjects.MyCartPOAGAIN;
import Academy.pageObjects.PlaceOrderPOAGAIN;
import Academy.pageObjects.ProductCatalogPOAGAIN;
import Academy.pageObjects.ThankYouPOAGAIN;
import Academy.pageObjects.YourOrdersPOAGAIN;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest {
	
	public LandingPagePOAGAIN lpPO;
	public ProductCatalogPOAGAIN pcPO;
	public MyCartPOAGAIN mcPO;
	public PlaceOrderPOAGAIN poPO;
	public ThankYouPOAGAIN tyPO;
	public YourOrdersPOAGAIN yoPO;
	
	
	@Given("^A user has landed on the Ecommerce landing page$")
    public void a_user_has_landed_on_the_ecommerce_landing_page() throws Throwable {
        
		 lpPO = launchApplication();
		
    }
	
	@Given("^A user has logged in with a username (.+) and a password (.+)$")
    public void a_user_has_logged_in_with_a_username_and_a_password(String username, String password) throws Throwable {
        
		lpPO.loginApplication(username, password);
		
    }
	
	 @When("^A user adds a product (.+) to the cart$")
	    public void a_user_adds_a_product_to_the_cart(String productname) throws Throwable {
	        
		  pcPO = new ProductCatalogPOAGAIN(driver);
		 Assert.assertEquals(pcPO.getToastTitleMethod(), "Login Successfully");
		 pcPO.addProductToCart(productname);
		 
	    }
	 
	 @And("^Checkout that this product (.+) is added and submit this order$")
	    public void checkout_that_this_product_is_added_and_submit_this_order(String productname) throws Throwable {
		 
		  mcPO = new MyCartPOAGAIN(driver);
		 
		 Assert.assertTrue(mcPO.isPresentInCart(productname));
			System.out.println(mcPO.isPresentInCart(productname));
			
			System.out.println(mcPO.getItemNumber(productname));
			
			mcPO.clickCheckoutButton();
			
			 poPO = new PlaceOrderPOAGAIN(driver);
			
			String wantedCountryPartial = "yug";
			String wantedCountryFull = "yugoslavia";
			
			poPO.enterCountry(wantedCountryPartial);
			
			poPO.selectCountry(wantedCountryFull);
			
			poPO.clickPlaceOrderButton();
		 
	    }
	 
	 @Then("^Verify the message \"([^\"]*)\" is displayed$")
	    public void verify_the_message_something_is_displayed(String messageTY) throws Throwable {
		 
		  tyPO = new ThankYouPOAGAIN(driver);  
		 Assert.assertEquals(tyPO.getThankYouMessage(), messageTY);
		 
	    }
	 
	 @And("^A browser is closed$")
	    public void a_browser_is_closed() throws Throwable {
	        
		 tearDown();
		 
	    }
	 
	 //For the second .feature file
	 
	 @Given("^A user has logged in with a wrong username (.+) and a password (.+)$")
	    public void a_user_has_logged_in_with_a_wrong_username_and_a_password(String username, String password) throws Throwable {
	        
		 lpPO.loginApplication(username, password);
		 
	    }
	 
	 @When("^A user clciks on the Login button$")
	    public void a_user_clciks_on_the_login_button() throws Throwable {
	        
	    }
	 
	 @Then("^Verify the warning message \"([^\"]*)\" is displayed$")
	    public void verify_the_warning_message_something_is_displayed(String messageIncorrect) throws Throwable {
	        
		 System.out.println(lpPO.incorrectEmailPassword());
			Assert.assertEquals(lpPO.incorrectEmailPassword(), messageIncorrect);
		 
	    }
	 
	 
	 //For the JK feature file
	 
	 @And("^Verify that the order is listed under the Your Order table$")
	    public void verify_that_the_order_is_listed_under_the_your_order_table() throws Throwable {
	        
		//To extract order ID
			System.out.println(tyPO.getOrderId());
			
			//To get this order ID into readable format
			String orderIDremoveFirstAndLast = removeFirstandLastJK(tyPO.getOrderId());
			String orderIDReadableFormat = orderIDremoveFirstAndLast.trim();
			System.out.println(orderIDReadableFormat);
			
			//Go to Your Orders page and verify that the product is listed
			 yoPO =  new YourOrdersPOAGAIN(driver);
			
			yoPO.goToOrdersPage();
			System.out.println(yoPO.getOrderIDInMyOrders(orderIDReadableFormat));
			Assert.assertTrue(yoPO.getOrderIDInMyOrders(orderIDReadableFormat));
	    }
	

}
