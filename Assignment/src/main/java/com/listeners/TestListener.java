package com.listeners;

import com.basefixture.BaseFixture;
import com.relevantcodes.extentreports.LogStatus;
import com.report.ExtentManager;
import com.report.ExtentTestManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener
{	
	public void onTestSuccess(ITestResult iTestResult)
    {        
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
    }
    
    public void onTestFailure(ITestResult iTestResult)
    {        
    	ExtentTestManager.getTest().log(LogStatus.FAIL, "Test case failed due to - "+iTestResult.getThrowable().getMessage());
    	Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseFixture) testClass).getDriver();       
        String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)webDriver).
               getScreenshotAs(OutputType.BASE64);
        ExtentTestManager.getTest().log(LogStatus.FAIL,"Test Failed",
        ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
    }
	
    public void onTestSkipped(ITestResult iTestResult)
    {             
        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped - " +iTestResult.getThrowable().getMessage());
    }
	
    public void onTestStart(ITestResult iTestResult)
    {        
        ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(),"");
    }
	
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult)
    {}
	
    public void onStart(ITestContext iTestContext)
    {}

    public void onFinish(ITestContext iTestContext)
    {        
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }
}
