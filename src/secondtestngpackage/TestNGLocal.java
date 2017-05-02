package secondtestngpackage;
import org.testng.annotations.Test;

import secondtestngpackage.Waiter;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.*;

public class TestNGLocal {
	
	private WebDriver driver;
	String baseURL = "https://www.browserstack.com";
    String expectedTitle = "Dashboard";
    String actualTitle = "";

	@Parameters({ "browser" })
	@BeforeTest
	public void openBrowser(String browser) {
		try {
			if (browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver","/Applications/geckodriver");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"/Applications/chromedriver");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("safari")) {
				System.setProperty("webdriver.safari.driver",
						"/usr/bin/safaridriver");
		    	SafariOptions options = new SafariOptions();
		    	options.setUseCleanSession(true);
		    	driver = new SafariDriver(options);
			}
		
		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void login_TestCase(){
		
        Waiter _waiter = new Waiter(driver);
		
		try{
			//driver.navigate().to(baseURL);
	    	driver.get("https://www.browserstack.com/");
	    	driver.manage().window().maximize();
	    	_waiter.waitForMe(By.linkText("Sign in"), 5);
	    	
	    	//Step 2: Search for Sign In link and click on it
	    	driver.findElement(By.linkText("Sign in")).click(); 
	    	_waiter.waitForMe(By.id("user_email_login"), 5);
	        
	    	//Step 3: Enter the email and password and click on Sign in button
	        driver.findElement(By.id("user_email_login")).sendKeys("email");
	        driver.findElement(By.id("user_password")).sendKeys("password");
	        driver.findElement(By.id("user_submit")).click(); 
	        Thread.sleep(5000);
	        
		    //Step 4: Click on cross icon on the Local installation pop up
	        if(driver.findElements( By.id("skip-local-installation") ).size() != 0)
	        {
	        	driver.findElement(By.id("skip-local-installation")).click(); 
	        	Thread.sleep(5000);
	        }
	        
	        //Step 5: Select the browser you want to open live session for and click on it
		    driver.findElement(By.xpath("//*[@id=\"rf-browsers\"]/div/div[2]/div[4]/ul/li[1]/a")).click(); 
		    _waiter.waitForMe(By.id("dock"), 10);
	        
	        actualTitle = driver.getTitle();
	        
	        assertEquals(actualTitle, expectedTitle);
	        
	        //Step 6: Check whether the live session was successful by checking the title of the Live session page
	        if (actualTitle.contentEquals(expectedTitle)){
	            System.out.println("Test Passed!");
	        } else {
	            System.out.println("Test Failed");
	        }  
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
	
}