package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By accSecHeaders = By.cssSelector("div#content h2");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getAccPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACC_PAGE_TITLE);
		System.out.println("Acc Page title is "+ title);
		return title;
	}
	
	public boolean getAccPageURL() {
		String currentURL = eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACC_PAGE_URL_PARAM);
		System.out.println("Accpage URL is"+ currentURL);
		if(currentURL.contains(AppConstants.ACC_PAGE_URL_PARAM)) {
		return true;
		}
		else
		return false;
	}
	public boolean isLogoutLinkExist() {
		return eleUtil.doEleIsDisplayed(logoutLink);
		
	}
	public boolean isSearchExist() {
		return eleUtil.doEleIsDisplayed(search);
		
	}
	
	public SearchResultsPage performSearch(String productName) {
		
		System.out.println("Product name is: "+productName);
		if(isSearchExist()) {
			eleUtil.doSendKeys(search, productName);
			eleUtil.doClick(searchIcon);
			return new SearchResultsPage(driver);
		}
		else {
			System.out.println("Please enter correct product name");
			return null;
		}
		
		
	}

	public ArrayList<String> getAccSecHeaders() {
		
		List<WebElement> headerList = eleUtil.waitForElementsToBeVisible(accSecHeaders,AppConstants.DEFAULT_LARGE_TIME_OUT);
		ArrayList<String> accPageHeaderList = new ArrayList<String>();
		for(WebElement e : headerList) {
			String text = e.getText();
			accPageHeaderList.add(text);
		}
		
		return accPageHeaderList;
	}
}
