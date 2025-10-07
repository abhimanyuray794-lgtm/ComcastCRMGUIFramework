package com.comcast.crm.listenerutility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImplementingClass implements ITestListener, ISuiteListener {
	ExtentReports report;
	ExtentTest test;
	String testName;
	String time = new Date().toString().replace(" ", "_").replace(":", "_");
	
	@Override
	public void onStart(ISuite suite) {
		
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("vtiger");
		spark.config().setReportName("vtiger Report");
		spark.config().setTheme(Theme.DARK);
		
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 11");
		report.setSystemInfo("browser", "chrome");
	}

	@Override
	public void onFinish(ISuite suite) {
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getMethod().getMethodName();
		test = report.createTest(testName);
		test.log(Status.INFO, testName+ " get started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, testName+ " got pass");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, testName+ " got fail=> "+result.getThrowable());
//		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		TakesScreenshot tks = (TakesScreenshot)UtilityClassObject.getDriver();
		String filePath = tks.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(filePath+"_"+time);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, testName+ " got skipped");
	}

}
