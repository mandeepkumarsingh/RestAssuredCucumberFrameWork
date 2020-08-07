package Cucumber.Options;



import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
/**
 * @author mandeep
 * 
 * */

@RunWith(Cucumber.class)
@CucumberOptions(features ="./src/test/java/Features/",
glue={"stepDefinitions"},
plugin="json:target/JsonReports/cucumber-report.json",
tags= {"@Test"},
dryRun=false)
public class TestRunner {
	
	

}
