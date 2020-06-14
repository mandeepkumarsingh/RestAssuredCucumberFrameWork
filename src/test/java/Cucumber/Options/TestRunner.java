package Cucumber.Options;



import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features ="/Users/rishi/Documents/myWorkSpace/RestFrameWork/src/test/java/Features/",
glue={"stepDefinitions"},
plugin="json:target/JsonReports/cucumber-report.json",
dryRun=false)
public class TestRunner {
	
	

}
