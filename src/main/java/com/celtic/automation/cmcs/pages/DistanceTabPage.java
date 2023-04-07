package com.celtic.automation.cmcs.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class DistanceTabPage extends ElementUtil {

	private WebDriver driver;
	private Logger logg;

	public DistanceTabPage(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#contentMsg > div > ul > li:nth-child(1) > span")
	WebElement distanceVerificationMsg1;

	@FindBy(css = "#contentMsg > div > ul > li:nth-child(2) > span")
	WebElement distanceVerificationMsg2;

	@FindBy(css = "#contentMsg > div > ul > li > span")
	WebElement distanceVerificationMsg3;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[2]")
	WebElement distanceDistanceDetailsSubhdr;

	@FindBy(xpath = "//label[@for='ReportingPeriodFrom']")
	WebElement distanceReportingperiodFromLbl;

	@FindBy(xpath = "//input[@id='ReportingPeriodFrom']")
	WebElement distanceReportingperiodFromTxt;

	@FindBy(xpath = "//label[@for='ReportingPeriodTo']")
	WebElement distanceReportingperiodToLbl;

	@FindBy(xpath = "//input[@id='ReportingPeriodTo']")
	WebElement distanceReportingperiodToTxt;

	@FindBy(xpath = "//label[@for='UsdotNbr']")
	WebElement distanceUSDOTNoLbl;

	@FindBy(xpath = "//input[@id='UsdotNbr']")
	WebElement distanceUSDOTNoFromTxt;

	@FindBy(xpath = "//label[@for='EstimatedDistanceChart']")
	WebElement distanceVehicleDistanceChartLbl;

	@FindBy(xpath = "//input[@id='EstimatedDistanceChart']")
	WebElement distanceVehicleDistanceChartChk;

	@FindBy(xpath = "//label[@for='OverrideContJur']")
	WebElement distanceOverrideContJurLbl;

	@FindBy(xpath = "//input[@id='OverrideContJur']")
	WebElement distanceOverrideContJurChk;

	@FindBy(xpath = "//label[@for='EstimatedDistance']")
	WebElement distanceEstimatedDistanceLbl;

	@FindBy(xpath = "//input[@id='EstimatedDistance']")
	WebElement distanceEstimatedDistanceTxt;

	@FindBy(xpath = "//label[@for='EstimatedDistanceGen']")
	WebElement distanceEstimatedDistance1Lbl;

	@FindBy(xpath = "//input[@id='EstimatedDistanceGen']")
	WebElement distanceEstimatedDistance1Txt;

	@FindBy(xpath = "//label[@for='ActualDistance']")
	WebElement distanceActualDistanceLbl;

	@FindBy(xpath = "//input[@id='ActualDistance']")
	WebElement distanceActualDistanceTxt;

	@FindBy(xpath = "//label[@for='ActualDistanceGen']")
	WebElement distanceActualDistance1Lbl;

	@FindBy(xpath = "//input[@id='ActualDistanceGen']")
	WebElement distanceActualDistance1Txt;

	@FindBy(xpath = "//label[@for='TotalFleetDistance']")
	WebElement distanceTotalFleetDistanceLbl;

	@FindBy(xpath = "//input[@id='TotalFleetDistance']")
	WebElement distanceTotalFleetDistanceTxt;

	@FindBy(xpath = "//label[@for='TotalFleetDistanceGen']")
	WebElement distanceTotalFleetDistance1Lbl;

	@FindBy(xpath = "//input[@id='TotalFleetDistanceGen']")
	WebElement distanceTotalFleetDistance1Txt;

	@FindBy(xpath = "//label[@for='FRPMlgQuetion']")
	WebElement distanceReportingPeriodQuestionLbl;

	@FindBy(xpath = "//input[@id='FRPMlgQuetion']")
	List<WebElement> distanceReportingPeriodQuestionrd;

	@FindBy(xpath = "//input[@id='FRPMlgQuetion' and @value='Y']")
	WebElement distanceQuestionYes;

	@FindBy(xpath = "//input[@id='FRPMlgQuetion' and @value='N']")
	WebElement distanceQuestionNo;

	@FindBy(xpath = "//label[@for='DistanceType']")
	WebElement distanceDistanceTypeLbl;

	@FindBy(xpath = "//select[@id='DistanceType']")
	WebElement distanceDistanceTypedd;

	@FindBy(xpath = "//label[@for='ActualDistConfirmation']")
	WebElement distanceActualDistanceQuestionLbl;

	@FindBy(xpath = "//input[@id='ActualDistConfirmation']")
	WebElement distanceActualDistanceQuestionChk;

	@FindBy(xpath = "//a[@id='showMapId']")
	WebElement distanceshowMaplnk;

	@FindBy(xpath = "(//th[@class='leftAlign'])[1]")
	WebElement distanceJurisdictionTbl;

	@FindBy(xpath = "(//th[@class='rightAlign'])[1]")
	WebElement distancedistanceTbl;

	@FindBy(xpath = "(//th[@scope='col'])[1]")
	WebElement distancePercentTbl;

	@FindBy(xpath = "//input[@id='MileageDataVMList_0__JurCode']//preceding-sibling::span")
	WebElement distanceJuri0;

	@FindBy(xpath = "//input[@id='MileageDataVMList_0__Mileage']")
	WebElement distanceJuri0Value;

	@FindBy(xpath = "//input[contains(@id,'MileageDataVMList')and contains(@id,'JurCode')]//preceding-sibling::span")
	List<WebElement> distanceJurisList;

	@FindBy(xpath = "//input[contains(@class,'rightAlign') and contains(@id,'MileageDataVMList')]")
	List<WebElement> distanceDistanceJurisList;

	@FindBy(xpath = "//a[@id='MileageBtn']")
	WebElement distanceDistanceTab;

	@FindBy(xpath = "//h3[@class='float-left']")
	WebElement distanceReinstateSubhdr;

	@FindBy(xpath = "//table[@id='dtlstJurisdiction']//tr/th[not(contains(@class,'hidden'))]")
	List<WebElement> distanceJurisTableHeader; // take text

	@FindBy(xpath = "//input[contains(@id,'MileageDataVMList') and contains(@id,'_JurCode')]/preceding-sibling::span")
	List<WebElement> distanceJuris; // take text

	@FindBy(xpath = "//input[contains(@id,'MileageDataVMList') and contains(@id,'_Mileage') and not (contains(@id,'Type'))]")
	List<WebElement> distanceDistance; // text

	@FindBy(xpath = "//input[contains(@id,'MileageDataVMList') and contains(@id,'Percent')]/preceding-sibling::span")
	List<WebElement> distancePercent; // text

	@FindBy(xpath = "//table[@id='dtlstJurisdiction']//tr/th[not(contains(@class,'hidden')) and contains(text(),'Jur')]")
	WebElement distanceJurisTableHeaderJuri; // take text

	@FindBy(xpath = "//table[@id='dtlstJurisdiction']//tr/th[not(contains(@class,'hidden')) and contains(text(),'Dist')]")
	WebElement distanceJurisTableHeaderDistance; // take text

	@FindBy(xpath = "//table[@id='dtlstJurisdiction']//tr/th[not(contains(@class,'hidden')) and contains(text(),'Percent')]")
	WebElement distanceJurisTableHeaderPercent; // take text

	public void selectYesOrNo(String selectvalue) {
		waitUntilElementsVisible(distanceReportingPeriodQuestionrd);
		for (WebElement a : distanceReportingPeriodQuestionrd) {
			if (FetchTextBoxValuewithattribute(a, "value").equalsIgnoreCase(selectvalue)) {
				clickElement(a);
				break;
			}
		}
	}

	public void enterMODistanceValue(String juri, String distanceValuedynamic) {
		waitUntilElementsVisible(distanceJurisList);
		for (int i = 0; i < distanceJurisList.size(); i++) {
			Boolean valuexists = validateTextbox(distanceDistanceJurisList.get(i));
			if (FetchTextBoxValuewithText(distanceJurisList.get(i)).equalsIgnoreCase(juri) && valuexists == false) {
				webEditTxtChange(distanceDistanceJurisList.get(i), distanceValuedynamic);
				break;
			}
		}
	}

	public void enterAllDistanceValue(String distanceValuedynamic) {
		waitUntilElementsVisible(distanceJurisList);
		for (int i = 0; i < distanceDistanceJurisList.size(); i++) {
			webEditTxtChange(distanceDistanceJurisList.get(i), distanceValuedynamic);
		}
	}

	public void enterEstimatedDistance(String estimatedDistanceValue) {
		webEditTxtChange(distanceEstimatedDistanceTxt, estimatedDistanceValue);
	}

	public void validateDistancetab(String textmsg) {
		if (FetchTextBoxValuewithText(distanceDistanceTab).contains(textmsg) == true) {
			assert true;
		} else {
			assert false;
		}
	}

	public void validatesubhdr(String hdrvalue) {
		if (FetchTextBoxValuewithText(distanceReinstateSubhdr).contains(hdrvalue))
			assert true;
	}

	public String distanceReportingPeriodFromLbl() {
		return FetchTextBoxValuewithText(distanceReportingperiodFromLbl);
	}

	public String distanceReportingPeriodFrom() {
		return FetchTextBoxValuewithattribute(distanceReportingperiodFromTxt, "value");
	}

	public String distanceReportingPeriodToLbl() {
		return FetchTextBoxValuewithText(distanceReportingperiodToLbl);
	}

	public String distanceReportingPeriodTo() {
		return FetchTextBoxValuewithattribute(distanceReportingperiodToTxt, "value");
	}

	public String distanceUsdotNbrLbl() {
		return FetchTextBoxValuewithText(distanceUSDOTNoLbl);
	}

	public String distanceUsdotNbr() {
		return FetchTextBoxValuewithattribute(distanceUSDOTNoFromTxt, "value");
	}

	public String distanceEstimatedDistanceChartLbl() {
		return FetchTextBoxValuewithText(distanceVehicleDistanceChartLbl);
	}

	public String distanceEstimatedDistanceChart() {
		boolean boolstatus = false;
		if (FetchTextBoxValuewithattributedisabled(distanceVehicleDistanceChartChk, "checked") == null) {
			boolstatus = false;
		} else {
			boolstatus = true;
		}
		return Boolean.toString(boolstatus);
	}

	public String distanceOverrideContJurLbl() {
		return FetchTextBoxValuewithText(distanceOverrideContJurLbl);
	}

	public String distanceOverrideContJur() {
		boolean boolstatus = false;
		if (FetchTextBoxValuewithattribute(distanceOverrideContJurChk, "checked") == null) {
			boolstatus = false;
		} else {
			boolstatus = true;
		}
		return Boolean.toString(boolstatus);
	}

	public String distanceEstimatedDistanceLbl() {
		return FetchTextBoxValuewithText(distanceEstimatedDistanceLbl);
	}

	public String distanceEstimatedDistance() {
		return FetchTextBoxValuewithattribute(distanceEstimatedDistanceTxt, "value");
	}

	public String distanceActualDistanceLbl() {
		return FetchTextBoxValuewithText(distanceActualDistanceLbl);
	}

	public String distanceActualDistance() {
		return FetchTextBoxValuewithattribute(distanceActualDistanceTxt, "value");
	}

	public String distanceTotalFleetDistanceLbl() {
		return FetchTextBoxValuewithText(distanceTotalFleetDistanceLbl);
	}

	public String distanceTotalFleetDistance() {
		return FetchTextBoxValuewithattribute(distanceTotalFleetDistanceTxt, "value");
	}

	public String distanceFRPMlgQuetionLbl() {
		return FetchTextBoxValuewithText(distanceReportingPeriodQuestionLbl);
	}

	public String distanceFRPMlgQuetion() {
		String value;
		if (!(FetchTextBoxValuewithattribute(distanceQuestionYes, "checked").isEmpty())) {
			value = FetchTextBoxValuewithattribute(distanceQuestionYes, "value");
		} else {
			value = FetchTextBoxValuewithattribute(distanceQuestionNo, "value");
		}
		return value;
	}

	public String distanceDistanceTypeLbl() {
		return FetchTextBoxValuewithText(distanceDistanceTypeLbl);
	}

	public String distanceDistanceType() {
		return FetchTextBoxValuewithattribute(distanceDistanceTypedd, "value");
	}

	public String distanceActualDistConfirmationLbl() {
		return FetchTextBoxValuewithText(distanceActualDistanceQuestionLbl);
	}

	public String distanceActualDistConfirmation() {
		boolean boolstatus = false;
		if (FetchTextBoxValuewithattribute(distanceActualDistanceQuestionChk, "checked") == null) {
			boolstatus = false;
		} else {
			boolstatus = true;
		}
		return Boolean.toString(boolstatus);
	}

	public ArrayList<String> fetchTableHeader() {
		waitUntilElementsVisible(distanceJurisTableHeader);
		ArrayList<String> headersArray = new ArrayList<String>();
		for (int i = 0; i < distanceJurisTableHeader.size(); i++) {
			headersArray.add(FetchTextBoxValuewithText(distanceJurisTableHeader.get(i)));
		}
		return headersArray;
	}

	public ArrayList<String> fetchTableJuris() {
		waitUntilElementsVisible(distanceJuris);
		ArrayList<String> jurisArray = new ArrayList<String>();
		for (int i = 0; i < distanceJuris.size(); i++) {
			jurisArray.add(FetchTextBoxValuewithText(distanceJuris.get(i)));
		}
		return jurisArray;
	}

	public ArrayList<String> fetchTablePercent() {
		waitUntilElementsVisible(distancePercent);
		ArrayList<String> percentArray = new ArrayList<String>();
		for (int i = 0; i < distancePercent.size(); i++) {
			percentArray.add(FetchTextBoxValuewithText(distancePercent.get(i)));
		}
		return percentArray;
	}

	public ArrayList<String> fetchTableDistanceMiles() {
		waitUntilElementsVisible(distanceDistance);
		ArrayList<String> distanceMilesArray = new ArrayList<String>();
		for (int i = 0; i < distanceDistance.size(); i++) {
			distanceMilesArray.add(FetchTextBoxValuewithattribute(distanceDistance.get(i), "value"));
		}
		return distanceMilesArray;
	}

	public String distanceValidateMessage1() {
		highlightElement(driver, distanceVerificationMsg1);
		return FetchTextBoxValuewithText(distanceVerificationMsg1);
	}

	public String distanceValidateMessage2() {
		highlightElement(driver, distanceVerificationMsg2);
		return FetchTextBoxValuewithText(distanceVerificationMsg2);
	}

	public String distanceValidateMessage3() {
		highlightElement(driver, distanceVerificationMsg3);
		return FetchTextBoxValuewithText(distanceVerificationMsg3);
	}

	public ArrayList<String> fetchTableDistanceMile() {
		waitUntilElementsVisible(distanceDistance);
		ArrayList<String> distanceMilesArray = new ArrayList<String>();
		for (int i = 0; i < distanceDistance.size(); i++) {
			distanceMilesArray.add(FetchTextBoxValuewithattribute(distanceDistance.get(i), "value"));
		}
		return distanceMilesArray;
	}

	public ArrayList<String> fetchTablPercent() {
		waitUntilElementsVisible(distancePercent);
		ArrayList<String> percentArray = new ArrayList<String>();
		for (int i = 0; i < distancePercent.size(); i++) {
			percentArray.add(FetchTextBoxValuewithText(distancePercent.get(i)));
		}
		return percentArray;
	}

	public ArrayList<String> fetchTablJuris() {
		waitUntilElementsVisible(distanceJuris);
		ArrayList<String> jurisArray = new ArrayList<String>();
		for (int i = 0; i < distanceJuris.size(); i++) {
			jurisArray.add(FetchTextBoxValuewithText(distanceJuris.get(i)));
		}
		return jurisArray;
	}

	public String distanceJurisTableHeaderPercent() {
		return FetchTextBoxValuewithText(distanceJurisTableHeaderPercent);
	}

	public String distanceJurisTableHeaderDistance() {
		return FetchTextBoxValuewithText(distanceJurisTableHeaderDistance);
	}

	public String distanceJurisTableHeaderJuri() {
		return FetchTextBoxValuewithText(distanceJurisTableHeaderJuri);
	}

}

