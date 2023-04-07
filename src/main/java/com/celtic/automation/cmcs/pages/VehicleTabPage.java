package com.celtic.automation.cmcs.pages;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class VehicleTabPage extends ElementUtil {
	private WebDriver driver;
	private Logger logg;
	
	public VehicleTabPage(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg=logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='validation-summary-errors clearfix']")
	WebElement vehicleValidateMessage;
	
	@FindBy(css = "#contentMsg > div > ul > li > span")
	WebElement vehicleVerificationMessage;
	
	@FindBy(css = "#contentMsg > div > ul")
	WebElement vehicleVerificationMessage2;
	
	@FindBy(css = "#contentMsg > div > ul > li:nth-child(1) > span")
	WebElement vehicleVerificationMessage3;
	
	@FindBy(css = "#contentMsg > div > ul > li:nth-child(2) > span")
	WebElement vehicleVerificationMessage4;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[1]")
	WebElement vehicleRenewalDetailsSubHdr;
	
	@FindBy(xpath = "//label[@for='NoOfAmendVeh']")
	WebElement vehicleAmendedVehiclesLbl;
	
	@FindBy(xpath = "//input[@id='NoOfAmendVeh']")
	WebElement vehicleAmendedVehiclesTxt;

	@FindBy(xpath = "//label[@for='NoOfAddVeh']")
	WebElement vehicleAddedVehiclesLbl;
	
	@FindBy(xpath = "//input[@id='NoOfAddVeh']")
	WebElement vehicleAddedVehiclesTxt;

	@FindBy(xpath = "//label[@for='NoOfDelVeh']")
	WebElement vehicleDeleteVehiclesLbl;
	
	@FindBy(xpath = "//input[@id='NoOfDelVeh']")
	WebElement vehicleDeleteVehiclesTxt;

	@FindBy(xpath = "//label[@for='NoOfRenewVeh']")
	WebElement vehicleRenewVehiclesLbl;
	
	@FindBy(xpath = "//input[@id='NoOfRenewVeh']")
	WebElement vehicleRenewVehiclesTxt;

	@FindBy(xpath = "//input[@id='VehicleAction' and contains(@title,'AMEND')]")
	WebElement vehicleAmendRd;
	
	@FindBy(xpath = "//input[@id='VehicleAction' and contains(@title,'DELETE')]")
	WebElement vehicleDeleteRd;
	
	@FindBy(xpath = "//input[@id='VehicleAction' and contains(@title,'Add')]")
	WebElement vehicleAddRd;

	@FindBy(xpath = "//input[@id='btnUpdateFromPrevYear']")
	WebElement vehicleUpdateFromPreviousYearBtn;
	
	@FindBy(xpath = "//input[@id='btnVehicleList']")
	WebElement vehicleVehicleListBtn;

	@FindBy(xpath = "//table[@id='gvVehicleList']//tr[contains(@class,'odd') or contains(@class,'even')]")
	List<WebElement> vehicleListRows;

	@FindBy(xpath = "(//td//a[@id='lnkGridSelectgvVehicleList'])")
	List<WebElement> handImgsList;

	public void clickAmendVehicleRadioButton() {
		clickElement(vehicleAmendRd);
	}

	public void clickDeleteVehicleRadioButton() {
		clickElement(vehicleDeleteRd);
	}

	public void clickAddVehicleRadioButton() {
		clickElement(vehicleAddRd);
	}

	public void clickUpdateFromPreviousYear() {
		clickElement(vehicleUpdateFromPreviousYearBtn);
	}

	public void clickVehicleList() {
		clickElement(vehicleVehicleListBtn);
	}

	@SuppressWarnings("unlikely-arg-type")
	public void validateAmendedVehiclesCount(String amendedVehiclesCount) {
		assert equals(FetchTextBoxValuewithattribute(vehicleAmendedVehiclesTxt, "value").toString()
				.equalsIgnoreCase(amendedVehiclesCount));
	}

	@SuppressWarnings("unlikely-arg-type")
	public void validateDeleteVehiclesCount(String amendedVehiclesCount) {
		assert equals(FetchTextBoxValuewithattribute(vehicleDeleteVehiclesTxt, "value").toString()
				.equalsIgnoreCase(amendedVehiclesCount));
	}

	public void selectRow(String unitValue) {
		for (int i = 1; i <= vehicleListRows.size(); i++) {
			WebElement Unitvalueineachrow = driver.findElement(
					By.xpath("//table[@id='gvVehicleList']//tr[contains(@class,'odd') or contains(@class,'even')][" + i
							+ "]//td[contains(@class,'white')]"));
			highlightElement(driver, handImgsList.get(i - 1));
			highlightElement(driver, Unitvalueineachrow);
			if (FetchTextBoxValuewithText(Unitvalueineachrow).equals(unitValue)) {
				clickElement(handImgsList.get(i - 1));
				break;
			}
		}
	}

	public String fetchAmendVehicleLbl() {
		return FetchTextBoxValuewithText(vehicleAmendedVehiclesLbl);
	}

	public String fetchAmendVehicle() {
		return FetchTextBoxValuewithattribute(vehicleAmendedVehiclesTxt, "value");
	}

	public String fetchAddVehiclesLbl() {
		return FetchTextBoxValuewithText(vehicleAddedVehiclesLbl);
	}

	public String fetchAddVehicles() {
		return FetchTextBoxValuewithattribute(vehicleAddedVehiclesTxt, "value");
	}

	public String fetchDeleteVehicleLbl() {
		return FetchTextBoxValuewithText(vehicleDeleteVehiclesLbl);
	}

	public String fetchDeleteVehicle() {
		return FetchTextBoxValuewithattribute(vehicleDeleteVehiclesTxt, "value");
	}

	public String fetchRenewVehicleLbl() {
		return FetchTextBoxValuewithText(vehicleRenewVehiclesLbl);
	}

	public String fetchRenewVehicle() {
		return FetchTextBoxValuewithattribute(vehicleRenewVehiclesTxt, "value");
	}

	public void vehicleValidateMessage() {
		highlightElement(driver, vehicleValidateMessage);
	}


}
