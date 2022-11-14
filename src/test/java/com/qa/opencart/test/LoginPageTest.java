package com.qa.opencart.test;



import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.constants.AppConstants;
import com.qa.operncart.base.BaseTest;

public class LoginPageTest extends BaseTest {
	
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actualTitle = loginObject.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void loginPageURLTest() {
		boolean url = loginObject.getLoginPageURL();
		Assert.assertTrue(url);
	}
	
	@Test(priority = 3)
	public void isForgotPasswordExistTest() {
		Assert.assertEquals(loginObject.isForgotPasswordLinkExist(), true);
	}

	@Test(priority = 4)
	public void doLoginTest() {
		loginObject.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
}
