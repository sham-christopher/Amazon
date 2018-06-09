package stepDefinition;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Amazon_Tests.Amazon_Tests.browserTasks;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;


	
public class Find_Nikon {
	WebDriver driver;
	browserTasks browserTasks = new browserTasks();
	
	@Given("^I navigate to \"(.*)\" using \"(.*)\" browser$")
	public void openBrowserAndNavigateToURL(String URL, String Browser) throws Exception  {
		driver = browserTasks.launchBrowser(Browser, driver, URL);
	}

	@When("^I search for \"(.*)\" in the search box$")
	public void searchBox(String arg1) throws Exception  {
		// Search for Nikon in the search box
		System.out.println("Searching for Nikon");
		driver.findElement(By.id("twotabsearchtextbox")).click(); 
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(arg1);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.RETURN);
	}

	@Then("^I sort by \"(.*)\"$")
	public void sort(String sort) throws Exception  {
		//Make the WebDriver to wait until the items are sorted
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result_1")));
		
		//Sort Descending order
		System.out.println("Sorting the list in Descending order");
		Select sortDropdown = new Select(driver.findElement(By.id("sort"))); 
		sortDropdown.selectByVisibleText(sort); 
	}

	@Then("^I click on the no \"(.*)\" item in the list$")
	public void clickOnWebElement(String arg1) throws Exception  {
		//Make the WebDriver to wait until the items are sorted
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result_1")));
		
		//Click on the second item retrieved by the search
		System.out.println("Clicking on the second item in the list.");
		
		List <WebElement> itemsList = new ArrayList<>();
		itemsList = driver.findElements(By.cssSelector("li[id*='result']"));
		Integer result = Integer.valueOf(arg1);
		result = result -1;
		WebElement item = itemsList.get(result);
		item.findElement(By.cssSelector("a[href*='www.amazon.com']")).click();;
	}

	@And("^I check if the product displayed is \"(.*)\"$")
	public void assertion(String arg1) throws Exception  {
		//Checking if the product is Nikon D3X
		String productTopic = driver.findElement(By.id("productTitle")).getText();
		try {
			Assert.assertTrue(productTopic.contains(arg1));
			System.out.println("Assert passed");
		}
		catch(AssertionError e) {
			driver.quit();
			System.out.println("Assert failed. "+arg1+" not found");
			throw e;
		}
			
	}
}
