package com.commonmethods;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import junit.framework.Assert;

public class WebUtils
{	
	static String workingDir=System.getProperty("user.dir");
	public static void ClickElementByXpath(WebDriver driver,WebElement element)
    {
        try
        {
            element.click();
        }        
        catch (NoSuchElementException e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());
        }        
        catch (WebDriverException e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());	
        }
        catch(Exception e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());	
        }
    }
	
	public static boolean closeAllOtherWindows(WebDriver driver, String openWindowHandle)
	{
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles)
		{
			if (!currentWindowHandle.equals(openWindowHandle))
			{
				driver.switchTo().window(currentWindowHandle);
				driver.close();
			}
		}		
		driver.switchTo().window(openWindowHandle);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}
	
	public static void waitForNewWindowAndSwitchToIt(WebDriver driver) throws InterruptedException
	{
		String cHandle = driver.getWindowHandle();
	    String newWindowHandle = null;
	    Set<String> allWindowHandles = driver.getWindowHandles();
	    for(int i = 0; i < 20; i++)
	    {
	    	if(allWindowHandles.size() > 1)
	    	{
	    		for(String allHandlers : allWindowHandles)
	    		{
	    			if (!allHandlers.equals(cHandle))
	    				newWindowHandle = allHandlers;
	            }
	    		driver.switchTo().window(newWindowHandle);
	            break;
	        }
	    	else
	    	{
	    		Thread.sleep(1000);
	        }
	    }
	    if(cHandle == newWindowHandle)
	    {
	    	throw new RuntimeException("Time out - No window found");
	    }
	}
	
	public static void WebDriverWait(WebDriver driver, WebElement element)
	{
		org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	 
	public static String getCurrentWindowTitle(WebDriver driver)
	{
		 String windowTitle = driver.getTitle();
		return windowTitle;
	}
	 
	public static String getMainWindowHandle(WebDriver driver)
	{
		return driver.getWindowHandle();
	}
	 
	public static void keyPressPageDown() throws AWTException
	{
		Robot robot=new Robot();
	    robot.keyPress(KeyEvent.VK_PAGE_DOWN);
	    robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}
	
	public static void keyPressPageUp() throws AWTException
	{
	    Robot robot=new Robot();
	    robot.keyPress(KeyEvent.VK_PAGE_UP);
	    robot.keyRelease(KeyEvent.VK_PAGE_UP);
	}
	
	public static void keyPressTab() throws AWTException
	{
		Robot robot=new Robot();
	    robot.keyPress(KeyEvent.VK_TAB);
	    robot.keyRelease(KeyEvent.VK_TAB); 
	}
	
	public static void keyPressEnter() throws AWTException
	{
		Robot robot=new Robot();
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER); 
	}
	
    public static void ClearInputByXpath(WebDriver driver,WebElement element)
    {
        try
        {
            element.clear();
        }                   
        catch (NoSuchElementException e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());	
        }        
        catch (WebDriverException e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());	
        }
        catch(Exception e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());	
        }
    }
    
    public static String GetElementText(WebDriver driver,WebElement element)
    {
    	String text=null;
        try
        {
            text=element.getText();
            return text;
            
        }                   
        catch (NoSuchElementException e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());
        }        
        catch (WebDriverException e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());	
        }
        catch(Exception e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());	
        }
		return text;
    }
    
    public static void EnterInputByXpath(WebDriver driver,WebElement element,String value)
    {
        try
        {
            element.sendKeys(value);
        }                  
        catch (NoSuchElementException e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());	
        }        
        catch (WebDriverException e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());	
        }
        catch(Exception e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());	
        }
    }
    
    public static void MoveToElement(WebDriver driver,WebElement element)
    {
        try
        {
        	Actions builder = new Actions(driver);
        	Action mouseOverTitle=builder.moveToElement(element).build();
        	mouseOverTitle.perform();
        }                  
        catch (NoSuchElementException e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());	
        }        
        catch (WebDriverException e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());	
        }
        catch(Exception e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());	
        }
    }
    
    public static void EnterthroughKeyboardFunction(WebDriver driver, WebElement element)
    {
    	try
        {
            element.sendKeys(Keys.ENTER);
        }                  
        catch (NoSuchElementException e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());	
        }        
        catch (WebDriverException e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());	
        }
    	catch(Exception e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());	
        }
	}
    
    public static boolean IsElementDisplayed(WebDriver driver,WebElement element)
    {
    	boolean flag=false;
        try
        {
        	flag=element.isDisplayed();
            return flag;
        }       
        catch (NoSuchElementException e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());	
        }        
        catch (WebDriverException e)
        {
        	Assert.fail(e.getMessage());
        	System.out.println(e.getMessage());	
        }
        catch(Exception e)
        {
        	Assert.fail(e.getMessage()); 
        	System.out.println(e.getMessage());	
        }
		return flag;
    }       
        
    public static String captureScreenshot(WebDriver driver, String screenShotName)
    {
    	try
    	{
    		TakesScreenshot ts=(TakesScreenshot)driver;
    		File source=ts.getScreenshotAs(OutputType.FILE);
    		String dest=workingDir+"/Screenshots/"+screenShotName+".png";
    		File destination=new File(dest);
    		FileUtils.copyFile(source,destination);
    		System.out.println("Screenshot Taken");
    		return dest;
    	}
    	catch(Exception e)
    	{
    		Assert.fail(e.getMessage()); 
    		System.out.println("Exception while taking screenshot"+e.getMessage());
    		return e.getMessage();    		
    	}
    }
	public static String readDataFromPropertiesFile(String fileLocation,String propertyName) throws IOException
	{
		String returnPropertyValue=null;
		File file = new File(fileLocation);
		FileInputStream fileInput = null;
		try
		{
			fileInput = new FileInputStream(file);
		}
		catch(FileNotFoundException e)
		{
			Assert.fail(e.getMessage());
			e.printStackTrace();
			System.out.println(e.getMessage());	
		}
		Properties prop = new Properties();
		prop.load(fileInput);				
		returnPropertyValue=prop.getProperty(propertyName);	
		return returnPropertyValue;
	}		
}
