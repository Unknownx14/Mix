import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void sumValidation()
	{
		JsonPath js = new JsonPath(payload.coursePrice()); //Getting this response from a dummy response in the payload.java
		
		//6.Verify if Sum of all Courses prices matches with Purchase Amount
				int purchaseAmount03 = js.getInt("dashboard.purchaseAmount");
				System.out.println(purchaseAmount03);
				
				int sumOfAllPucrhases = 0;
				int allCourses03 = js.getInt("courses.size()");
				for(int i=0; i<allCourses03; i++)
				{
					int singlePrice = js.getInt("courses["+i+"].price");
					int singleCopies = js.getInt("courses["+i+"].copies");
					int singlePurchaseAmount = singlePrice*singleCopies;
					sumOfAllPucrhases = sumOfAllPucrhases + singlePurchaseAmount;

				}
				System.out.println(sumOfAllPucrhases);
				Assert.assertEquals(purchaseAmount03, sumOfAllPucrhases);
		
	}
	
}
