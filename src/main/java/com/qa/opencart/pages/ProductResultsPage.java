package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductResultsPage {

	private By productsImg = By.cssSelector("div.caption a");

	private WebDriver driver;
	private ElementUtil elUtil;

	public ProductResultsPage(WebDriver driver) {
		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}

	public int productsCount() {
		int count = elUtil.getElements(productsImg).size();
		System.out.println("Product count " + count);
		return count;
	}

	public ProductInfoPage getProductInfo(String product) {
		List<WebElement> products = elUtil.getElements(productsImg);
		for (WebElement e : products) {
			if (e.getText().equals(product)) {
				System.out.println(e.getText());
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
}
