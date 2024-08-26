package com.comcast.crm.Objectrepositaryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	WebDriver driver;
	public ContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText = "Contacts")
	private WebElement Contactlnk;
	
	@FindBy (xpath="//img[@title='Create Contact...']")
	private WebElement createNewContbtn;
	
	@FindBy(name="search_text")
	private WebElement searchEdt;
	
	@FindBy(name="search_field")
	private WebElement searchDD;
	
	@FindBy(name="submit")
	private WebElement searchBtn;
	
	public WebElement getCreateNewContbtn() {
		return createNewContbtn;
	}
	
	public WebElement getSearchEdt() {
		return searchEdt;
	}
	
	public WebElement getSearchDD() {
		return searchDD;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}
}


