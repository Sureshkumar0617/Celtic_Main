package com.celtic.automation.cmcs.pages;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class Enquiry extends ElementUtil {
	private WebDriver driver;
	private Logger logg;

	public Enquiry(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//table[@id='FltInquiryGrid']")
	WebElement fleetEnquiryGrid;

	@FindBy(xpath = "//td[contains(@class,'leftAlign')]")
	List<WebElement> fleetEnquiryRowValues;

	@FindBy(xpath = "//input[@id='AccountNO']")
	WebElement supplementEnquiryAccountNo;

	@FindBy(xpath = "//input[@id='FleetExpiryYear']")
	WebElement supplementEnquiryExpyear;

	@FindBy(xpath = "//table[@id='supplementInquiryGrid']")
	WebElement supplementEnquiryGrid;

	@FindBy(xpath = "//table[@id='VehInquiryGrid']")
	WebElement vehicleEnquiryGrid;

	@FindBy(xpath = "//th[contains(@aria-label,'VIN')]")
	WebElement vehicleEnquiryVIN;

	@FindBy(xpath = "//th[contains(@aria-label,'Unit')]")
	WebElement vehicleEnquiryUnitNo;

	@FindBy(xpath = "//input[@id='ExpirationYear']")
	WebElement vehicleEnquiryExpYear;

	@FindBy(xpath = "//th[contains(@aria-label,'Fleet Exp. MM/YYYY')]")
	WebElement vehicleEnquiryFleetExp;

	@FindBy(xpath = "//table[@id='VehInquiryGrid']/tbody/tr")
	List<WebElement> vehicleEnquiryRows;

	@FindBy(xpath = "//table[@id='gvVinTransInquiryGrid']")
	WebElement vehicleSupplementEnquiryGrid;

	@FindBy(xpath = "//input[@id='FleetExpYear']")
	WebElement vehicleSupplementEnquiryExpYear;

	@FindBy(xpath = "//table[@id='gvVinTransInquiryGrid']/tbody/tr")
	List<WebElement> vehicleSupplementEnquiryRows;

	@SuppressWarnings("unused")
	public Boolean fleetenquiryvaluevalidation(String valueCheck) throws InterruptedException {
		waitUntilElementVisible(fleetEnquiryGrid);
		Boolean flag = false;
		if (isPresentAndDisplayed(fleetEnquiryGrid)) {
			for (int i = 0; i < fleetEnquiryRowValues.size(); i++) {
				if (FetchTextBoxValuewithText(fleetEnquiryRowValues.get(i)).contains(valueCheck)) {
					highlightElement(driver, fleetEnquiryRowValues.get(i));
					sleepTime(1000); // to wait the screen for user visibility
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	public void enterSupplementEnquiryAccountNo(String accountNoValue) throws IOException {
		webEditTxtChange(supplementEnquiryAccountNo, accountNoValue);
	}

	public void enterSupplementEnquiryExpYear(String enquiryExpYear) {
		webEditTxtChange(supplementEnquiryExpyear, enquiryExpYear);

	}

	@SuppressWarnings("unused")
	public Boolean supplementEnquiryvaluevalidation(String valueCheck) throws InterruptedException {
		Boolean flag = false;
		waitUntilElementVisible(supplementEnquiryGrid);
		if (isPresentAndDisplayed(supplementEnquiryGrid)) {
			for (int i = 0; i < fleetEnquiryRowValues.size(); i++) {
				if (FetchTextBoxValuewithText(fleetEnquiryRowValues.get(i)).contains(valueCheck)) {
					highlightElement(driver, fleetEnquiryRowValues.get(i));
					sleepTime(1000); // to wait the screen for user visibility
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	public void enterVehicleEnquiryExpYear(String vehicleEnquiryExpYearValue) {
		webEditTxtChange(vehicleEnquiryExpYear, vehicleEnquiryExpYearValue);
	}

	public void sortVehicleEnquiryUnitNo() {
		waitUntilElementVisible(vehicleEnquiryGrid);
		if (isPresentAndDisplayed(vehicleEnquiryGrid)) {
			if (!(FetchTextBoxValuewithattribute(vehicleEnquiryUnitNo, "class").contains("asc"))) {
				clickElement(vehicleEnquiryUnitNo);
			}
		}
	}

	public int vehicleEnquiryTableRowCount() {
		return vehicleEnquiryRows.size();
	}

	public String fetchVehicleEnquiryVIN(String i, String yearValue) {
		String vinTableValue = null;
		WebElement ExpiryYear = driver.findElement(By.xpath("//table[@id='VehInquiryGrid']/tbody/tr[" + i + "]/td[5]"));
		WebElement VIN = driver.findElement(By.xpath("//table[@id='VehInquiryGrid']/tbody/tr[" + i + "]/td[6]"));
		if (isPresentAndDisplayed(vehicleEnquiryGrid)) {
			if (FetchTextBoxValuewithText(ExpiryYear).contains(yearValue)) {
				highlightElement(driver, VIN);
				sleepTime(1000); // to wait the screen for user visibility
				vinTableValue = FetchTextBoxValuewithText(VIN);
			}
		}
		return vinTableValue;
	}

	public String fetchVehicleEnquiryUnit(String i, String yearValue) {
		String unitTableValue = null;
		WebElement ExpiryYear = driver.findElement(By.xpath("//table[@id='VehInquiryGrid']/tbody/tr[" + i + "]/td[5]"));
		WebElement unit = driver.findElement(By.xpath("//table[@id='VehInquiryGrid']/tbody/tr[" + i + "]/td[8]"));
		if (isPresentAndDisplayed(vehicleEnquiryGrid)) {
			if (FetchTextBoxValuewithText(ExpiryYear).contains(yearValue)) {
				highlightElement(driver, unit);
				sleepTime(1000); // to wait the screen for user visibility
				unitTableValue = FetchTextBoxValuewithText(unit);
			}
		}
		return unitTableValue;
	}

	public void enterVehicleSupplementEnquiryExpYear(String vehicleSupplementEnquiryExpYearValue) {
		webEditTxtChange(vehicleSupplementEnquiryExpYear, vehicleSupplementEnquiryExpYearValue);
	}

	public void clickVehicleSupplementUnitNo() {
		if (isPresentAndDisplayed(vehicleSupplementEnquiryGrid)) {
			if (!(FetchTextBoxValuewithattribute(vehicleEnquiryUnitNo, "class").contains("asc"))) {
				clickElement(vehicleEnquiryUnitNo);
			}
		}
	}

	public int vehicleSupplementEnquiryTableRowCount() {
		return vehicleSupplementEnquiryRows.size();

	}

	public String FetchVehicleSupplementEnquiryVIN(String i, String yearValue) {
		String vinTableValue = null;
		WebElement ExpiryYear = driver.findElement(By.xpath("//tr[" + i + "]//td[contains(@class,'Alignment')][4]"));
		WebElement VIN = driver.findElement(By.xpath("//tr[" + i + "]//td[contains(@class,'Alignment')][8]"));
		if (isPresentAndDisplayed(vehicleSupplementEnquiryGrid)) {

			if (FetchTextBoxValuewithText(ExpiryYear).contains(yearValue)) {
				highlightElement(driver, VIN);
				sleepTime(1000); // to wait the screen for user visibility
				vinTableValue = FetchTextBoxValuewithText(VIN);
			}
		}
		return vinTableValue;
	}

	public String fetchVehicleSupplementEnquiryUnit(String i, String yearValue, String serviceTypeValue) {
		String unitTableValue = null;
		WebElement expiryYear = driver.findElement(By.xpath("//tr[" + i + "]//td[contains(@class,'Alignment')][4]"));
		WebElement serviceType = driver.findElement(By.xpath("//tr[" + i + "]//td[contains(@class,'Alignment')][6]"));
		WebElement unit = driver.findElement(By.xpath("//tr[" + i + "]//td[contains(@class,'Alignment')][10]"));

		if (isPresentAndDisplayed(vehicleSupplementEnquiryGrid)) {
			if (FetchTextBoxValuewithText(expiryYear).contains(yearValue)) {
				if (FetchTextBoxValuewithText(serviceType).contains(serviceTypeValue)) {
					highlightElement(driver, unit);
					sleepTime(1000); // to wait the screen for user visibility
					unitTableValue = FetchTextBoxValuewithText(unit);
				}
			}
		}
		return unitTableValue;
	}

}
