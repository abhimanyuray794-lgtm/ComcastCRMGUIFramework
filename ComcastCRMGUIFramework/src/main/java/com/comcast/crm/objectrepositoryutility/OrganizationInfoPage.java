package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement actualHeaderInfo;
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement actualOrgName;
	
	@FindBy(id="dtlview_Industry")
	private WebElement actualIndustry;
	
	@FindBy(id="dtlview_Type")
	private WebElement actualType;
	
	public WebElement getActualOrgName() {
		return actualOrgName;
	}
	
	public WebElement getActualHeaderInfo() {
		return actualHeaderInfo;
	}

	public WebElement getActualIndustry() {
		return actualIndustry;
	}

	public WebElement getActualType() {
		return actualType;
	}

	
	
	
}
