package com.flipkartpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchProductPage
{
	@SuppressWarnings("unused")
	private WebDriver driver;
	public SearchProductPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='vh79eN']")
	public WebElement SEARCH_BUTTON;
	
	@FindBy(xpath="//*[@class='hGSR34 _2beYZw']")
	public WebElement PRODUCT_REVIEW;	
	
	@FindBy(xpath="//*[@class='_1vC4OE _3qQ9m1']")
	public WebElement SHOE_PRICE;
	
	@FindBy(xpath="//a[text()='6']")
	public WebElement SHOE_SIZE;	
	
	@FindBy(xpath="//*[text()='BUY NOW']")
	public WebElement BUY_NOW;	
	
	@FindBy(xpath="//*[text()='ADD TO CART']")
	public WebElement ADD_TO_CART;
	
	@FindBy(xpath="//*[text()='Place Order']")
	public WebElement PLACE_ORDER;
	
	@FindBy(xpath="//*[text()='Remove']")
	public WebElement REMOVE_PRODUCT;
	
	@FindBy(xpath="//*[text()='Your Shopping Cart is empty']")
	public WebElement EMPTY_CART_MSG;
	
	
	
}
