import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse24 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(payload.coursePrice()); //Getting this response from a dummy response in the payload.java
		
		
		//1.Print No of courses returned by API
		int numberCourses = js.getInt("courses.size()");
		System.out.println(numberCourses);
		
		
		//2.Print Purchase Amount
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		
		//3.Print Title of the first course
		String courseTitle = js.getString("courses[2].title");
		System.out.println(courseTitle);
		
		
		//4.Print All courses titles and their respective Prices
		int allCourses = js.getInt("courses.size()");
		for(int i=0; i<allCourses; i++)
		{
			String singleTitle = js.getString("courses["+i+"].title");
			int singlePrice = js.getInt("courses["+i+"].price");
			System.out.println(singleTitle);
			System.out.println(singlePrice);
		}
		
		
		//5.Print no of copies sold by RPA Course
		String wantedCourse = "RPA";
		int allCourses02 = js.getInt("courses.size()");
		for(int i=0; i<allCourses02; i++)
		{
			String singleTitle = js.getString("courses["+i+"].title");
				if(singleTitle.equals(wantedCourse))
				{
					System.out.println("Assignment 5. - " +singleTitle);
					int singleCopies = js.getInt("courses["+i+"].copies");
					System.out.println("Assignment 5. - " +singleCopies);
					break;
				}

		}
		
		
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
