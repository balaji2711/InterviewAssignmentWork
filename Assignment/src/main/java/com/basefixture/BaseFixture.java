package com.basefixture;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.report.SendMailSSLWithAttachment;

public class BaseFixture
{
	public WebDriver driver;
	String workingDir=System.getProperty("user.dir");
	
	public WebDriver getDriver()
	{
        return driver;
    }

    @BeforeClass
    @Parameters({"browser"})
    public void setup(String browser)
    {
    	if(browser.equalsIgnoreCase("Firefox"))
    	{
    		System.setProperty("webdriver.gecko.driver", workingDir+"\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		}
    	else if(browser.equalsIgnoreCase("Chrome"))
		{ 
			System.setProperty("webdriver.chrome.driver", workingDir+"\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		}
		System.out.println("Launching Browser");
		driver.get("https://www.flipkart.com/");
    }

    @AfterClass
    public void teardown()
    {
        driver.quit();
    }
    
    @AfterSuite
	public void afterSuite()
	{
		SendMailSSLWithAttachment.mailTrigger();
	}
}
