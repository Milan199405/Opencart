package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
  
	private By contentHeader = By.cssSelector("div#content h2");
	private By searchField = By.cssSelector("div#search input");
	private By searchButton = By.cssSelector("div#search button");
	
	private WebDriver driver;
	private ElementUtil elUtil;
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}
	
	public String getAccountPageTitle() {
		return elUtil.doGetTitle();
	}
	
	public 	List<String> getAccountPageContentHeader() {
		List<String> actualContentHeader = new ArrayList<String>();
		List<WebElement> header = elUtil.getElements(contentHeader);
		for(WebElement e: header) {
			String text = e.getText();
			actualContentHeader.add(text);
		}
		
		return actualContentHeader;
	}
	
	public ProductResultsPage searchProduct(String productName) {
		elUtil.doSendKeys(searchField, productName);
		elUtil.doClick(searchButton);
		return new ProductResultsPage(driver);
	}
}
