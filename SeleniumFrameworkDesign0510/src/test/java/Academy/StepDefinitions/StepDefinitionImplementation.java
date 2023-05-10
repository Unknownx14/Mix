package Academy.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Academy.TestComponents.BaseTest;
import Academy.pageObject.LandingPagePO;
import Academy.pageObject.MyCartPO;
import Academy.pageObject.PlaceOrderPO;
import Academy.pageObject.ProductCataloguePO;
import Academy.pageObject.ThankYouPO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest {

	
	public LandingPagePO lpPO;
	public ProductCataloguePO pcPO;
	public MyCartPO mcPO;
	public PlaceOrderPO poPO;
	public ThankYouPO tyPO;
	
	
	@Given("^A user has landed on the Ecommerce landing page$")
	public void a_user_has_landed_on_the_ecommerce_landing_page() throws IOException 
	{
		lpPO = launchApplication();
    }
	
	
	@Given("^A user has logged in with a username (.+) and a password (.+)$")
    public void a_user_has_logged_in_with_a_username_and_a_password(String username, String password) 
	{
		lpPO.LoginApplicationAction(username, password);
    }
	
	
	@When("^A user adds a product (.+) to the cart$")
    public void a_user_adds_a_product_to_the_cart(String productname)
	{
		
		pcPO = new ProductCataloguePO(driver);
		
		List<WebElement> allProducts = pcPO.getProductList();
			
		//This is just for me
		Assert.assertEquals(pcPO.addProductToCart(productname), "Product Added To Cart"); //wantedProductName

    }
	
	
	@And("^Checkout that this product (.+) is added and submit this order$")
    public void checkout_that_this_product_is_added_and_submit_this_order(String productname)
	{
		pcPO.clickCartButtonAbstract(); //This is by Rahul in the Abstract, because it is common for all pages
		//pcPO.clickCartButton(); // I did it in the ProductCataloguePO
				
		//On this page, find the selected product under the Cart
		
		 mcPO = new MyCartPO(driver);
		
		mcPO.getProductInCartByName(productname); //wantedProductName
		mcPO.verifyH3text(productname);//wantedProductName
		Assert.assertTrue(mcPO.verifyH3text(productname)); //wantedProductName
		mcPO.clickCheckoutButton();
			
		//We need the Actions class for this dropdown
		 poPO = new PlaceOrderPO(driver);
		
		String partialTypeInCountry = "indi"; //yug
		String yourCountry = "india"; //yugoslavia
		poPO.chooseCountry(partialTypeInCountry);
		poPO.selectYourCountry(yourCountry);
		poPO.clickPlaceOrderButton();
    }
	
	
	@Then("^Verify the message \"([^\"]*)\" is displayed$")
    public void verify_the_message_something_is_displayed(String strArg1)
	{
				 tyPO = new ThankYouPO(driver);
				
				Assert.assertEquals(tyPO.validateThankYouMessage(), strArg1);
    }
	
	
	@And("^A browser is closed$")
    public void a_browser_is_closed()
	{
		driver.close();
    }
	
	
	//For ErrorValidations.feature file
	@Given("^A user has tried to log in with an incorrect username (.+) and a password (.+)$")
    public void a_user_has_tried_to_log_in_with_an_incorrect_username_and_a_password(String username, String password)
	{
		lpPO.LoginApplicationAction(username, password);
    }

    @When("^A user clicks on the Login button$")
    public void a_user_clicks_on_the_login_button()
    {
        
    }

    @Then("^Verify the message \"([^\"]*)\" is displayed and a user is not logged in$")
    public void verify_the_message_something_is_displayed_and_a_user_is_not_logged_in(String strArg1)
    {
    	System.out.println(lpPO.getErrorMessage());
		Assert.assertEquals(lpPO.getErrorMessage(), strArg1);
    }
	
	
	
	
}
