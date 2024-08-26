package com.comcast.crm.Objectrepositaryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

	public class CreatingNewOrganizationPage {
		WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="accountname")
	private WebElement orgnameEdt;
	
	@FindBy(xpath="input[@titlr='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDB;
	
	public WebElement getOrgnameEdt() {
		return orgnameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createOrg(String orgName) {
		orgnameEdt.sendKeys(orgName);
		saveBtn.click();
		
	}
	
	public void createOrg(String orgName , String industry) {
		orgnameEdt.sendKeys(orgName);
		Select sel = new Select(industryDB);
		sel.selectByVisibleText(industry);
		saveBtn.click();
	}
	
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	