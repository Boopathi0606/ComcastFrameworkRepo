package com.comcast.crm.Objectrepositaryutility;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.Webdriverutility.WebDriverUtility;

public class CreatingNewContactPage {
	WebDriver driver;
	WebDriverUtility wlib = new WebDriverUtility();
	public CreatingNewContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy (xpath = "//span[@class='dvHeaderText']")
	private WebElement headerTxt;
	
	@FindBy (name ="lastname")
	private WebElement lastNameEdt;
	
	@FindBy (xpath ="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy (name = "support_start_date")
	private WebElement startdateEdt;
	
	@FindBy (name = "support_end_date")
	private WebElement enddateEdt;
	
	@FindBy (partialLinkText = "module=Contacts")
	private WebElement currenturl;
	
	@FindBy (partialLinkText = "Accounts&action")
	private WebElement windowurl;
	
	@FindBy(xpath = "(//img[@title='Select'])[1]")
	private WebElement OrgNameBtn;
	
	@FindBy(id = "search_txt")
	private WebElement Searchtext;
	
	@FindBy(name="search")
	private WebElement Searchbtn;
	
	public WebElement getSearchbtn() {
		return Searchbtn;
	}
	
	public WebElement getSearchtext() {
		return Searchtext;
	}

	public WebElement getOrgNameBtn() {
		return OrgNameBtn;
	}

	public WebElement getWindowurl() {
		return windowurl;
	}

	public WebElement getCurrenturl() {
		return currenturl;
	}

	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getStartdateEdt() {
		return startdateEdt;
	}

	public WebElement getEnddateEdt() {
		return enddateEdt;
	}
	
	public void createContact(String lastName,String startdate,String enddate) {
		lastNameEdt.sendKeys(lastName);
		startdateEdt.sendKeys(startdate);
		enddateEdt.sendKeys(enddate);
		saveBtn.click();
	}

	public void createContact(String contactLastName, String OrgName) {
		// TODO Auto-generated method stub
		lastNameEdt.sendKeys(contactLastName);
		OrgNameBtn.click();
		wlib.switchToTabOnTitle(driver, "Accounts&action");
		Searchtext.sendKeys(OrgName);
		Searchbtn.click();
		driver.findElement(By.xpath("//a[text()='"+OrgName+"']")).click();
		wlib.switchToTabOnTitle(driver, "module=Contacts");
		saveBtn.click();
	}
	
	public void createContact1(String lastName) {
		// TODO Auto-generated method stub
		lastNameEdt.sendKeys(lastName);
		saveBtn.click();
	}



}