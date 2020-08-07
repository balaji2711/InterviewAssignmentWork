package com.flipkarttests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.basefixture.BaseFixture;
import com.commonmethods.WebUtils;
import com.flipkartpages.HomePage;
import com.flipkartpages.SearchProductPage;
import com.relevantcodes.extentreports.LogStatus;
import com.report.ExtentTestManager;

public class SearchProductTest extends BaseFixture
{
	String search_Data=null;
	String workingDir=System.getProperty("user.dir");
	
	@Test(priority=0)
	public void verifySearchProduct() throws InterruptedException, IOException
	{
		ExtentTestManager.getTest().setDescription("Search Product");		
		ExtentTestManager.getTest().assignCategory("Regression");
		ExtentTestManager.getTest().assignAuthor("Balaji G");
		HomePage homePage=new HomePage(getDriver());
		SearchProductPage searchPage=new SearchProductPage(getDriver());
		
		search_Data=WebUtils.readDataFromPropertiesFile(workingDir+"/src/test/resources/com/DataRepository/HomePage/HomePageData.properties","Username");		
		try
		{
			getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			boolean isElementFound=WebUtils.IsElementDisplayed(getDriver(),homePage.SEARCH_BOX);		
		    if(isElementFound)
		    {	    		    	    	    		    	
		    	WebUtils.ClearInputByXpath(getDriver(), homePage.SEARCH_BOX);
				WebUtils.EnterInputByXpath(getDriver(), homePage.SEARCH_BOX, search_Data);								
				ExtentTestManager.getTest().log(LogStatus.PASS, "Product name entered successfully");
				WebUtils.ClickElementByXpath(getDriver(), searchPage.SEARCH_BUTTON);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Product searched successfully");
				System.out.println("Product searched successfully");
				Thread.sleep(10000);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Product searched successfully");
		    }
		    else
		    {
		    	Assert.fail("Unable to search a product");
		    	ExtentTestManager.getTest().log(LogStatus.FAIL, "Unable to search a product");
		    }	  
		}
		catch(Exception e)
		{			
			Assert.fail("Test case failed due to - "+e.getMessage());
			ExtentTestManager.getTest().log(LogStatus.FAIL, e.getMessage());
		}		  
	 }
	

}
