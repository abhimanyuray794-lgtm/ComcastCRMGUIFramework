package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {
	WebDriver driver = null;

	public CreateContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "lastname")
	WebElement lastName;
	
	@FindBy(id="mobile")
	WebElement mobEdit;
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[position()=1]")
	WebElement saveButton;

	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getMobEdit() {
		return mobEdit;
	}
	
	public WebElement getSaveButton() {
		return saveButton;
	}

}
