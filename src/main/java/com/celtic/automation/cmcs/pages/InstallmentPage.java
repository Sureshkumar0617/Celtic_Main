package com.celtic.automation.cmcs.pages;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.celtic.automation.cmcs.util.ElementUtil;

public class InstallmentPage extends ElementUtil {

	private WebDriver driver;
	private Logger logg;

	public InstallmentPage(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#contentMsg > div > ul > li:nth-child(1) > span")
	WebElement installmentPaymentMessage1;

	@FindBy(css = "#contentMsg > div > ul > li:nth-child(2) > span")
	WebElement installmentPaymentMessage2;

	@FindBy(xpath = "//a[@title='Installment Payment']")
	WebElement installmentPayment;

	@FindBy(xpath = "//a[@id='lnkGridSelectCustomerGrid']")
	WebElement installmentPaymentGrid;

	public void clickInstallmentPayment() {
		clickElement(installmentPayment);
	}

	public void clickGrid() {
		clickElement(installmentPaymentGrid);
	}

	public String validateMessage1() {
		highlightElement(driver, installmentPaymentMessage1);
		return FetchTextBoxValuewithText(installmentPaymentMessage1);
	}

	public String validateMessage2() {
		highlightElement(driver, installmentPaymentMessage2);
		return FetchTextBoxValuewithText(installmentPaymentMessage2);

	}

}
