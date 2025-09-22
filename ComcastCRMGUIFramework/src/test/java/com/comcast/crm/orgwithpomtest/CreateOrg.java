package com.comcast.crm.orgwithpomtest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreateOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;


public class CreateOrg {

	public static void main(String[] args) throws IOException {
		//Step1: create object
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		//Step2: read common data from properties file and test script data from excel file
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();
		
		//step3: launch browser, maximize window, add implicit wait and navigate to url
		WebDriver driver = null;
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}else {
			driver = new FirefoxDriver();
		}
		wLib.maximizeWindow(driver);
		wLib.waitForPageToLoad(driver);
		driver.get(URL);
		
		//step4: enter user name, password using pom class and properties file
		LoginPage lp = new LoginPage(driver);
		lp.getUsernameEdit().sendKeys(USERNAME);
		lp.getPasswordEdit().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		
		//step5: create organization and verify header and name
		HomePage ho = new HomePage(driver);
		ho.getorgLink().click();
		
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getPlusButton().click();
		
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.getOrName().sendKeys(orgName);
		cop.getSaveButton().click();
		
		//verify header message
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualHeaderInfo = oip.getActualHeaderInfo().getText();
		System.out.println(actualHeaderInfo);
		if(actualHeaderInfo.contains(orgName)) {
			System.out.println(orgName + " header is verified == PASS");
		}else {
			System.out.println(orgName + " header is not verified == FAIL");
		}
		//verify organization name
		String actualOrgName = oip.getActualOrgName().getText();
		System.out.println(actualOrgName);
		if(actualOrgName.equals(orgName)) {
			System.out.println(orgName + " is created == PASS");
		}else {
			System.out.println(orgName + " is not created == FAIL");
		}
		
		//step6: sign out from application and use quit method to close browser
		wLib.moveMouseOnElement(driver, ho.getMouseHover());
		ho.getSignoutButton().click();
		
		driver.quit();
	
	}

}
