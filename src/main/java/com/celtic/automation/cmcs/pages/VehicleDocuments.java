package com.celtic.automation.cmcs.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class VehicleDocuments extends ElementUtil {

	private WebDriver driver;
	private Logger logg;

	public VehicleDocuments(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@title='Vehicle Documents']")
	WebElement dashboardVehicleDocuments;

	@FindBy(xpath = "//th[contains(@class,'Alignment')and not(contains(@class,'disabled')) and not(contains(@class,'hidden'))]")
	List<WebElement> vehicleTableHeader1;

	@FindBy(xpath = "//th[contains(@class,'Alignment sorting_disabled') and contains(@class,'mw')]")
	List<WebElement> vehicleTableHeader2;

	@FindBy(xpath = "//td[not(contains(@class,'hidden'))]//select[contains(@id,'DocStatInd') and not(contains(@disabled,'true'))]")
	List<WebElement> vehicleDocsdd;

	@FindBy(xpath = "//tr[@role='row']//td[contains(@class,'Alignment')and not(contains(@class,'hidden'))]")
	List<WebElement> vehicleRowValues;

	@FindBy(xpath = "//select[@doctype='HVUTForm2290']")
	List<WebElement> vehicleHVUTFormdd;

	@FindBy(xpath = "//select[@doctype='LeaseContract']")
	List<WebElement> vehicleLeaseContractdd;

	@FindBy(xpath = "//select[@doctype='TitleDocument']")
	List<WebElement> vehicleTitleDocumentdd;

	@FindBy(xpath = "//select[@doctype='PlateReturn']")
	List<WebElement> vehiclePlateReturndd;

	public void clickVehicleDocuments() throws InterruptedException {
		highlightElement(driver, dashboardVehicleDocuments);
		clickElementUsingActions(dashboardVehicleDocuments);
	}

	public void selectHVUT(String HVUTValue) {
		waitUntilElementsVisible(vehicleHVUTFormdd);
		for (int i = 0; i < vehicleHVUTFormdd.size(); i++) {
			selectFromDropdownByVisibleText(vehicleHVUTFormdd.get(i), HVUTValue);
		}
	}

	public void selectLeaseContract(String HVUTValue) {
		waitUntilElementsVisible(vehicleLeaseContractdd);
		for (int i = 0; i < vehicleLeaseContractdd.size(); i++) {
			selectFromDropdownByVisibleText(vehicleLeaseContractdd.get(i), HVUTValue);
		}
	}

	public void selectTitleDocument(String HVUTValue) {
		waitUntilElementsVisible(vehicleTitleDocumentdd);
		for (int i = 0; i < vehicleTitleDocumentdd.size(); i++) {
			selectFromDropdownByVisibleText(vehicleTitleDocumentdd.get(i), HVUTValue);
		}
	}

	public void selectPlateReturn(String HVUTValue) {
		waitUntilElementsVisible(vehiclePlateReturndd);
		for (int i = 0; i < vehiclePlateReturndd.size(); i++) {
			selectFromDropdownByVisibleText(vehiclePlateReturndd.get(i), HVUTValue);
		}
	}

	public ArrayList<String> fetchTableHeader1() {
		waitUntilElementsVisible(vehicleTableHeader1);
		ArrayList<String> headers1Array = new ArrayList<String>();
		for (int i = 0; i < vehicleTableHeader1.size(); i++) {
			headers1Array.add(FetchTextBoxValuewithText(vehicleTableHeader1.get(i)));
		}
		return headers1Array;
	}

	public ArrayList<String> fetchTableHeader2() {
		waitUntilElementsVisible(vehicleTableHeader2);
		ArrayList<String> headers2Array = new ArrayList<String>();
		for (int i = 0; i < vehicleTableHeader2.size(); i++) {
			headers2Array.add(FetchTextBoxValuewithText(vehicleTableHeader2.get(i)));
		}
		return headers2Array;
	}

	public ArrayList<String> fetchTableRows() {
		waitUntilElementsVisible(vehicleRowValues);
		ArrayList<String> rowArray = new ArrayList<String>();
		for (int i = 0; i < vehicleRowValues.size(); i++) {
			rowArray.add(FetchTextBoxValuewithText(vehicleRowValues.get(i)));
		}
		return rowArray;
	}

}
