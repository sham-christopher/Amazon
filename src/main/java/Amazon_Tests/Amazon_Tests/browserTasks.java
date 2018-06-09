package Amazon_Tests.Amazon_Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class browserTasks {
	public void launchBrowser(String Browser, WebDriver driver, String URL) {
		switch(Browser.toLowerCase()){
		case "chrome" : 	System.out.println("Initialising Chrome driver");
		
							//Open Chrome in Incognito mode
							ChromeOptions options = new ChromeOptions();
							options.addArguments("--incognito");
							DesiredCapabilities capabilities = DesiredCapabilities.chrome();
							capabilities.setCapability(ChromeOptions.CAPABILITY, options);
							
							//Navigate to the Website
							System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
							driver = new ChromeDriver(capabilities);
							System.out.println("Navigating to "+URL);
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
							System.out.println("Navigating to "+URL);
							driver.navigate().to(URL);
							driver.manage().window().maximize();
							break;
		}
	}
}
