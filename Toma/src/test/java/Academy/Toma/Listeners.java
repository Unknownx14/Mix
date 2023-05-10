package Academy.Toma;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import resources.ExtentReporterNG;

public class Listeners extends Base implements ITestListener {

	ExtentReporterNG erng = new ExtentReporterNG(); //Object of this class
	ExtentReports extent = erng.getReportObject(); //Call this Method from the ExtentReporterNG
	
	ExtentTest test;
	
	ThreadLocal<ExtentTest> thredLocalExtent = new ThreadLocal<ExtentTest>(); //This is for making everything run smoothly in parallel 01
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName()); //In the Report one entry with a Method name will be created
		thredLocalExtent.set(test); //This is for making everything run smoothly in parallel 02
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		thredLocalExtent.get().log(Status.PASS, "Test has passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		thredLocalExtent.get().fail(result.getThrowable());
		
		WebDriver driver =null; //This is a dummy value for this driver= (WebDriver) result.getTestClass()...
		String testMethodName = result.getMethod().getMethodName();
		
		try {
			driver= (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance()); //This is to get the "driver" variable from an actual TC, since the driver in base.java is not alive
		} catch (Exception e) {
			
		} 
		
		try {
			thredLocalExtent.get().addScreenCaptureFromPath(getScreenshotPath(testMethodName, driver), testMethodName); //For adding a screenshot into the Report, takes 2 arguments - imagePath, title
			//getScreenshotPath(testMethodName, driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
