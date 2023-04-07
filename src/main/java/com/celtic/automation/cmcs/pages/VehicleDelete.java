package com.celtic.automation.cmcs.pages;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class VehicleDelete extends ElementUtil {
	private WebDriver driver;
	private Logger logg;

	public VehicleDelete(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#contentMsg > div > ul")
	WebElement deleteVerificationMessage;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[1]")
	WebElement amendVehicleSupplementDetailsSubHdr;

	@FindBy(xpath = "//label[@for='NoOfDeletedVehicle']")
	WebElement deleteVehicleDeletedVehicleLbl;

	@FindBy(xpath = "//input[@id='NoOfDeletedVehicle']")
	WebElement deleteVehicleDeletedVehicleTxt;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[2]")
	WebElement amendVehicleSearchSubHdr;

	@FindBy(xpath = "//label[@for='UnitNbr']")
	WebElement deleteVehicleUnitNumberLbl;

	@FindBy(xpath = "//input[@id='UnitNbr']")
	WebElement deleteVehicleUnitNumberTxt;

	@FindBy(xpath = "//label[@for='VIN']")
	WebElement deleteVehicleVINLbl;

	@FindBy(xpath = "//input[@id='VIN']")
	WebElement deleteVehicleVINTxt;

	@FindBy(xpath = "//label[@for='PlateNbr']")
	WebElement deleteVehiclePlateNbrLbl;

	@FindBy(xpath = "//input[@id='PlateNbr']")
	WebElement deleteVehiclePlateNbrTxt;

	@FindBy(xpath = "//input[@id='btnFind']")
	WebElement deleteVehicleSearchBtn;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[3]")
	WebElement amendVehicleListOfActiveVehicleSubHdr;

	@FindBy(xpath = "//th[@aria-label='Select']")
	WebElement deleteVehicleSelectTbl;

	@FindBy(xpath = "//th[contains(@aria-label,'Unit')]")
	WebElement deleteVehicleUnitTbl;

	@FindBy(xpath = "//th[contains(@aria-label,'VIN')]")
	WebElement deleteVehicleVINTbl;

	@FindBy(xpath = "//th[contains(@aria-label,'Plate No')]")
	WebElement deleteVehiclePlateNoTbl;

	@FindBy(xpath = "//th[contains(@aria-label,'Plate Status')]")
	WebElement deleteVehiclePlateStatusTbl;

	@FindBy(xpath = "//th[contains(@aria-label,'Inactive')]")
	WebElement deleteVehicleInactiveDateTbl;

	@FindBy(xpath = "//th[contains(@aria-label,'Returned')]")
	WebElement deleteVehiclePlateReturnedDocumentTbl;

	@FindBy(xpath = "//th[contains(@aria-label,'Affidavit')]")
	WebElement deleteVehicleAffidavitDocumentTbl;

	@FindBy(xpath = "//th[contains(@aria-label,'Comment')]")
	WebElement deleteVehicleCommentTbl;

	@FindBy(xpath = "//th[contains(@aria-label,'Waive')]")
	WebElement deleteVehicleWaiveBasejurisdictionCreditTbl;

	@FindBy(xpath = "//input[contains(@id,'DeletedVehicleVMList') and  contains(@id,'SelectFlag')]")
	WebElement deleteVehicleCheckboxChk;

	@FindBy(xpath = "//select[contains(@id,'DeletedVehicleVMList') and  contains(@id,'PlateStatusFlag')]")
	WebElement deleteVehicleStatusPlatedd;

	@FindBy(xpath = "//input[contains(@name,'DeleteDate')]")
	WebElement deleteVehicleInactiveDateddPicker;

	@FindBy(xpath = "//select[contains(@id,'PlateReturnDocumentFlag')]")
	WebElement deleteVehiclePlateReturnedDocumenTdd;

	@FindBy(xpath = "//select[contains(@id,'AffidavitDoc')]")
	WebElement deleteVehicleAffidavitDocdd;

	@FindBy(xpath = "//textarea[contains(@id,'Comment')]")
	WebElement deleteVehicleCommentTxt;

	@FindBy(xpath = "//input[@id='btnVehicleList']")
	WebElement deleteVehicleVehicleListBtn;

	@FindBy(xpath = "//table[@id='DeleteVehicleGrid']/tbody/tr")
	List<WebElement> deleteVehicleRowInTable;

	@FindBy(xpath = "//td[contains(@class,'leftAlign')]")
	List<WebElement> deleteCellsInaRow;

	@FindBy(xpath = "//a[text()='Next']")
	WebElement nextLink;

	@FindBy(xpath = "//div[@id='DeleteVehicleGrid_info']")
	WebElement showInGentries;

	@FindBy(xpath = "//a[text()='First']")
	WebElement firstLink;

	@FindBy(xpath = "//a[text()='Previous']")
	WebElement previousLink;

	@FindBy(xpath = "//a[text()='Last']")
	WebElement lastLink;

	public void enterUnitNo(String unitNumbeTxtValue) {
		webEditTxtChange(deleteVehicleUnitNumberTxt, unitNumbeTxtValue);
	}

	public void clickonSearch() {
		clickElement(deleteVehicleSearchBtn);
	}

	public void clickFirstLink() {
		clickElement(firstLink);
	}

	public void clickPreviousLink() {
		clickElement(previousLink);
	}

	public void clickLastLink() {
		clickElement(lastLink);
	}

	public void clickNextLink() {
		clickElement(nextLink);
	}

	public void clickspecificpagenumberLink(int number) {
		clickElement(driver.findElement(By.xpath("//a[contains(text()," + number + ")]")));
	}

	@SuppressWarnings("unused")
	public int selectRowinaTable(String unitValue) {
		int i;
		int j;
		waitUntilElementsVisible(deleteVehicleRowInTable);
		for (i = 1; i <= deleteVehicleRowInTable.size(); i++) {
			List<WebElement> cellvalueineachrow = driver.findElements(
					By.xpath("//table[@id='DeleteVehicleGrid']/tbody/tr[" + i + "]//td[contains(@class,'leftAlign')]"));
			for (j = 1; j <= cellvalueineachrow.size(); j++) {
				if (driver
						.findElement(By.xpath("//table[@id='DeleteVehicleGrid']/tbody/tr[" + i
								+ "]//td[contains(@class,'leftAlign')][" + j + "]"))
						.getText().equalsIgnoreCase(unitValue))
					break;
			}
			break;
		}
		return i;
	}

	public void deleteFewVehicles(String vehiclesCount, String plateStatusValue, String plateReturnedValue,
			String affidavitDocumentValue, String deleteCommentsValue) throws InterruptedException {
		int j = 0;
		String showresultsTo = null;
		for (int i = 1; i <= Integer.valueOf(vehiclesCount); i++) {
			showresultsTo = FetchTextBoxValuewithText(showInGentries);
			if (i > Integer.valueOf(
					showresultsTo.substring(showresultsTo.indexOf("to ") + 3, showresultsTo.indexOf("of ") - 1))) {
				clickElement(nextLink);
				waitUntilElementClickable(deleteVehicleSearchBtn);
				i = i - 1;
			} else {
				showresultsTo = FetchTextBoxValuewithText(showInGentries);
				j = (i + 1 - (Integer.valueOf(showresultsTo
						.substring(showresultsTo.indexOf("Showing ") + 8, showresultsTo.indexOf("to")).trim())));
				WebElement checkBoxCheck = driver.findElement(
						By.xpath("//table[@id='DeleteVehicleGrid']/tbody/tr[" + j + "]/td[1]/input[@title='Select']"));
				clickElement(checkBoxCheck);

				WebElement plateStatus = driver
						.findElement(By.xpath("//table[@id='DeleteVehicleGrid']/tbody/tr[" + j + "]/td[5]//select"));
				selectFromDropdownByVisibleText(plateStatus, plateStatusValue);

				WebElement plateReturnedDocument = driver
						.findElement(By.xpath("//table[@id='DeleteVehicleGrid']/tbody/tr[" + j + "]/td[7]//select"));
				selectFromDropdownByVisibleText(plateReturnedDocument, plateReturnedValue);

				WebElement affidavitDocument = driver
						.findElement(By.xpath("//table[@id='DeleteVehicleGrid']/tbody/tr[" + j + "]/td[8]//select"));
				selectFromDropdownByVisibleText(affidavitDocument, affidavitDocumentValue);

				WebElement deleteComments = driver
						.findElement(By.xpath("//table[@id='DeleteVehicleGrid']/tbody/tr[" + j + "]/td[9]/textarea"));
				webEditTxtChange(deleteComments, deleteCommentsValue);
			}
		}
	}

	public void clickCheckBoxFromGrid() {
		clickElement(deleteVehicleCheckboxChk);
	}

	public void selectPlateStatus(String plateStatusValue) {
		selectFromDropdownByVisibleText(deleteVehicleStatusPlatedd, plateStatusValue);
	}

	public void selectPlateReturnedDocument(String plateReturnedDocumentValue) {
		selectFromDropdownByVisibleText(deleteVehiclePlateReturnedDocumenTdd, plateReturnedDocumentValue);
	}

	public void selectAffidavitDocument(String affidavitDocumentValue) {
		selectFromDropdownByVisibleText(deleteVehicleAffidavitDocdd, affidavitDocumentValue);
	}

	public void entercomments(String commentsValue) {
		webEditTxt(deleteVehicleCommentTxt, commentsValue);
	}

	public void clickVehicleList() {
		clickElement(deleteVehicleVehicleListBtn);
	}

	public String deleteValidationMessage() {
		highlightElement(driver, deleteVerificationMessage);
		return FetchTextBoxValuewithText(deleteVerificationMessage);
	}

}
