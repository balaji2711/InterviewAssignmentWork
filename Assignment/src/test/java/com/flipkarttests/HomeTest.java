package com.flipkarttests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.basefixture.BaseFixture;
import com.commonmethods.WebUtils;
import com.flipkartpages.HomePage;
import com.flipkartpages.SearchProductPage;
import com.relevantcodes.extentreports.LogStatus;
import com.report.ExtentTestManager;

public class HomeTest extends BaseFixture
{ 		
	String userName_Data=null;String password_Data=null;
	String search_Data=null;
	String workingDir=System.getProperty("user.dir");		
	
	@Test(priority=0)
	public void verifyFlipKartHomePage() throws IOException
	{	
		ExtentTestManager.getTest().setDescription("Verify the Home Page is successfully loaded or not");
		ExtentTestManager.getTest().assignCategory("Regression");
		ExtentTestManager.getTest().assignAuthor("Balaji G");
		HomePage homePage=new HomePage(getDriver());		
						
		try
		{			
			getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			boolean isElementFound=WebUtils.IsElementDisplayed(getDriver(),homePage.SEARCH_BOX);		
		    if(isElementFound)
		    {
		    	ExtentTestManager.getTest().log(LogStatus.PASS, "Search text box is available in the Home Page");	    		    	
		    	System.out.println("Flipkart home page is loaded successfully");	    	
		    	ExtentTestManager.getTest().log(LogStatus.PASS, "Flipkart home page is loaded successfully");
		    }
		    else
		    {		
		    	Assert.fail("Flipkart home page is not loaded successfully");
		    	ExtentTestManager.getTest().log(LogStatus.FAIL, "Flipkart home page is not loaded successfully");
		    }	 
		}
		catch(Exception e)
		{			
			Assert.fail("Test case failed due to - "+e.getMessage());
			ExtentTestManager.getTest().log(LogStatus.FAIL, e.getMessage());
		}		  
	 }
	
	@Test(priority=1)
	public void verifyLoginWithValidCredentials() throws InterruptedException, IOException
	{
		ExtentTestManager.getTest().setDescription("Login with valid credentials - Flipkart");		
		ExtentTestManager.getTest().assignCategory("Regression");
		ExtentTestManager.getTest().assignAuthor("Balaji G");
		HomePage homePage=new HomePage(getDriver());		
		
		userName_Data=WebUtils.readDataFromPropertiesFile(workingDir+"/src/test/resources/com/DataRepository/HomePage/HomePageData.properties","Username");
		password_Data=WebUtils.readDataFromPropertiesFile(workingDir+"/src/test/resources/com/DataRepository/HomePage/HomePageData.properties","Password");
		try
		{
			getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			boolean isElementFound=WebUtils.IsElementDisplayed(getDriver(),homePage.SEARCH_BOX);		
		    if(isElementFound)
		    {	    		    	    	    		    	
		    	WebUtils.ClearInputByXpath(getDriver(), homePage.ENTER_USERNAME);
				WebUtils.EnterInputByXpath(getDriver(), homePage.ENTER_USERNAME, userName_Data);
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "Username entered successfully");
				WebUtils.ClearInputByXpath(getDriver(), homePage.ENTER_PASSWORD);
				WebUtils.EnterInputByXpath(getDriver(), homePage.ENTER_PASSWORD, password_Data);
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "Password entered successfully");
				WebUtils.ClickElementByXpath(getDriver(), homePage.LOGIN_BUTTON);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Login button clicked successfully");
				System.out.println("Login successful - Flipkart");
				Thread.sleep(5000);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Login successful - Flipkart");
		    }
		    else
		    {
		    	Assert.fail("Unable to login - Flipkart Application");
		    	ExtentTestManager.getTest().log(LogStatus.FAIL, "Unable to login - Flipkart Application");
		    }	  
		}
		catch(Exception e)
		{			
			Assert.fail("Test case failed due to - "+e.getMessage());
			ExtentTestManager.getTest().log(LogStatus.FAIL, e.getMessage());
		}		  
	 }
	
	@Test(priority=2)
	public void verifySearchProduct() throws InterruptedException, IOException
	{
		ExtentTestManager.getTest().setDescription("Search Product");		
		ExtentTestManager.getTest().assignCategory("Regression");
		ExtentTestManager.getTest().assignAuthor("Balaji G");
		HomePage homePage=new HomePage(getDriver());
		SearchProductPage searchPage=new SearchProductPage(getDriver());
		
		search_Data=WebUtils.readDataFromPropertiesFile(workingDir+"/src/test/resources/com/DataRepository/HomePage/SearchProductData.properties","SearchProduct");		
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
				System.out.println("Product searched successfully");
				Thread.sleep(5000);
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
	
	@Test(priority=3)
	public void verifyAddMostReviewedProductToCart() throws InterruptedException, IOException, AWTException
	{		
		ExtentTestManager.getTest().setDescription("Add the shoe which has top rating, Add the shoe which has the size as lowest against the others, Compare and add to the cart");		
		ExtentTestManager.getTest().assignCategory("Regression");
		ExtentTestManager.getTest().assignAuthor("Balaji G");		
		List<WebElement> reviewRatings = driver.findElements(By.xpath("//*[@class='hGSR34 _2beYZw']"));
		List<String> formattedRatingsValue= new ArrayList<>();		
		String mainHandle = getDriver().getWindowHandle();		
		SearchProductPage searchPage=new SearchProductPage(getDriver());
		float reviewValue = 0f;
		int index=0;
		for(int i=0;i<reviewRatings.size();i++)
		{
			String actualValue=reviewRatings.get(i).getText();
			//To remove '*' from String actualValue 
			actualValue=actualValue.substring(0,actualValue.length()-2);		
			formattedRatingsValue.add(actualValue);			
		}
		
		for(int j=0;j<formattedRatingsValue.size();j++)
		{				
			if(Float.valueOf(formattedRatingsValue.get(j)) >= 4.0)
			{
				reviewValue = Float.valueOf(formattedRatingsValue.get(j));
				index=j;
				break;
		    }			
		}
		System.out.println("The first shoe on sale with at least a 4 star rating:"+reviewValue);
		System.out.println("The index:"+index);
		ExtentTestManager.getTest().log(LogStatus.PASS, "The shoe which has the most ratings: "+reviewValue);
		index=index+1;
		ExtentTestManager.getTest().log(LogStatus.PASS, "The index is: "+index);
		reviewRatings.get(index).findElement(By.xpath("(//*[@class='_2cLu-l'])"+"["+index+"]")).click();
		String shoePriceFromSearchPage=getDriver().findElement(By.xpath("(//*[@class='_1vC4OE'])"+"["+index+"]")).getText();
		WebUtils.waitForNewWindowAndSwitchToIt(getDriver());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully switched back to child window");		
		Thread.sleep(5000);		
		List<WebElement> shoeSize = driver.findElements(By.xpath("//*[@class='_2_26Ng _5FnwXU']"));
		List<Integer> formattedShoeSizeValue= new ArrayList<>();
		
		for(int i=0;i<shoeSize.size();i++)
		{				
			Integer value=Integer.valueOf(shoeSize.get(i).getText());
			formattedShoeSizeValue.add(value);			
		}
		Collections.sort(formattedShoeSizeValue);
		System.out.println(formattedShoeSizeValue);
		int smallestShoeValue=formattedShoeSizeValue.get(0);
		Thread.sleep(5000);		
		getDriver().findElement(By.xpath("//a[text()='"+smallestShoeValue+"']")).click();
		System.out.println("Added the smallest available size to shopping cart:"+smallestShoeValue);
		WebUtils.keyPressPageDown();
		WebUtils.WebDriverWait(getDriver(), searchPage.ADD_TO_CART);
		WebUtils.ClickElementByXpath(getDriver(), searchPage.ADD_TO_CART);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Product successfully added to the cart");		
		Assert.assertEquals(WebUtils.GetElementText(getDriver(), searchPage.PLACE_ORDER), "PLACE ORDER","Unable to add the product to the cart");
		String shoePriceFromCartPage=getDriver().findElement(By.xpath("//*[@class='pMSy0p XU9vZa']")).getText();
		Assert.assertEquals(shoePriceFromCartPage, shoePriceFromSearchPage,"The price is not matching between search page and cart page");
		WebUtils.ClickElementByXpath(getDriver(), searchPage.REMOVE_PRODUCT);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Product successfully removed from the cart");
		WebUtils.WebDriverWait(getDriver(), searchPage.EMPTY_CART_MSG);
		Assert.assertEquals(WebUtils.GetElementText(getDriver(), searchPage.EMPTY_CART_MSG), "Your Shopping Cart is empty","Unable to remove the product from the cart");
		getDriver().switchTo().window(mainHandle);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully switched back to parent window");		
	}
}