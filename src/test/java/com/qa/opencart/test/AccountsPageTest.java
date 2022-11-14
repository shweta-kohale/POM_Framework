package com.qa.opencart.test;

import java.util.ArrayList;
import java.util.Collections;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.constants.AppConstants;
import com.qa.operncart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic: 100: Opencart Account page")
@Story("UserStory: 101: Account Page Test")
public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void accSetUp() {
		
		accPage = loginObject.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Description("Logout link availability check")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void LogoutLinkTest() {
		Assert.assertEquals(accPage.isLogoutLinkExist(), true);
	}
	
	@Description("Search check")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void SearchTest() {
		Assert.assertEquals(accPage.isSearchExist(), true);
	}
	
	@Description("Account Header list check")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 3)
	public void AccHeaderListTest() {
		ArrayList<String> accHeaderList = accPage.getAccSecHeaders();
		Collections.sort(accHeaderList);
		System.out.println("Account age header List"+accHeaderList);
		Assert.assertEquals(accHeaderList, AppConstants.ACC_PAGE_SEC_HEADERS);
	}
	
	@Description("Account page title check")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 4)
	public void accPageTitle() {
		String accPageTitle = accPage.getAccPageTitle();
		Assert.assertEquals(accPageTitle, AppConstants.ACC_PAGE_TITLE);
	}
	
	@DataProvider
	public Object[][] getSearchKey() {
		return new Object[][]  {
			
			{"MacBook"},
			{"iMac"},
			{"Macbook air"}
		};
	}
	
	
	@Test(dataProvider="getSearchKey",priority = 5)
	public void searchCheckTest(String searchKey) {
		searchResultsPage = accPage.performSearch(searchKey);
		Assert.assertTrue(searchResultsPage.searchSuccessful());
	}
	
	@DataProvider
	public Object[][] getSearchCheckData() {
		return new Object[][]  {
			
			{"MacBook","MacBook Air"},
			{"iMac","iMac"},
			{"Macbook air","MacBook Air"}
		};
	}
	
	
	@Test(dataProvider = "getSearchCheckData",priority = 6)
	public void searchTest(String searchProduct, String productName) {
		searchResultsPage = accPage.performSearch(searchProduct);
		if(searchResultsPage.searchSuccessful()) {
			productInfo =  searchResultsPage.selectProduct(productName);
			String actHeader = productInfo.getProductHeader(productName);
			Assert.assertEquals(actHeader, productName);
		}
	}
}
