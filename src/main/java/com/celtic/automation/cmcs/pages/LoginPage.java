package com.celtic.automation.cmcs.pages;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class LoginPage extends ElementUtil {
	private WebDriver driver;
	private Logger logg;

	public LoginPage(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='UserName']")
	WebElement loginUserId;

	@FindBy(xpath = "//input[@id='Password']")
	WebElement loginPassword;

	@FindBy(xpath = "//input[@id='btnlogin']")
	WebElement loginbtnLogin;

	public String getLoginPageTitle() {
		return getTitle();
	}

	public void enterUsername(String username) throws Exception {
		webEditTxtChange(loginUserId, username);
	}

	public void enterPassword(String password) {
		webEditTxtChange(loginPassword, password);
	}

	public void clickLoginBtn() throws InterruptedException {
		clickElementUsingActions(loginbtnLogin);
	}
}
