package com.comcast.crm.contactmodulewithbaseclass;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.comcast.crm.baseclassutility.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
public class CreateContactTest extends BaseClass{
	
	@Test
	public void createContactTest() throws EncryptedDocumentException, IOException {
		String lastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();
		
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		
		ContactsPage cp = new ContactsPage(driver);
		cp.getPlusButton().click();
		
		CreateContactPage cco = new CreateContactPage(driver);
		cco.getLastName().sendKeys(lastName);
		cco.getSaveButton().click();
		
		}
}
