package com.comcast.crm.orgtest;

import java.io.IOException;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.Webdriverutility.JavaUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateOrganizationWithIndustriesTest {

	public static void main(String[] args) throws Throwable, IOException {
		// create object
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();

		// read common data from Properties file

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		String orgName = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

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
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// step 2:navigate to Organization module
		driver.findElement(By.linkText("Organizations")).click();

		// step 3:click on "create Organization" Button
		driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/btnL3Add.gif')]")).click();

		// step 4: enter all the details & create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		WebElement wesel1 = driver.findElement(By.name("industry"));
		Select sel1 = new Select(wesel1);
		sel1.selectByVisibleText(industry);

		WebElement wesel2 = driver.findElement(By.name("accounttype"));
		Select sel2 = new Select(wesel2);
		sel2.selectByVisibleText(type);

		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		// Verify the industry and type info
		String actindustries = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actindustries.equals(industry)) {
			System.out.println(industry + " information is verified===PASS");
		} else {
			System.out.println(industry + " information is not verified====FAIL");
		}

		String actType = driver.findElement(By.id("dtlview_Type")).getText();
		if (actType.equals(type)) {
			System.out.println(type + " information is verified===PASS");

		} else {
			System.out.println(type + " information is not verified====FAIL");
		}
		// step 5:logout
		Actions act = new Actions(driver);

		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).click()
				.perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

	}

}
