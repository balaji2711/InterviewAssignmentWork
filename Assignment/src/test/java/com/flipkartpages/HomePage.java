package com.flipkartpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage
{
	@SuppressWarnings("unused")
	private WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@title='Search for products, brands and more']")
	public WebElement SEARCH_BOX;
	
	@FindBy(xpath="//*[@class='_2zrpKA']")
	public WebElement ENTER_USERNAME;
	
	@FindBy(xpath="//*[@class='_2zrpKA _3v41xv']")
	public WebElement ENTER_PASSWORD;
	
	@FindBy(xpath="//*[@class='_2AkmmA _1LctnI _7UHT_c']")
	public WebElement LOGIN_BUTTON;
}

