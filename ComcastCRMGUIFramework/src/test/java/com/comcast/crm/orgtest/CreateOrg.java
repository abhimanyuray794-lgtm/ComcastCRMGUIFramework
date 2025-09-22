package com.comcast.crm.orgtest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrg {
	public static void main(String[] args) throws IOException {
		//step1: create object
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		//step2: read common data from properties file
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		
		//step3: read test data from excel file
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();
		
		//step4: launch browser and perform action
		WebDriver driver = null;
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}else {
			driver = new FirefoxDriver();
		}
		wLib.maximizeWindow(driver);
		wLib.waitForPageToLoad(driver);
		driver.get(URL);
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[position()=1]")).click();
		
		//verify header message
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		System.out.println(headerInfo);
		if(headerInfo.contains(orgName)) {
			System.out.println(orgName + " header is verified == PASS");
		}else {
			System.out.println(orgName + " header is not verified == FAIL");
		}
		//verify organization name
		String actualOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		System.out.println(actualOrgName);
		if(actualOrgName.equals(orgName)) {
			System.out.println(orgName + " is created == PASS");
		}else {
			System.out.println(orgName + " is not created == FAIL");
		}
		driver.quit();
		
	}
}
