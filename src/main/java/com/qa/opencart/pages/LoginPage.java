package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opercart.factory.DriverFactory;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginbtn = By.cssSelector("input.btn-primary");
	private By forgotPassword = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img.img-responsive");
	private static final Logger log = LogManager.getLogger(LoginPage.class);
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE);
		System.out.println("Page title is "+ title);
		//log.info("Login page title is : "+title);
		return title;
	}
	
	public boolean getLoginPageURL() {
		
		String currentURL = eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_PARAM);
		System.out.println("Loginpage URL is"+ currentURL);
		if(currentURL.contains(AppConstants.LOGIN_PAGE_URL_PARAM)) {
		return true;
		}
		else
		return false;
	}
	
	@Step("Checking forgorpassword link availability")
	public boolean isForgotPasswordLinkExist() {
		return eleUtil.doEleIsDisplayed(forgotPassword);
		
	}
	
	@Step("Login with Username:{0} and Password:{1}")
	public AccountsPage doLogin(String userName,String pwd) {
		eleUtil.doSendKeysWithWait(this.username, AppConstants.DEFAULT_LARGE_TIME_OUT, userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginbtn);
		return new AccountsPage(driver);
	}

}
