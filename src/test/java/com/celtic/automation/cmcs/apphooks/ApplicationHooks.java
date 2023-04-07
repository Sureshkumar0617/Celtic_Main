package com.celtic.automation.cmcs.apphooks;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.celtic.automation.cmcs.factory.DriverFactory;
import com.celtic.automation.cmcs.util.ElementUtil;
import com.celtic.automation.cmcs.util.GenericFunctions;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks extends DriverFactory {
	public WebDriver driver;
	public String browser;
	Properties prop;

	//@Before(value="@RWC001")
	public void launchBrowser(Scenario scenario) throws Exception {
		if (scenario.getName().toLowerCase().contains("chrome")) {
			browser = "chrome";
		} else if (scenario.getName().toLowerCase().contains("chromeincognito")) {
			browser = "chrome-incognito";
		} else if (scenario.getName().toLowerCase().contains("edge")) {
			browser = "edge";
		} else if (scenario.getName().toLowerCase().contains("firefox")) {
			browser = "firefox";
		}
		initdriver(browser);
	}

	//@AfterStep(value="@RWC001")
	public void as(Scenario scenario) throws Exception {
		scenario.attach(new GenericFunctions().getByteScreenshot(), "image/png", "anyname");
	}

	//@After(value="@RWC001")
	public void tearDown(Scenario scenario) throws Exception {
		String exeTime = new SimpleDateFormat("ddMMYYYYHH").format(new Date());
		try {
			if (scenario.isFailed()) {
				String screenshotName = scenario.getName().replaceAll(" ", "_");
				byte[] sourcePath = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
				scenario.attach(sourcePath, "image/png", screenshotName);

				TakesScreenshot ts = (TakesScreenshot) getDriver();
				File source = ts.getScreenshotAs(OutputType.FILE);
				String fileLocation = System.getProperty("user.dir") + "\\" + "test-output\\";

				String recentCreatedFile = ElementUtil.getfolder(fileLocation);
				File f = new File(recentCreatedFile);

				if (f.exists()) {
					FileUtils.copyFile(source,
							new File(recentCreatedFile + "\\" + "Screenshot_Failed", screenshotName + ".png"));
				} else {
					f.mkdir();
					FileUtils.copyFile(source, new File(fileLocation + "\\" + "Screenshot_Failed" + "\\" + exeTime,
							screenshotName + ".png"));
				}
			}
		} catch (Exception e) {

		}
	}

}
