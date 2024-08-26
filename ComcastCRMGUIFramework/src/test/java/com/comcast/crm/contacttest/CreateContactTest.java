package com.comcast.crm.contacttest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.Objectrepositaryutility.ContactPage;
import com.comcast.crm.Objectrepositaryutility.CreatingNewContactPage;
import com.comcast.crm.Objectrepositaryutility.CreatingNewOrganizationPage;
import com.comcast.crm.Objectrepositaryutility.HomePage;
import com.comcast.crm.basetest.BaseClass;

public class CreateContactTest extends BaseClass {
	@Test
	public void createContactTest() throws Throwable {
		// read testscript data from excel file
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		// step 2: navigate to Contact module
		HomePage hp = new HomePage(driver);
		hp.getContactlnk().click();

		// step3: Click on the Create Contact Button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContbtn().click();

		// step 4: Enter all the details & Create new Contact
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContact1(lastName);

		// verify
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actLastName.equals(lastName)) {
			System.out.println(lastName + "information is verified===PASS");
		} else {
			System.out.println(lastName + "information is not verified===FAIL");
		}

	}

	@Test
	public void CreateContactWithSupportDateTest() throws Throwable, IOException {
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();

		// step 2: navigate to Contact module
		HomePage hp = new HomePage(driver);
		hp.getContactlnk().click();

		// step3: click on create Contact Button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContbtn().click();

		// step4 Enter the all the details and create new Contact
		String endDate = jLib.getSystemDateYYYYMMDD();
		String startDate = jLib.getRequiredDateYYYYMMDD(30);
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContact(lastName, startDate, endDate);

		// verify
		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if (actStartDate.equals(startDate)) {
			System.out.println(startDate + "infomation is verified====PASS");
		} else {
			System.out.println(startDate + "infomation is not verified====FAIL");
		}

	}

	@Test
	public void createContactWithOrgTest() throws Throwable, IOException {
		String orgname = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();
		String ContactLastName = eLib.getDataFromExcel("contact", 7, 3) + jLib.getRandomNumber();
		// step 2: navigate to Organization module
		HomePage hp = new HomePage(driver);
		hp.getContactlnk().click();

		// step3: click on Create Contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContbtn().click();

		// step4: enter all the details &create new Organization
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getLastNameEdt().sendKeys(ContactLastName);

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgname);
		// verify
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if (actOrgName.trim().equals(orgname)) {
			System.out.println(orgname + "information is created===PASS");
		} else {
			System.out.println(orgname + "information is not created===FAIL");
		}

	}

}
