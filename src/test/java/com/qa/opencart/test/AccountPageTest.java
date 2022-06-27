package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.Constants;

@Listeners(TestAllureListener.class)
public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accountPageSetup() {
		accountPage = loginPage.doLogin(props.getProperty("email"), props.getProperty("password"));
	}

	@Test
	public void accountPageTitleTest() {
		String actualTitle = accountPage.getAccountPageTitle();
		Assert.assertEquals(actualTitle, Constants.ACCOUNT_PAGE_TITLE);
	}

	@Test
	public void accountPageContentHeaderestTest() {
		List<String> actualContentHeader = accountPage.getAccountPageContentHeader();
		Assert.assertEquals(actualContentHeader, Constants.expectedAccountPageContentHeader());
	}

	@DataProvider
	public Object[][] getProducts() {
		return new Object[][] { { "Macbook" }, { "Apple" }, { "Samsung" } };
	}

	@Test(dataProvider = "getProducts")
	public void searchProductTest(String product) {
		accountPage.searchProduct(product);
	}

	@Test(dataProvider = "getProducts")
	public void productsResultCountTest(String product) {
		productsResultPage = accountPage.searchProduct(product);
		Assert.assertTrue(productsResultPage.productsCount() > 0);
	}

	@DataProvider
	public Object[][] getProductsSelectData() {
		return new Object[][] { { "Macbook", "MacBook Pro" }, { "Apple", "Apple Cinema 30\"" },
				{ "Samsung", "Samsung SyncMaster 941BW" } };
	}

	@Test(dataProvider = "getProductsSelectData")
	public void productInfoTest(String product, String mainProduct) {
		productsResultPage = accountPage.searchProduct(product);
		productsResultPage.getProductInfo(mainProduct);
	}

	
}
