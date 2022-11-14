package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.constants.AppConstants;
import com.qa.operncart.base.BaseTest;

public class ProductPageTest extends BaseTest{

	@BeforeClass
	public void prodInfoSetup() {
		accPage = loginObject.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getproductInfoKey() {
		return new Object[][]  {
			
			{"MacBook","MacBook Pro"},
			{"iMac","iMac"},
			{"Macbook air","MacBook Air"}
		};
	}
	@Test(dataProvider = "getproductInfoKey")
	public void productHeaderTest(String key, String productName) {
		searchResultsPage= accPage.performSearch(key);
		productInfo = searchResultsPage.selectProduct(productName);
		String actProductHeader = productInfo.getProductHeader(productName);
		Assert.assertEquals(actProductHeader, productName);
	}
	
	@DataProvider
	public Object[][] getProductInfoData() {
		return new Object[][]  {
			
			{"MacBook","MacBook Pro",AppConstants.MACBOOK_PRO_CONSTANT},
			{"iMac","iMac",AppConstants.IMAC_CONSTANT},
			{"Macbook air","MacBook Air",AppConstants.MACBOOK_AIR_CONSTANT}
		};
	}
	
	@Test(dataProvider = "getProductInfoData")
	public void productImagesCountTest(String searchProduct, String productName, int imgCount) {
		searchResultsPage= accPage.performSearch(searchProduct);
		productInfo = searchResultsPage.selectProduct(productName);
		int actProductImageCount = productInfo.getProductImagesCount();
		System.out.println("Actual Product image count: "+actProductImageCount);
		Assert.assertEquals(actProductImageCount, imgCount);
	}
	 	
	@Test
	public void productMetaDataTest() {
		searchResultsPage= accPage.performSearch("MacBook");
		productInfo = searchResultsPage.selectProduct("MacBook");
		Map<String,String> actProductMetaData = productInfo.getProductMetaData();
		Assert.assertEquals(actProductMetaData.get("Brand"), "Apple");
		Assert.assertEquals(actProductMetaData.get("Product Code"), "Product 16");
		Assert.assertEquals(actProductMetaData.get("Reward Points"), "600");
		Assert.assertEquals(actProductMetaData.get("Availability"), "In Stock");
	}
	
}
