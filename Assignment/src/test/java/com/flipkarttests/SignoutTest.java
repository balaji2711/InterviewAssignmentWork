package com.flipkarttests;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.basefixture.BaseFixture;
import com.commonmethods.WebUtils;
import com.flipkartpages.SignoutPage;
import com.relevantcodes.extentreports.LogStatus;
import com.report.ExtentTestManager;

public class SignoutTest extends BaseFixture
{
	@Test(priority=2)
	public void verifyLogoutFromApplication() throws InterruptedException, AWTException
	{
		Thread.sleep(5000);
		ExtentTestManager.getTest().setDescription("Verify the user is able to logout from the application or not");
		ExtentTestManager.getTest().assignCategory("Regression");
		ExtentTestManager.getTest().assignAuthor("Balaji G");
		SignoutPage signoutPage=new SignoutPage(getDriver());
		try
		{
			Thread.sleep(5000);
		    WebUtils.keyPressPageDown();
		    WebUtils.keyPressPageDown();
		    getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    boolean isElementPresent=WebUtils.IsElementDisplayed(getDriver(),signoutPage.SIGNOUT_BUTTON);
		    if(isElementPresent)
		    {
		    	WebUtils.ClickElementByXpath(getDriver(),signoutPage.SIGNOUT_BUTTON);
			    ExtentTestManager.getTest().log(LogStatus.PASS, "Signed out successfully");
			    System.out.println("Signed out successfully");
			    Thread.sleep(5000);
		    }
		    else
		    {	
		    	Assert.fail("Sign out button is not available");
			    ExtentTestManager.getTest().log(LogStatus.FAIL, "Sign out button is not available");
			}		    		
		}
		catch(Exception e)
		{			
			Assert.fail("Test case failed due to - "+e.getMessage());
			ExtentTestManager.getTest().log(LogStatus.FAIL, e.getMessage());
		}		   
	}

}
