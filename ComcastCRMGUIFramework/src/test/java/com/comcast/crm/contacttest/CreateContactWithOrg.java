package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import com.comcast.crm.generic.Webdriverutility.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateContactWithOrg {

	public static void main(String[] args) throws Throwable {
		// create object for utility class
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		String orgname = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();
		String ContactLastName = eLib.getDataFromExcel("contact", 7, 3);

		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		// step 1:login to app
		wLib.waitForPageToLoad(driver);

		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// step 2:navigate to Organization module
		driver.findElement(By.linkText("Organizations")).click();

		// step 3:click on "create Organization" Button
		driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/btnL3Add.gif')]")).click();

		// step 4: enter all the details & create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgname);

		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		// verify Header msg Expected Result
		String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerinfo.contains(orgname)) {
			System.out.println(orgname + " header is Created====PASS");
		} else {
			System.out.println(orgname + " header is not created====FAIL");
		}

		// step5:navigate to contact module

		driver.findElement(By.linkText("Contacts")).click();

		// step 6:click on "create Organization" Button
		driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/btnL3Add.gif')]")).click();

		// step 7: enter all the details & create new organization
		driver.findElement(By.name("lastname")).sendKeys(ContactLastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// switch to child window
		wLib.switchToTabOnURL(driver, "Accounts&action");

		driver.findElement(By.name("search_text")).sendKeys(orgname);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

		// switch to parent window
		wLib.switchToTabOnURL(driver, "Contacts&action");

		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		// verify Header msg Expected Result
		headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerinfo.contains(ContactLastName)) {
			System.out.println(ContactLastName + " header is Created====PASS");
		} else {
			System.out.println(ContactLastName + " header is not created====FAIL");
		}

		// Verify Header Orgname info Expected Result
		String actorgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actorgname);
		if (actorgname.trim().equals(orgname)) {
			System.out.println(orgname + " Information is Created====PASS");
		} else {
			System.out.println(orgname + " Information is not Created====FAIL");
		}

		// step 5:logout
		Actions act = new Actions(driver);

		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).click()
				.perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

	}

}
