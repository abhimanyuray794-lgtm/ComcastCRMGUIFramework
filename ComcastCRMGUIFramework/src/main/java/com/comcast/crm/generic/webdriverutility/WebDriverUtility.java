package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebDriverUtility {
	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void moveMouseOnElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	public void scrollToElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.scrollToElement(element).perform();
	}
	
	public void dropDownByText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);	
	}
	
	public void dropDownByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);	
	}
	
	public void dropDownByValue(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByValue(text);	
	}
	
}
