package com.comcast.crm.Objectrepositaryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
public HomePage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver,this);
}

	@FindBy(linkText = "Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement Contactlnk;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignslnk;
	
	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLnk;
	
	public void navigateTocampaignsPage() {
		Actions act = new Actions(driver);
		act.moveToElement(moreLink).perform();
		campaignslnk.click();
	}
	 
	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getCampaignslnk() {
		return campaignslnk;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getContactlnk() {
		return Contactlnk;
	}

	public void logout() {
		Actions act=new Actions(driver);
		act.moveToElement(adminImg).perform();
		signOutLnk.click();
		
	}
	
	

}
