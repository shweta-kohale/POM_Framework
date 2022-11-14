package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String,String> productInfoMap;
	
	private By productImgs = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getProductHeader(String mainProductName) {
		String xPath = "//h1[text()='"+mainProductName+"']";
		String productHeader = eleUtil.doGetText(By.xpath(xPath));
		return productHeader;
	}
	
	public int getProductImagesCount() {
		return eleUtil.waitForElementsToBeVisible(productImgs, AppConstants.DEFAULT_LARGE_TIME_OUT).size();
	}
	
	
	public String getProductPageTitle(String productPageTitle) {
		return eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, productPageTitle);
	}
	
	public String poductPageURL(String productPageURL) {
		return eleUtil.waitForUrlIs(AppConstants.DEFAULT_TIME_OUT, productPageURL);
	}
	
	public Map<String,String> getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		productInfoMap = new LinkedHashMap<String,String>();
		for(WebElement e : metaList) {
			String metaText = e.getText();
			String meta[] = metaText.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
		productInfoMap.forEach((metaKey,metaValue) -> System.out.println(metaKey +":" + metaValue));
		return productInfoMap;
	}
}
