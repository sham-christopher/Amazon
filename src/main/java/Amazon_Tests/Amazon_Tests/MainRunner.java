package Amazon_Tests.Amazon_Tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(
		features = "Features",
		glue = {"Amazon_Tests.Amazon_Tests"}
		)
public class MainRunner {

}
