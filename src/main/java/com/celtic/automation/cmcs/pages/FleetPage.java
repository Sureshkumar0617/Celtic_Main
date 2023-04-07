package com.celtic.automation.cmcs.pages;

import java.io.IOException;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class FleetPage extends ElementUtil {
	private WebDriver driver;
	private Logger logg;

	public FleetPage(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h3[@class='float-left']")
	WebElement fleetLeftHeader;

	@FindBy(xpath = "//h3[@class='float-right']")
	WebElement fleetRightHeader;

	@FindBy(xpath = "//div[contains(@class,'box-title')]/h4")
	WebElement fleetSubHeader;

	@FindBy(xpath = "//label[@for='AccountNo']")
	WebElement fleetAccountNoLbl;

	@FindBy(xpath = "//label[@for='Can']")
	WebElement FleetMCECustomerNoLbl;

	@FindBy(xpath = "//label[@for='FleetNbr']")
	WebElement fleetFltNoLbl;

	@FindBy(xpath = "//label[@for='FltExpYear']")
	WebElement fleetFltExpYearLbl;

	@FindBy(xpath = "//input[@id='AccountNo']")
	WebElement fleetAccountNotTxt;

	@FindBy(xpath = "//input[@id='Can']")
	WebElement fleetMCECustomerIdTxt;

	@FindBy(xpath = "//input[@id='FleetNbr']")
	WebElement fleetFleetNoTxt;

	@FindBy(xpath = "//input[@id='FltExpYear']")
	WebElement fleetFltExpYearTxt;

	@FindBy(xpath = "//input[@id='btnProceed']")
	WebElement fleetProceedBtn;

	@FindBy(xpath = "//input[@id='btnRefresh']")
	WebElement fleetRefreshBtn;

	@FindBy(xpath = "//input[@id='btnQuit']")
	WebElement fleetQuitBtn;

	@FindBy(xpath = "//button[@id='btnHelp']")
	WebElement fleetHelpBtn;

	@FindBy(xpath = "//input[@id='FleetNo']")
	WebElement supplementFleetNo;

	@FindBy(xpath = "//input[@id='FleetExpYear']")
	WebElement supplementFleetExpirationYear;

	@FindBy(xpath = "//th[contains(@aria-label,'Account No')]")
	WebElement fleetGridAccountNoHdr;

	@FindBy(xpath = "//th[contains(@aria-label,'MCE')]")
	WebElement FleetGridMCECustomerNohdr;

	@FindBy(xpath = "//th[contains(@aria-label,'Fleet') and contains(@aria-label,'No')]")
	WebElement fleetGridFleetNoHdr;

	@FindBy(xpath = "//th[contains(@aria-label,'Fleet') and contains(@aria-label,'Month')]")
	WebElement fleetGridFleetExpMonthHdr;

	@FindBy(xpath = "//th[contains(@aria-label,'Fleet') and contains(@aria-label,'Year')]")
	WebElement fleetGridFleetExpYearHdr;

	@FindBy(xpath = "//th[contains(@aria-label,'Fleet') and contains(@aria-label,'Type')]")
	WebElement fleetGridFleetTypeHdr;

	@FindBy(xpath = "//th[contains(@aria-label,'Fleet') and contains(@aria-label,'Status')]")
	WebElement fleetGridFleetStatusHdr;

	@FindBy(xpath = "(//a[@id='lnkGridSelectCustomerGrid'])[last()]")
	WebElement fleetGridLastHandimg;

	@FindBy(xpath = "//a[@id='lnkGridSelectCustomerGrid']")
	WebElement fleetGridFirstHandimg;

	@FindBy(xpath = "//input[@id='LastInactiveDate']")
	WebElement fleetLastInactiveDate;

	@FindBy(xpath = "//a[contains(@class,'nav-fast')]")
	WebElement fleetMonthYearHdr;

	@FindBy(xpath = "//a[contains(@class,'nav-prev')]")
	WebElement fleetPreviouslnk;

	@FindBy(xpath = "//a[contains(@class,'nav-nex')]")
	WebElement fleetNextlnk;

	public void enterAccountNo(String accountNoValue) throws IOException {
		webEditTxtChange(fleetAccountNotTxt, accountNoValue);
	}

	public void enterFleetNo(String fleetNoValue) throws IOException {
		webEditTxtChange(fleetFleetNoTxt, fleetNoValue);
	}

	public void enterFleetyear(String fleetYearValue) throws IOException {
		webEditTxtChange(fleetFltExpYearTxt, fleetYearValue);
	}

	public void enterFleetNoSupplement(String fleetNoValue) throws IOException {
		webEditTxtChange(supplementFleetNo, fleetNoValue);
	}

	public void enterFleetYearSupplement(String fleetYearValue) throws IOException {
		webEditTxtChange(supplementFleetExpirationYear, fleetYearValue);
	}

	public void clickProceedBtn() {
		clickElement(fleetProceedBtn);
	}

	public void gridPresence() {
		if (isPresentAndDisplayed(fleetGridAccountNoHdr)) {
			if (isPresentAndDisplayed(fleetGridLastHandimg)) {
				clickElement(fleetGridLastHandimg);
			}
		}
	}

	public void clickFirstHandimg() {
		clickElement(fleetGridFirstHandimg);
	}

	public void enterLastInactiveDays(String inactiveDateValue) {
		clickElement(fleetLastInactiveDate);
		webEditTxt(fleetLastInactiveDate, inactiveDateValue);
	}

	public void clickCalendarHeader() {
		clickElement(fleetMonthYearHdr);
	}

	public void clickPreviouslnk() {
		clickElement(fleetPreviouslnk);
	}

	public void clickNextlnk() {
		clickElement(fleetNextlnk);
	}
}
