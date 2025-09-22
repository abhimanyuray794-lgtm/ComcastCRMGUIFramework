package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganizationPage {
	WebDriver driver;
	public CreateOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountname")
	WebElement orName;
	
	@FindBy(name="industry")
	WebElement industryDropDown;
	
	@FindBy(name="accounttype")
	WebElement typeDropDown;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[position()=1]")
	WebElement saveButton;
	
	public WebElement getOrName() {
		return orName;
	}

	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}
	
	public WebElement getTypeDropDown() {
		return typeDropDown;
	}
	
	public WebElement getSaveButton() {
		return saveButton;
	}
}
