package com.celtic.automation.cmcs.pages;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class VehicleList extends ElementUtil {
	private WebDriver driver;
	private Logger logg;
	
	public VehicleList(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg=logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//table[@id='gvVehicleList']/tbody/tr")
	List<WebElement> vehicleList;

	public String fetchRequiredUnitNumber(String serviceType) {
		String unitNumberTextValue = null;
		for (int i = 1; i < vehicleList.size(); i++) {
			WebElement rowValues = driver
					.findElement(By.xpath("//table[@id='gvVehicleList']/tbody/tr[" + i + "]/td[5]"));
			String rowValue = FetchTextBoxValuewithText(rowValues);
			if (rowValue.contains(serviceType)) {
				WebElement unitNumber = driver
						.findElement(By.xpath("//table[@id='gvVehicleList']/tbody/tr[" + i + "]/td[2]"));
				unitNumberTextValue = FetchTextBoxValuewithText(unitNumber).trim();
			}
		}
		return unitNumberTextValue;
	}

}
