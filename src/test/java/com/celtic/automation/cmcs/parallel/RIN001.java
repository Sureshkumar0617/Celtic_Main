package com.celtic.automation.cmcs.parallel;


import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.celtic.automation.cmcs.factory.DriverFactory;
import com.celtic.automation.cmcs.pages.BillingTab;
import com.celtic.automation.cmcs.pages.CommonObjects;
import com.celtic.automation.cmcs.pages.DashBoardPage;
import com.celtic.automation.cmcs.pages.FleetPage;
import com.celtic.automation.cmcs.pages.LoginPage;
import com.celtic.automation.cmcs.pages.Reinstatement;
import com.celtic.automation.cmcs.util.ConfigReader;
import com.celtic.automation.cmcs.util.ElementUtil;
import com.celtic.automation.cmcs.util.ReadExcelUtil;
import com.celtic.automation.cmcs.util.ScreenshotUtility;
import com.celtic.automation.cmcs.util.WriteExcelUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class RIN001 {
	private LoginPage loginpage = new LoginPage(DriverFactory.getDriver());
	private DashBoardPage dashboardpage = new DashBoardPage(DriverFactory.getDriver());
	private FleetPage fleetpage = new FleetPage(DriverFactory.getDriver());
	private CommonObjects commonobjects = new CommonObjects(DriverFactory.getDriver());
	private BillingTab billingtab = new BillingTab(DriverFactory.getDriver());
	private ConfigReader config=new ConfigReader();
	private ElementUtil eleutil;
	private	int readRowNo=3;
	private int writeRowNo=4;
	private ReadExcelUtil excelutil=null;
	private WriteExcelUtil excelutilWrite=null;
	private Logger log ;
	private ScreenshotUtility screenshotUtil =new ScreenshotUtility();
	private Reinstatement reinstate =new Reinstatement(DriverFactory.getDriver());
	private String className = this.getClass().getName().split("[.]")[1];
	
@Given("User login as an Internal user")
public void user_login_as_an_internal_user() throws Exception {
	
		//excelutil = new ReadExcelUtil(config.readRwcExcel());
	//excelutilWrite=new WriteExcelUtil();
	
	config.loggerConfigurator(new Throwable().getStackTrace()[0].getClassName());
	config.initprop();
	excelutil = new ReadExcelUtil(config.readRwcExcel(),ConfigReader.log);
	excelutilWrite=new WriteExcelUtil(ConfigReader.log);
	eleutil =new ElementUtil(ConfigReader.log);

	excelutilWrite.setCellData(config.writeRinExcel(),"Sheet1","Account",writeRowNo,"30942");

	CommonStep.scenario.log("Launch the application using URL and login with valid credentials");
	DriverFactory.getDriver().get(config.readLoginURL());
	ConfigReader.getLogInfo("****************************** Login to the application  *****************************************");
	screenshotUtil.captureScreenshot(className,"ApplicationLogin");
	loginpage.enterUsername(config.readLoginInternalUser());
	ConfigReader.getLogInfo("*** Enter Username ***");
	screenshotUtil.captureScreenshot(className,"Username");
	loginpage.enterPassword(config.readPswrd());
	ConfigReader.getLogInfo("*** Enter Password ***");
	screenshotUtil.captureScreenshot(className,"Password");
	loginpage.clickLoginBtn();
	ConfigReader.getLogInfo("*** Click Login ***");
	screenshotUtil.captureScreenshot(className,"Login");
}
	@When("User will navigate to IRP & Reinstate Fleet")
	public void user_will_navigate_to_irp_reinstate_fleet() throws Exception {
		CommonStep.scenario.log("Expand the Services header on the left column of the screen and select IRP");
		dashboardpage.clickIRPLink();
		log.info("*** Click IRP ***");
		screenshotUtil.captureScreenshot(className,"IRP");
	
		CommonStep.scenario.log("Navigate to Fleet menu .Click on More and select Fleet Reinstatement");
		commonobjects.waitForSpinner();
		dashboardpage.clickFleetMore();
		log.info("*** Click Fleet More ***");
		screenshotUtil.captureScreenshot(className,"FleetMore");
		
		
		dashboardpage.clickFleetReinstatement();
		log.info("*** Click Fleet Reinstatement ***");
		screenshotUtil.captureScreenshot(className,"FleetReinstatement");
		commonobjects.waitForSpinner();
	}

	@Then("User will provide all the inputs & select the record on the grid")
	public void user_will_provide_all_the_inputs_select_the_record_on_the_grid() throws Exception {
		
		CommonStep.scenario.log("Enter the fleet details having inactive duration of 18 months or more .(ie the fleet was last active in June 2019 if current year is 2020) & hit the proceed button");
		fleetpage.enterAccountNo(excelutil.getCellData("PreSetup","AccountNumber",readRowNo));
		log.info("*** Enter Account Number ***");
		screenshotUtil.captureScreenshot(className,"Entering AccountNumber");
	
		fleetpage.enterLastInactiveDays(excelutil.getCellData("PreSetup","InactiveDate",readRowNo));
		log.info("*** Enter Last Inactive Days ***");
		screenshotUtil.captureScreenshot(className,"Entering Last Inactive Days");
	
		CommonStep.scenario.log("Select the record displayed on search page");
		commonobjects.clickProceed();	
		//select 1st record
		fleetpage.clickFirstHandimg();
		
	}

	@Then("User will navigate to Distance page & Proceed")
	public void user_will_navigate_to_distance_page_proceed() throws IOException, Exception {
		//Land on the Distance page
		//distancetabpage.validatesubhdr(ExcelReader.FetchDataFromSheet(ConfigReader.readRINexcel(),"PreSetup",rownum,2));
		CommonStep.scenario.log("Check the details on distance page and proceed on billing page");
		
		log.info(commonobjects.validateInfoMsgs());
		excelutilWrite.setCellData(config.writeRinExcel(),"Distance",reinstate.distanceReportingPeriodFromlbl(),writeRowNo,reinstate.distanceReportingPeriodFrom());
		excelutilWrite.setCellData(config.writeRinExcel(),"Distance",reinstate.distanceReportingPeriodTolbl(), writeRowNo,reinstate.distanceReportingPeriodTo());
		excelutilWrite.setCellData(config.writeRinExcel(),"Distance",reinstate.distanceUsdotNbrlbl(), writeRowNo,reinstate.distanceUsdotNbr());
		excelutilWrite.setCellData(config.writeRinExcel(),"Distance",reinstate.distanceEstimatedDistanceChartlbl(), writeRowNo,reinstate.distanceEstimatedDistanceChart());
		excelutilWrite.setCellData(config.writeRinExcel(),"Distance",reinstate.distanceOverrideContJurlbl(), writeRowNo,reinstate.distanceOverrideContJur());
		excelutilWrite.setCellData(config.writeRinExcel(),"Distance",reinstate.distanceEstimatedDistancelbl(), writeRowNo,reinstate.distanceEstimatedDistance());
		excelutilWrite.setCellData(config.writeRinExcel(),"Distance",reinstate.distanceActualDistancelbl(), writeRowNo,reinstate.distanceActualDistance());
		excelutilWrite.setCellData(config.writeRinExcel(),"Distance",reinstate.distanceTotalFleetDistancelbl(), writeRowNo,reinstate.distanceTotalFleetDistance());
		excelutilWrite.setCellData(config.writeRinExcel(),"Distance",reinstate.distanceFRPMlgQuetionlbl(), writeRowNo,reinstate.distanceFRPMlgQuetion());
		excelutilWrite.setCellData(config.writeRinExcel(),"Distance",reinstate.distanceDistanceTypelbl(), writeRowNo,reinstate.distanceDistanceType());
		excelutilWrite.setCellData(config.writeRinExcel(),"Distance",reinstate.distanceActualDistConfirmationlbl(), writeRowNo,reinstate.distanceActualDistConfirmation());

		commonobjects.clickProceed();
				}

	@Then("User will navigate to billing Page & provide mandatory inputs & proceed")
	public void user_will_navigate_to_billing_page_provide_mandatory_inputs_proceed() throws IOException, Exception {
	
		CommonStep.scenario.log("Check the details displayed on the billing page");
		//land on the billing page
		billingtab.enterreceiptdate(excelutil.getCellData("Billing","LastReceiptDate",readRowNo));
		log.info("*** Enter  Receipt  Date ***");
		screenshotUtil.captureScreenshot(className,"Entering Receipt Date");
	CommonStep.scenario.log("After verifying the details click on proceed button");	
		commonobjects.clickProceed();
	}

	@Then("User will validate the Success Information Message")
	public void user_will_validate_the_success_information_message() throws IOException, Exception {
		//Validating the information message
		commonobjects.validateInfoMessage(excelutil.getCellData("Billing","InformationMessage",readRowNo));
		log.info("*** Validating the information Message ***");
		screenshotUtil.captureScreenshot(className,"Validating Information Message");
		
		dashboardpage.clickLogout();

	}
	
}
