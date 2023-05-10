package Academy.Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="C:\\Users\\joko2909\\SeleniumTraining\\Toma14112022\\src\\test\\java\\Academy\\Cucumber",
					glue="Academy.StepDefinitions", //The package where stepdefinition files are located)
					monochrome=true, //This is for results to be in a readable format
					tags= "@ErrorValid", //For tags
					plugin= {"html:target/cucumber.html"}) //Generate a report into the html plugin
public class TestNGTestRunner extends AbstractTestNGCucumberTests { //Extend with this AbstractTestNGCucumberTests class in order to run the TestNG

}
