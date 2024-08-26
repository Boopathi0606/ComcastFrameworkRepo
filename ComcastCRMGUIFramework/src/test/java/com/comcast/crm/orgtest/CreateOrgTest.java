package com.comcast.crm.orgtest;
import java.io.IOException;

import com.comcast.crm.Objectrepositaryutility.CreatingNewOrganizationPage;
import com.comcast.crm.Objectrepositaryutility.HomePage;
import com.comcast.crm.Objectrepositaryutility.LoginPage;
import com.comcast.crm.Objectrepositaryutility.OrganizationInfoPage;
import com.comcast.crm.Objectrepositaryutility.OrganizationsPage;
import com.comcast.crm.generic.Webdriverutility.*;
import java.time.Duration;
import java.util.Map.Entry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateOrgTest {

	public static void main(String[] args) throws Throwable, IOException {
		// Create object for utility class
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
//		String USERNAME = fLib.getDataFromPropertiesFile("username");
//		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		// read testscript from excel file

		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

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
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);

		LoginPage lp = new LoginPage(driver);
		lp.loginTOapp(URL,"admin", "admin");

		// step 2:navigate to Organisation module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step 3:click on "create Organisation" Button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// step 4: enter all the details & create new organisation
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);

		
		  //verify Header msg Expected Result String
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actOrgName=oip.getHeaderMsg().getText();
		if(actOrgName.contains(orgName)){
			System.out.println(orgName + "name is verified===PASS");
		}
		else {
			System.out.println(orgName +"name is not verified===FAIL");
		} 
		//step 5:logout
		hp.logout();
		driver.quit();

	}

}
