package com.celtic.automation.cmcs.pages;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class DashBoardPage extends ElementUtil {
	private WebDriver driver;
	private Logger logg;

	public DashBoardPage(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@title='Services']")
	WebElement dashboardServicesLink;

	@FindBy(xpath = "//a[@title='IRP']")
	WebElement dashboardIRPLink;

	@FindBy(xpath = "//a[@title='Enterprise']")
	WebElement dashboardEnterpriseLink;

	@FindBy(xpath = "//div[@class='PageHeader row']")
	WebElement dashboardHeader;

	@FindBy(xpath = "//div[@class='PageHeader row']//li[contains(@class,'breadcrumb-item')]")
	WebElement dashboardSecondRow;

	@FindBy(xpath = "//div[@class='PageHeader row']//li[contains(@class,'breadcrumb-item')]/strong")
	WebElement dashboardIRPHeader;

	@FindBy(xpath = "//a[@title='Renew Fleet']")
	WebElement dashboardRenewFleetLink;

	@FindBy(xpath = "//a[@title='Fleet Inquiry']")
	WebElement dashboardFleetEnquiry;

	@FindBy(xpath = "//a[@title='Supplement Continuance']")
	WebElement dashboardSupplementContinuancelnk;

	@FindBy(xpath = "//a[@title='Vehicle Inquiry']")
	WebElement dashboardVehicleEnquiry;

	@FindBy(xpath = "//a[@title='Vehicle Supplement Inquiry']")
	WebElement dashboardVehicleSupplementEnquiry;

	@FindBy(xpath = "//a[@title='Supplement Inquiry']")
	WebElement dashboardSupplementEnquiry;

	@FindBy(xpath = "//a[@title='Account Inquiry']")
	WebElement dashboardAccountEnquiry;

	@FindBy(xpath = "//a[@title='Supplement Documents']")
	WebElement dashboardSupplementDocuments;

	@FindBy(xpath = "//a[@title='Logout']")
	WebElement dashboardLogout;

	@FindBy(xpath = "//a[@title='Installment Payment']")
	WebElement installmentPayment;

	@FindBy(xpath = "//a[text()='Add Vehicle']")
	WebElement siteMapAddVehicle;

	@FindBy(xpath = "//input[@id='accountNo']")
	WebElement accountNo;

	@FindBy(id = "FleetNbr")
	WebElement vehicleFleetNo;

	@FindBy(id = "FltExpYear")
	WebElement vehicleExpYr;

	@FindBy(id = "btnProceed")
	WebElement vehicleProceed;

	@FindBy(xpath = "//ul[@class='errorMessage']//span")
	WebElement dashboardMessage;

	@FindBy(xpath = "//ul[contains(@class,'errorMessage')]/li/span")
	WebElement dashboardErrorMsg;

	@FindBy(xpath = "//a[@id='cartRef']")
	WebElement cartImg;

	@FindBy(xpath = "//li[@class='dropdown']/a[contains(text(),'More')]")
	WebElement dashboardFleetMore;

	@FindBy(xpath = "//a[text()='Fleet Reinstatement']")
	WebElement dashoboadMoreFleetReinstatement;

	@FindBy(xpath = "//a[@title='Operations']")
	WebElement dashboardOperation;

	@FindBy(xpath = "//a[text()='Inventory']")
	WebElement dashboardInventory;

	@FindBy(xpath = "//a[text()='New Inventory']")
	WebElement inventoryNewInventory;

	@FindBy(xpath = "//a[@title='Assign Inventory']")
	WebElement inventoryAssignInventorylnk;

	public void clickServiceLink() throws InterruptedException {
		waitUntilElementClickable(dashboardServicesLink);
		clickElementUsingActions(dashboardServicesLink);
	}

	public void clickIRPLink() throws InterruptedException {
		waitUntilElementClickable(dashboardIRPLink);
		clickElementUsingActions(dashboardIRPLink);
	}

	public void clickEnterpriseLink() throws InterruptedException {
		waitUntilElementClickable(dashboardEnterpriseLink);
		clickElementUsingActions(dashboardEnterpriseLink);
	}

	public void clickInstallmentPayment() {
		waitUntilElementClickable(installmentPayment);
		clickElementUsingJavscriptExecutor(installmentPayment);
	}

	public void validateIRPScreen(String title) throws InterruptedException {
		highlightElement(driver, dashboardIRPHeader);
		if (FetchTextBoxValuewithText(dashboardIRPHeader).equalsIgnoreCase(title)) {
			assert true;
		} else {
			assert false;
		}
	}

	public void clickRenewFleetLink() throws InterruptedException {
		waitUntilElementClickable(dashboardRenewFleetLink);
		clickElementUsingActions(dashboardRenewFleetLink);
	}

	public void validateMessage(CharSequence successMessageValue) {
		if (FetchTextBoxValuewithText(dashboardMessage).contains(successMessageValue)) {
			assert true;
		} else {
			assert false;
		}
	}

	public void clickSupplementContinuance() throws InterruptedException {
		waitUntilElementClickable(dashboardSupplementContinuancelnk);
		clickElementUsingActions(dashboardSupplementContinuancelnk);
	}

	public void clickFleetEnquiry() throws InterruptedException {
		waitUntilElementClickable(dashboardFleetEnquiry);
		clickElementUsingActions(dashboardFleetEnquiry);
	}

	public void clickVehicleEnquiry() throws InterruptedException {
		waitUntilElementClickable(dashboardVehicleEnquiry);
		clickElementUsingActions(dashboardVehicleEnquiry);
	}

	public void clickVehicleSupplementEnquiry() throws InterruptedException {
		waitUntilElementClickable(dashboardVehicleSupplementEnquiry);
		clickElementUsingActions(dashboardVehicleSupplementEnquiry);
	}

	public void clickSupplementEnquiry() throws InterruptedException {
		waitUntilElementClickable(dashboardSupplementEnquiry);
		clickElementUsingActions(dashboardSupplementEnquiry);
	}

	public void clickAccountEnquiry() throws InterruptedException {
		waitUntilElementClickable(dashboardAccountEnquiry);
		clickElementUsingActions(dashboardAccountEnquiry);
	}

	public void clickSupplementDocuments() throws InterruptedException {
		waitUntilElementClickable(dashboardSupplementDocuments);
		clickElementUsingActions(dashboardSupplementDocuments);
	}

	public void enterAccountNo(String accountNoValue) {
		waitUntilElementClickable(accountNo);
		webEditTxt(accountNo, accountNoValue);
	}

	public void validateErrorMessage(String errorMessgeValue) {
		if (FetchTextBoxValuewithText(dashboardErrorMsg).contains(errorMessgeValue)) {
			assert true;
		} else {
			assert false;
		}
	}

	public void clickCartImg() {
		waitUntilElementClickable(cartImg);
		clickElement(cartImg);
	}

	public void clickFleetMore() throws InterruptedException {
		waitUntilElementClickable(dashboardFleetMore);
		clickElement(dashboardFleetMore);
	}

	public void clickFleetReinstatement() {
		waitUntilElementClickable(dashoboadMoreFleetReinstatement);
		clickElement(dashoboadMoreFleetReinstatement);
	}

	public void clickLogout() {
		waitUntilElementClickable(dashboardLogout);
		clickElement(dashboardLogout);
	}

	public void clickOperation() {
		waitUntilElementClickable(dashboardOperation);
		clickElement(dashboardOperation);
	}

	public void clickOnInventory() {
		waitUntilElementClickable(dashboardInventory);
		clickElement(dashboardInventory);
	}

	public void clickNewInventory() {
		waitUntilElementClickable(inventoryNewInventory);
		clickElement(inventoryNewInventory);
	}

	public void clickAssignInventory() {
		waitUntilElementClickable(inventoryAssignInventorylnk);
		clickElement(inventoryAssignInventorylnk);
	}
}
