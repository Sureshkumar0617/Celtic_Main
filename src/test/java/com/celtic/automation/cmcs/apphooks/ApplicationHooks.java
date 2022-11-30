package com.celtic.automation.cmcs.apphooks;


import java.io.File;import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.celtic.automation.cmcs.factory.DriverFactory;
import com.celtic.automation.cmcs.util.ConfigReader;
import com.celtic.automation.cmcs.util.ElementUtil;
import com.celtic.automation.cmcs.util.GenericFunctions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	//public static Logger log = LogManager.getLogger(ApplicationHooks.class);
	Properties prop;
	private ConfigReader config =new ConfigReader();
	public static Scenario scenario;

	@Before
	public void launchBrowser(Scenario scenario) throws IOException {
		
				

		driverFactory = new DriverFactory();
		config.initprop();
		try {
			driver = driverFactory.initdriver(config.readBrowser());
		} catch (IOException e) {
			// TODO Auto-generated catch block

		}


	}
	@AfterStep
	public void as(Scenario scenario) throws Exception
	{
		scenario.attach(GenericFunctions.getByteScreenshot(), "image/png", "anyname");

	}


	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}




	@After(order = 1)
	public void tearDown(Scenario scenario) throws Exception {
		String exeTime = new SimpleDateFormat("ddMMYYYYHH").format(new Date());

		if (scenario.isFailed()) {
			// take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);

			TakesScreenshot ts = (TakesScreenshot)driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			String fileLocation=System.getProperty("user.dir")+"\\"+config.readPassedScreenshotFile();
			 
			String recentCreatedFile=ElementUtil.getfolder(fileLocation);
			File f = new File(recentCreatedFile);
			
			if(f.exists()) { 
				FileUtils.copyFile(source, new File(recentCreatedFile+"\\"+"Screenshot_Failed",screenshotName+".png"));	
			}
			else {
				FileUtils.copyFile(source, new File(fileLocation+"\\"+"Screenshot_Failed"+"\\"+exeTime,screenshotName+".png"));
			}
	
		}
	}
}
	