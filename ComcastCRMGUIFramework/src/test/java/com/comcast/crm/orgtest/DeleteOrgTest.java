package com.comcast.crm.orgtest;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.Objectrepositaryutility.CreatingNewOrganizationPage;
import com.comcast.crm.Objectrepositaryutility.HomePage;
import com.comcast.crm.Objectrepositaryutility.LoginPage;
import com.comcast.crm.Objectrepositaryutility.OrganizationInfoPage;
import com.comcast.crm.Objectrepositaryutility.OrganizationsPage;
import com.comcast.crm.generic.Webdriverutility.JavaUtility;
import com.comcast.crm.generic.Webdriverutility.WebDriverUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class DeleteOrgTest {

	public static void main(String[] args) throws Throwable, IOException {
		// Create object for utility class
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
//		String USERNAME = fLib.getDataFromPropertiesFile("username");
//		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		// read testscript from excel file
		String orgName = eLib.getDataFromExcel("org", 10, 2) + jLib.getRandomNumber();

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
		lp.loginTOapp("http://localhost:8888","admin", "admin");

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
		
		//go back to organization page 
		hp.getOrgLink().click();

		//serach for organisation
		op.getSearchEdit().sendKeys(orgName);
		wLib.select(op.getSearchDD(), "Organization Name");
		op.getSearchBtn().click();
		
		//In dynamic webTable select & Delete org
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		//step 5: logout
		//	hp.logout();
		//driver.quit();
		 
		  
		 

	}

}
