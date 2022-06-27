package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100:Opencart login page test")
@Story("OC 101: Opencart login page test with multiple features")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {

	@Description("Testing for login page title")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void titleTest() {
		
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void urlTest() {
		
		String url = loginPage.getLoginPageUrl();
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Test
	public void registerLinkTest() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	
	@Test(priority = 1)
	public void loginTest() {
		loginPage.doLogin(props.getProperty("email"),props.getProperty("password"));
	}
}
