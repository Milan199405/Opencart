package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void accountPageSetup() {
		accountPage = loginPage.doLogin(props.getProperty("email"), props.getProperty("password"));
	}

	@Test()
	public void productHeaderTest() {
		productsResultPage = accountPage.searchProduct("Macbook");
		productInfoPage = productsResultPage.getProductInfo("MacBook Pro");
		String header = productInfoPage.getProductHeader();
		Assert.assertEquals(header, Constants.PRODUCT_HEADER[0]);
	}

	@Test()
	public void productMetaDataTest() {
		productsResultPage = accountPage.searchProduct("Macbook");
		productInfoPage = productsResultPage.getProductInfo("MacBook Pro");
		Map<String, String> productMetaInfo = productInfoPage.getProductMetaData();
		productMetaInfo.forEach((k, v) -> System.out.println(k + ":" + v));
		
        softAssert.assertEquals(productMetaInfo.get("Brand"), " Apple");
		softAssert.assertEquals(productMetaInfo.get("Product Code"), " Product 18");
		softAssert.assertEquals(productMetaInfo.get("Reward Points"), " 800");
		softAssert.assertEquals(productMetaInfo.get("Availability"), " In Stock");
		softAssert.assertAll();

	}
}
