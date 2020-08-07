package com.flipkartpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignoutPage
{
	@SuppressWarnings("unused")
	private WebDriver driver;
	public SignoutPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[text()='Sign Out']")
	public WebElement SIGNOUT_BUTTON;

}
