package com.celtic.automation.cmcs.pages;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class WgtGroupAdd extends ElementUtil {

	private WebDriver driver;
	private Logger logg;

	public WgtGroupAdd(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[1]")
	WebElement addWgtGroupSupplementDetailsSubHdr;

	@FindBy(xpath = "//label[@for='WgtGroupType']")
	WebElement addWgtGroupWeightGroupTypeLbl;

	@FindBy(xpath = "//select[@id='WgtGroupType']")
	WebElement addWgtGroupWeightGroupTypedd;

	@FindBy(xpath = "//label[@for='WgtGroupNo']")
	WebElement addWgtGroupWeightGroupNoLbl;

	@FindBy(xpath = "//input[@id='WgtGroupNo']")
	WebElement weightGroupNoTxt;

	@FindBy(xpath = "//label[@for='MaxGrossWeight']")
	WebElement maxGrossWeightLbl;

	@FindBy(xpath = "//select[@id='MaxGrossWeight']")
	WebElement maxGrossWeightdd;

	@FindBy(xpath = "//label[@for='WgtGroupNoOld']")
	WebElement addWgtGroupCopyFromWeightLbl;

	@FindBy(xpath = "//input[@id='WgtGroupNoOld']")
	WebElement addWgtGroupCopyFromWeightTxt;

	@FindBy(xpath = "//input[@id='btnCopyFrom']")
	WebElement addWgtGroupCopyFromBtn;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[2]")
	WebElement addWgtGroupListOfJurandWeightSubHdr;

	@FindBy(xpath = "//input[contains(@id,'WeightList') and contains(@id,'WeightQ')]")
	List<WebElement> addWgtGroupWeightTxt;

	@FindBy(xpath = "//input[@id='btnGoToWeightGroupSelection']")
	WebElement goToWeightGroupSelectionBtn;

	@FindBy(xpath = "//input[@id='btnDeleteWeightGroup']")
	WebElement deleteWeightGroupBtn;

	@FindBy(xpath = "//input[contains(@id,'Juris')]/preceding-sibling::span")
	List<WebElement> editWgtGroupJurisList;

	@FindBy(xpath = "//input[contains(@id,'WeightQ')]")
	List<WebElement> editWgtGroupWeightTxt;

	public void selectWeightGroupType(String weightGroupTypeValue) {
		selectFromDropdownByVisibleText(addWgtGroupWeightGroupTypedd, weightGroupTypeValue); // B - BUS P - POWER UNIT T
	}

	public void enterWeightGroupNo(String weightGroupNoValue) {
		webEditTxtChange(weightGroupNoTxt, weightGroupNoValue);
	}

	public void selectMaxGrossWeight(String maxGrossWeightValue) {
		selectFromDropdownByVisibleText(maxGrossWeightdd, maxGrossWeightValue); // B - BUS P - POWER UNIT T - TRAILER
	}

	public void clickGoToWeightGroupSelection() {
		clickElement(goToWeightGroupSelectionBtn);
	}

	public void clickDeleteWeightGroup() {
		clickElement(deleteWeightGroupBtn);
	}

	public void enterWeightJuriValue(String juri) {
		waitUntilElementsVisible(editWgtGroupJurisList);
		int GrossWeightint = Integer.valueOf(fetchMaxGrossWeight());

		for (int i = 0; i < editWgtGroupJurisList.size(); i++) {
			if (FetchTextBoxValuewithText(editWgtGroupJurisList.get(i)).equalsIgnoreCase(juri)) {
				webEditTxtChange(editWgtGroupWeightTxt.get(i), String.valueOf(GrossWeightint - 1));
				break;
			}
		}
	}

	public void enterAllWeightValue(String distanceValueDynamic) {
		waitUntilElementsVisible(editWgtGroupWeightTxt);
		for (int i = 0; i < editWgtGroupWeightTxt.size(); i++) {
			webEditTxtChange(editWgtGroupWeightTxt.get(i), distanceValueDynamic);
		}
	}

	public String fetchMaxGrossWeight() {
		String GrossWeight = FetchDropdownSelectedValue(maxGrossWeightdd);
		return GrossWeight;
	}

}
