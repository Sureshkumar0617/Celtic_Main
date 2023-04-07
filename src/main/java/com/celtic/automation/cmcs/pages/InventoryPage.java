package com.celtic.automation.cmcs.pages;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class InventoryPage extends ElementUtil {

	private WebDriver driver;
	private Logger logg;

	public InventoryPage(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#contentMsg > div > ul > li > span")
	WebElement inventoryValidateMessage;

	@FindBy(xpath = "//label[@for='SelectedApplicationType']")
	WebElement applicationTypeLbl;

	@FindBy(xpath = "//select[@id='SelectedApplicationType']")
	WebElement applicationTypeDd;

	@FindBy(xpath = "//label[@for='SelectedInventoryType']")
	WebElement inventoryNewInTypeLbl;

	@FindBy(xpath = "//select[@id='SelectedInventoryType']")
	WebElement inventoryNewInTypeDd;

	@FindBy(xpath = "//label[@for='SelectedInventorySubType']")
	WebElement inventorySubTypelbl;

	@FindBy(xpath = "//select[@id='SelectedInventorySubType']")
	WebElement inventorySubTypeDd;

	@FindBy(xpath = "//label[@for='SelectedInventoryStatus']")
	WebElement inventoryStatusLbl;

	@FindBy(xpath = "//select[@id='SelectedInventoryStatus']")
	WebElement inventoryStatusDd;

	@FindBy(xpath = "//label[@for='SelectedIssueLocation']")
	WebElement inventoryIssueLocationLbl;

	@FindBy(xpath = "//select[@id='SelectedIssueLocation']")
	WebElement inventoryIssueLocationDd;

	@FindBy(xpath = "//label[@for='FromNo']")
	WebElement invemtoryFromNoLbl;

	@FindBy(xpath = "//input[@id='FromNo']")
	WebElement invemtoryFromNo;

	@FindBy(xpath = "//label[@for='ToNbr']")
	WebElement invemtoryToNoLbl;

	@FindBy(xpath = "//input[@id='ToNbr']")
	WebElement invemtoryToNo;

	@FindBy(xpath = "//label[@for='NoOfIneventoriesToOrder']")
	WebElement inventoryQuantityLbl;

	@FindBy(xpath = "//input[@id='NoOfIneventoriesToOrder']")
	WebElement inventoryQuantity;

	@FindBy(xpath = "//label[@for='ProdExpYYYY']")
	WebElement inventoryYearLbl;

	@FindBy(xpath = "//input[@id='ProdExpYYYY']")
	WebElement inventoryYear;

	@FindBy(xpath = "//label[@for='UserId']")
	WebElement inventoryUserIdLbl;

	@FindBy(xpath = "//input[@id='UserId']")
	WebElement inventoryUserId;

	@FindBy(xpath = "//label[@for='StatusEffDate']")
	WebElement inventoryStatusEffectiveDateLbl;

	@FindBy(xpath = "//input[@id='StatusEffDate']")
	WebElement inventoryStatusEffectiveDate;

	@FindBy(xpath = "//label[@for='IsCustomerInventory']")
	WebElement inventoryIsCustomerInventoryLbl;

	@FindBy(xpath = "//input[@id='IsCustomerInventory']")
	WebElement inventoryIsCustomerInventoryChk;

	@FindBy(xpath = "//select[@class='cancel valid']")
	WebElement inventoryType;

	@FindBy(xpath = "//a[text()='Status Inquiry']")
	WebElement inventoryStatusInventory;

	@FindBy(xpath = "//*[text()='No data available in table']")
	WebElement inventoryValidateMsg;

	@FindBy(xpath = "//div[contains(@class,'alert-info')]")
	WebElement inventoryAddedMsg;

	@FindBy(xpath = "//label[@for='PreFix']")
	WebElement inventoryPrefixLbl;

	@FindBy(xpath = "//input[@id='PreFix']")
	WebElement inventoryPrefixTxt;

	public void clickOnInventoryStatus() {
		clickElement(inventoryStatusInventory);
	}

	public void selectInventoryType(String selectValue) {
		selectFromDropdownByValue(inventoryType, selectValue);
	}

	public void selectInventorySubType(String selectValue) {
		selectFromDropdownByValue(inventorySubTypeDd, selectValue);
	}

	public void validateInventoryMsg() {
		highlightElement(driver, inventoryValidateMsg);
	}

	public void selectApplicationType(String selectValue) {
		selectFromDropdownByVisibleText(applicationTypeDd, selectValue);
	}

	public void selectNewInventoryType(String selectValue) {
		selectFromDropdownByVisibleText(inventoryNewInTypeDd, selectValue);
	}

	public void selectNewSubInventoryType(String selectValue) {
		selectFromDropdownByVisibleText(inventorySubTypeDd, selectValue);
	}

	public void selectInventoryStatus(String selectValue) {
		selectFromDropdownByVisibleText(inventoryStatusDd, selectValue);
	}

	public void enterFromNo(String fromnoValue) {
		webEditTxtChange(invemtoryFromNo, fromnoValue);
	}

	public void enterToNo(String fromnoValue) {
		webEditTxtChange(invemtoryToNo, fromnoValue);
	}

	public void enterPrefix(String fromnoValue) {
		webEditTxtChange(inventoryPrefixTxt, fromnoValue);
	}

	public void enterQuantity(String quantityValue) {
		webEditTxtChange(inventoryQuantity, quantityValue);
	}

	public void enterYear(String yearValue) {
		webEditTxtChange(inventoryYear, yearValue);
	}

	public void validateAddedMsg(String msgValue) {
		highlightElement(driver, inventoryAddedMsg);
		if (FetchTextBoxValuewithText(inventoryAddedMsg).contains(msgValue))
			assert true;
	}

	public String validateMessage() {
		highlightElement(driver, inventoryValidateMessage);
		return FetchTextBoxValuewithText(inventoryValidateMessage);
	}

}