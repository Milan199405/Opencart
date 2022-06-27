package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
    private By header = By.cssSelector("div#content h1");
    private By productMeta = By.xpath("(//div[@id = 'content']//ul)[3]/li");
	
//	private WebDriver driver;
	private ElementUtil elUtil;
	
	public ProductInfoPage(WebDriver driver) {
//		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}
	
	public String getProductHeader() {
		return elUtil.getElement(header).getText();
	}
	
	public HashMap<String, String> getProductMetaData() {
		HashMap<String, String> metaDataMap = new LinkedHashMap<String, String>(); 
		List<WebElement> productMetaData = elUtil.getElements(productMeta);
		for(WebElement e: productMetaData) {
			String text = e.getText();
			String splittedData[] = text.split(":");
			String key = splittedData[0];
			String value = splittedData[1];
			metaDataMap.put(key, value);
		}
		return metaDataMap;
	}
}
