package Academy.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	
	
	public static ExtentReports getReportObject() //It is static so we do not have to create an object of this class
	{
		
		String path = System.getProperty("user.dir")+ "\\reports\\index.html";
		ExtentSparkReporter esReporter = new ExtentSparkReporter(path);
		esReporter.config().setReportName("Web Automation Results JK");
		esReporter.config().setDocumentTitle("Test Results JK");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(esReporter);
		extent.setSystemInfo("QA", "Jovan Kovacevic");
		
		return extent;
		
	}

}
