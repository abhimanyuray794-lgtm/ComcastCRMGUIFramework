package com.comcast.crm.orgmodulewithbaseclass;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.baseclassutility.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreateOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationTest extends BaseClass {

	@Test
	public void createOrganizationTest() throws EncryptedDocumentException, IOException {
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);

		String orgName = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();

		HomePage ho = new HomePage(driver);
		ho.getorgLink().click();
		
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getPlusButton().click();

		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.getOrName().sendKeys(orgName);
		cop.getSaveButton().click();

		// verify header message
		String actualHeaderInfo = oip.getActualHeaderInfo().getText();
		Reporter.log(actualHeaderInfo, true);
		boolean status = actualHeaderInfo.contains(orgName);
		Assert.assertEquals(status, true);
		Reporter.log("PASS", true);

		// verify organization name
		String actualOrgName = oip.getActualOrgName().getText();
		Reporter.log(actualOrgName, true);
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actualOrgName, orgName);
		Reporter.log("PASS", true);

	}

	@Test
	public void createOrganizationWithIndustryTest()
			throws EncryptedDocumentException, IOException, InterruptedException {
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgName = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
		String industry = elib.getDataFromExcel("org", 4, 4);
		String type = elib.getDataFromExcel("org", 4, 5);

		HomePage ho = new HomePage(driver);
		ho.getorgLink().click();
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getPlusButton().click();

		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.getOrName().sendKeys(orgName);

		wlib.dropDownByText(cop.getIndustryDropDown(), industry);
		wlib.dropDownByText(cop.getTypeDropDown(), type);
		cop.getSaveButton().click();

		// verify industry
		String actualIndustry = oip.getActualIndustry().getText();
		Reporter.log(actualIndustry);
		Assert.assertEquals(actualIndustry, industry);
		Reporter.log("PASS", true);
	}
}
