package com.comcast.crm.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.crm.Objectrepositaryutility.HomePage;
import com.comcast.crm.Objectrepositaryutility.LoginPage;
import com.comcast.crm.generic.Webdriverutility.JavaUtility;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class BaseClass {
	public DataBaseUtility dLib = new DataBaseUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public FileUtility fLib = new FileUtility();
	public WebDriver driver = null;
	
	@BeforeSuite
	public void configBs() {
		System.out.println("Execute BS");
		System.out.println("=====Connect to DB , Report Config=====");
		dLib.getDbconnection();
	}
	@BeforeClass
	public void configBC() throws Throwable
	{
		System.out.println("Execute BC");
		System.out.println("=====Launch the Browser=====");
		String Browser = fLib.getDataFromPropertiesFile("browser");
				
		if (Browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (Browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
	}
	
	@BeforeMethod
	public void configBM() throws Throwable
	{
		System.out.println("Execute BM");
		System.out.println("===Login===");
		
		String Url = fLib.getDataFromPropertiesFile("url");
		String Username = fLib.getDataFromPropertiesFile("username");
		String Password = fLib.getDataFromPropertiesFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginTOapp(Url, Username, Password);
	}
	
	@AfterMethod
	public void configAM()
	{
		System.out.println("Execute AM");
		System.out.println("===LogOut===");
		HomePage hp=new HomePage(driver);
		hp.logout();
	}
	@AfterClass
	public void configAC() throws Throwable
	{
		System.out.println("Execute AC");
		System.out.println("=====Close the Browser=====");
		driver.quit();
		
	}
	
	
	@AfterSuite
	public void configAS() throws Throwable {
		System.out.println("Execute AS");
		System.out.println("=====Disconnect from DB , Report Backup=====");
		dLib.closeDbconnection();
	}

	
}
