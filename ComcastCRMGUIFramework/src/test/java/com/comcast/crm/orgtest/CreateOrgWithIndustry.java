package com.comcast.crm.orgtest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrgWithIndustry {
	public static void main (String[] args) throws IOException {
	//step1: create utility object
	FileUtility flib = new FileUtility();
	ExcelUtility elib = new ExcelUtility();
	JavaUtility jlib = new JavaUtility();
	WebDriverUtility wlib = new WebDriverUtility();
	
	//step2: read data from properties file
	String BROWSER = flib.getDataFromPropertiesFile("browser");
	String URL = flib.getDataFromPropertiesFile("url");
	String USERNAME = flib.getDataFromPropertiesFile("username");
	String PASSWORD = flib.getDataFromPropertiesFile("password");
	
	//step3: read data from excel file
	String orgName = elib.getDataFromExcel("org", 4, 2) + jlib.getRandomNumber();
	String industry = elib.getDataFromExcel("org", 4, 4);
	String type = elib.getDataFromExcel("org", 4, 5);
	
	//step4: launch browser and perform action
			WebDriver driver = null;
			if(BROWSER.equals("chrome")) {
				driver = new ChromeDriver();
			}else {
				driver = new FirefoxDriver();
			}
			wlib.maximizeWindow(driver);
			wlib.waitForPageToLoad(driver);
			driver.get(URL);
			
			driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			driver.findElement(By.id("submitButton")).click();
			
			driver.findElement(By.linkText("Organizations")).click();
			driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			driver.findElement(By.name("accountname")).sendKeys(orgName);
			WebElement dropDownIndustry = driver.findElement(By.name("industry"));
			wlib.dropDownByText(dropDownIndustry, industry);
			WebElement dropDownType = driver.findElement(By.name("accounttype"));
			wlib.dropDownByText(dropDownType, type);
			driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[position()=1]")).click();
	}
}
			
			