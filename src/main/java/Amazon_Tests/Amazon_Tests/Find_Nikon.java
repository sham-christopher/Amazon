package Amazon_Tests.Amazon_Tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;


	
public class Find_Nikon {
	WebDriver driver;
	
	@Given("^I navigate to \"([^\"]*)\" using \"([^\"]*)\" browser$")
	public void navigateToURL(String URL, String Browser) throws Exception  {
		switch(Browser.toLowerCase()){
			case "chrome" : 	System.out.println("Initialising Chrome driver");
			
								//Open Chrome in Incognito mode
								ChromeOptions options = new ChromeOptions();
								options.addArguments("--incognito");
								DesiredCapabilities capabilities = DesiredCapabilities.chrome();
								capabilities.setCapability(ChromeOptions.CAPABILITY, options);
								
								//Navigate to the Website
								System.out.println("Going to "+URL);
								System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
								driver = new ChromeDriver(capabilities);
								driver.navigate().to(URL);
								driver.manage().window().maximize();
								break;
								
			case "firefox":		System.out.println("Initialising Firefor driver");
		
								//Open Firefox in Incognito mode
								FirefoxProfile firefoxProfile = new FirefoxProfile();    
								firefoxProfile.setPreference("browser.privatebrowsing.autostart", true);
						
								//Initialising WebDriver
								System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\geckodriver-v0.20.1-win64\\geckodriver.exe");
								driver = new FirefoxDriver();
								driver.navigate().to(URL);
								driver.manage().window().maximize();
								break;
			}
	}

	@When("^I search for \"([^\"]*)\" in the search box$")
	public void searchBox(String arg1) throws Exception  {
		// Search for Nikon in the search box
		System.out.println("Searching for Nikon");
		driver.findElement(By.id("twotabsearchtextbox")).click(); 
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Nikon");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.RETURN);
	}

	@Then("^I sort by \"([^\"]*)\"$")
	public void sort(String arg1) throws Exception  {
		//Make the WebDriver to wait until the items are sorted
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result_1")));
		
		//Sort Descending order
		System.out.println("Sorting the list in Descending order");
		Select sortDropdown = new Select(driver.findElement(By.id("sort"))); 
		sortDropdown.selectByValue("price-desc-rank");
	}

	@Then("^I click on the \"([^\"]*)\" item in the list$")
	public void clickOnWebElement(String arg1) throws Exception  {
		//Make the WebDriver to wait until the items are sorted
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result_1")));
		
		//Click on the second item retrieved by the search
		System.out.println("Clicking on the second item in the list");
		WebElement searchItem = driver.findElement(By.id("result_1"));
		WebElement item = searchItem.findElement(By.cssSelector("a[href*='www.amazon.com']"));
		item.click();
	}

	@And("^I check if the product displayed is \"([^\"]*)\"$")
	public void assertion(String arg1) throws Exception  {
		//Check the product topic
		//Checking if the product is Nikon D3X
		String productTopic = driver.findElement(By.id("productTitle")).getText();
		Assert.assertTrue(productTopic.contains("Nikon D3X"));		
	}
}
