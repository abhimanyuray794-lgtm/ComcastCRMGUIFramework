package com.comcast.crm.baseclassutility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlTest;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {

	DataBaseUtility dlib = new DataBaseUtility();
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib = new JavaUtility();
	public WebDriverUtility wlib = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;

	@BeforeSuite
	public void configBS() {
		System.out.println("==connect to DB, Report Config==");
//		dlib.getDbconnection();
	}

	@BeforeClass
	public void configBC(XmlTest test) throws IOException, InterruptedException {
		System.out.println("==Launch the BROWSER==");
		String BROWSER = System.getProperty("browser");
		if (BROWSER == null) {
			BROWSER = test.getParameter("browser");
		}
		if (BROWSER == null) {
			BROWSER = flib.getDataFromPropertiesFile("browser");
		}
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else {
			driver = new FirefoxDriver();
		}
		UtilityClassObject.setDriver(driver);
		sdriver = driver;
	}

	@BeforeMethod
	public void configBM() throws IOException {
		System.out.println("==Login==");
		String URL = System.getProperty("url", flib.getDataFromPropertiesFile("url"));
		String USERNAME = System.getProperty("username", flib.getDataFromPropertiesFile("username"));
		String PASSWORD = System.getProperty("password", flib.getDataFromPropertiesFile("password"));
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
	}

	@AfterMethod
	public void configAM() throws InterruptedException {
		System.out.println("==Logout==");
		HomePage hp = new HomePage(driver);
		wlib.moveMouseOnElement(driver, hp.getMouseHover());
		hp.getSignoutButton().click();
		;
	}

	@AfterClass
	public void configAC() {
		System.out.println("==Close the BROWSER");
		driver.quit();
	}

	@AfterSuite
	public void configAS() {
		System.out.println("==close DB, Report Backup");
//		dlib.closeDbconnection();
	}
}
