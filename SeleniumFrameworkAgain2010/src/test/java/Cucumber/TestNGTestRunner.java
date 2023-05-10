package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="C:\\Users\\joko2909\\SeleniumTraining\\SeleniumFrameworkAgain2010\\src\\test\\java\\Cucumber",
					glue="Academy.StepDefinitions", //The package where stepdefinition files are located)
					monochrome=true, //This is for results to be in a readable format
					tags= "@JK", //For tags
					plugin= {"html:target/cucumber.html"}) //Generate a report into the html plugin
public class TestNGTestRunner extends AbstractTestNGCucumberTests { //Extend with this AbstractTestNGCucumberTests class in order to run the TestNG

}
