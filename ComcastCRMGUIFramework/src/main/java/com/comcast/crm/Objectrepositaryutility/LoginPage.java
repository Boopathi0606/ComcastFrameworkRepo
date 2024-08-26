package com.comcast.crm.Objectrepositaryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.Webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility{
	/*
	 * Rule 1 : Create a seperate java class   * Rule 2 : Object Creation 
	 * Rule 3 :Object Initialaization 		   * Rule 4 : Object Encapsulation 
	 * Rule 5 								   * 
	 */	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	// Rule 2
	@FindBy(name="user_name")
	private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	// Rule 4
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	// Rule 5 : Provide Action
	public void loginTOapp(String url,String username,String password)
	{
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
	
		
	
	
	
	
}
