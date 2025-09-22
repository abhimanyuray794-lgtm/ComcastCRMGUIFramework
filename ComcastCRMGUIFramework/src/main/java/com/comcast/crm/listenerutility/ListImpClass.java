package com.comcast.crm.listenerutility;

import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.comcast.crm.baseclassutility.BaseClass;

public class ListImpClass implements ITestListener, ISuiteListener {

	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
	}
	
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
	}
	
	public void onTestStart(ITestResult result) {
		System.out.println("====>"+result.getMethod().getMethodName()+">====Start====");
	}
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("====>"+result.getMethod().getMethodName()+">====End====");
	}
	
	public void onTestFailure(ITestResult result){
		String testName = result.getMethod().getMethodName();
		
		TakesScreenshot tks = (TakesScreenshot)BaseClass.sdriver;
		File src = tks.getScreenshotAs(OutputType.FILE);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		File dest = new File("./screenshot/"+testName+""+time+".jpg");
		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void onTestSkipped(ITestResult result) {
		
	}
}
