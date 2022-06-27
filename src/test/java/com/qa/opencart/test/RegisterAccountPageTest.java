package com.qa.opencart.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterAccountPageTest extends BaseTest {

	@BeforeClass
	public void preSetup() {
		registerAccountPage = loginPage.goToRegisterPage();
	}

	public String getRandomEmail() {
		Random randomGenerator = new Random();
		String email = "Rahul" + randomGenerator.nextInt(10000) + "kumar" + "@gmail.com";
        return email;
	}

	@DataProvider
	public Object[][] getTestData() {
		Object testData[][] = ExcelUtil.getExcelData(Constants.REGISTER_PAGE_SHEET_NAME);
		return testData;
	}

	@Test(dataProvider = "getTestData")
	public void registerAccountTest(String fn, String ln,String phone, String pwd, String subscribe) {
		String successMsg = registerAccountPage.registerAccount(fn, ln, getRandomEmail(), phone, pwd, pwd, subscribe);
		Assert.assertTrue(successMsg.equals(Constants.ACCOUNT_CREATION_SUCCESS_MSG));
	}
}
