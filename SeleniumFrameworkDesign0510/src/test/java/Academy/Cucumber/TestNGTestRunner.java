package Academy.Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="C:\\Users\\joko2909\\SeleniumTraining\\SeleniumFrameworkDesign0510\\src\\test\\java\\Academy\\Cucumber", 
		//features="src/test/java/Academy.Cucumber/StandAlone184.feature", //All feature files in this package are to be run, src/test/java/features/Login.feature for a single file
				glue="Academy.StepDefinitions", //The package where stepdefinition files are located
				monochrome=true, //This is for results to be in a readable format
				tags= "@ErrorValidations", //For tags
				plugin= {"html:target/cucumber.html"}) //Generate a report into the html plugin

public class TestNGTestRunner extends AbstractTestNGCucumberTests { //Extend with this AbstractTestNGCucumberTests class in order to run the TestNG

	
	
}
