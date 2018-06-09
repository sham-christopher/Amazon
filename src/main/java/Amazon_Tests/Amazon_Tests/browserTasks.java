package Amazon_Tests.Amazon_Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;

public class browserTasks {
	public WebDriver launchBrowser(String Browser, WebDriver driver, String URL) {
		switch(Browser.toLowerCase()){
		case "chrome" : 	System.out.println("Initialising Chrome driver");
		
							//Open Chrome in Incognito mode
							ChromeOptions options = new ChromeOptions();
							options.addArguments("--incognito");
							DesiredCapabilities capabilities = DesiredCapabilities.chrome();
							capabilities.setCapability(ChromeOptions.CAPABILITY, options);
							
							//Navigate to the Website
							ChromeDriverManager.getInstance().setup();
							driver = new ChromeDriver(capabilities);
							System.out.println("Driver used is "+ driver);
							System.out.println("Navigating to "+URL);
							driver.navigate().to(URL);
							driver.manage().window().maximize();
							break;
							
		case "firefox":		System.out.println("Initialising Firefor driver");
	
							//Open Firefox in Incognito mode
							FirefoxProfile firefoxProfile = new FirefoxProfile();    
							firefoxProfile.setPreference("browser.privatebrowsing.autostart", true);
					
							//Initialising WebDriver
							FirefoxDriverManager.getInstance().setup();
							driver = new FirefoxDriver();
							System.out.println("Navigating to "+URL);
							driver.navigate().to(URL);
							driver.manage().window().maximize();
							break;
		}
		return (driver);
	}
}
