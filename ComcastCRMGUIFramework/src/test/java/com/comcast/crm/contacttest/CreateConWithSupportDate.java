package com.comcast.crm.contacttest;
import java.time.Duration;
import com.comcast.crm.generic.Webdriverutility.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateConWithSupportDate {

	public static void main(String[] args) throws Throwable {
		//create object
				FileUtility fLib=new FileUtility();
				ExcelUtility eLib=new ExcelUtility();
				JavaUtility jLib=new JavaUtility();
				
				//read common data from  Properties file
				
				String BROWSER=	fLib.getDataFromPropertiesFile("browser");
				String URL=	fLib.getDataFromPropertiesFile("url");
				String USERNAME=fLib.getDataFromPropertiesFile("username");
				String PASSWORD=fLib.getDataFromPropertiesFile("password");
		
		String lastName=eLib.getDataFromExcel("contact", 4, 2)+jLib.getRandomNumber();
	
		
		WebDriver driver=null;
		 if(BROWSER.equals("chrome")) {
			 driver=new ChromeDriver();
			 }
		 else if(BROWSER.equals("firefox")){
			 driver=new FirefoxDriver();
		 }
			 else if(BROWSER.equals("edge")) {
				 driver=new EdgeDriver();
				 }
			 else {
		driver=new ChromeDriver();
			 }
		 driver.manage().window().maximize();
		//step 1:login to app
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		 driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//step 2:navigate to Organization module
		driver.findElement(By.linkText("Contacts")).click(); 
		
		//step 3:click on "create Organization" Button
		driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/btnL3Add.gif')]")).click();
				
		//step 4: enter all the details & create new organization
		String startDate=jLib.getSystemDateYYYYMMDD();
		String endDate=jLib.getRequiredDateYYYYMMDD(30);
		
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.xpath("//input[@id='jscal_field_support_start_date']")).sendKeys(startDate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.xpath("//input[@id='jscal_field_support_end_date']")).sendKeys(endDate);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		
		//verify start date
		String actstartdate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actstartdate.equals(startDate)) {
			System.out.println(startDate + " startDate is verified====PASS");
		}else {
			System.out.println(startDate + " startDate is not verified====FAIL");
		}
		
		//verify end date
		String actenddate=driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actenddate.equals(endDate)) {
			System.out.println(endDate + " endDate is verified====PASS");
		}else {
			System.out.println(endDate + " endDate is not verified====FAIL");
		}
		
		//step 5:logout
		Actions act= new Actions(driver);
		
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")))
		.click().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		}

}
