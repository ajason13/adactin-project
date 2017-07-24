/**
 * File Name: Core.java<br>
 * Alvarez, Jason<br>
 * Java Boot Camp Exercise<br>
 * Instructor: Jean-francois Nepton<br>
 * Created: Jun 28, 2017
 */
package com.sqa.ja.auto;

import org.apache.log4j.*;
import org.openqa.selenium.*;

import com.sqa.ja.helpers.*;

/**
 * Core - Base class for everything test
 *
 * @author Alvarez, Jason
 * @version 1.0.0
 * @since 1.0
 */
public class Core {

	/**
	 * URL of website to test
	 */
	private String baseURL;

	/**
	 * Drivers available are FireFox, Chrome, and IE
	 */
	private WebDriver driver;

	/**
	 * Apache log4j, a logging library for Java
	 */
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * Appends to testingName to make screenshot filename unique
	 */
	private int screenshotCount = 1;

	/**
	 * Default screenshot name
	 */
	protected String testingName = "Auto Test";

	/**
	 * Initial setup and final teardown for any test
	 */
	private BasicTest relTest;

	/**
	 * Constructor with known {@link #driver} and {@link #baseURL} from
	 * BasicTest instance
	 *
	 * @param test
	 *            Uses {@link #driver} and {@link #baseURL} from BasicTest Class
	 */
	public Core(BasicTest test) {
		this.baseURL = test.getBaseURL();
		this.driver = test.getDriver();
		this.relTest = test;
	}

	/**
	 * Constructor with known baseURL and unknown WebDriver
	 *
	 * @param baseURL
	 *            See {@link #baseURL}
	 */
	public Core(String baseURL) {
		this.baseURL = baseURL;
		this.driver = null;
	}

	/**
	 * Constructor with known baseURL and driver
	 *
	 * @param baseURL
	 *            See {@link #baseURL}
	 * @param driver
	 *            See {@link #driver}
	 */
	public Core(String baseURL, WebDriver driver) {
		this.baseURL = baseURL;
		this.driver = driver;
	}

	/**
	 * @return the {@link #baseURL}
	 */
	public String getBaseURL() {
		return this.baseURL;
	}

	/**
	 * @return the {@link #driver}
	 */
	public WebDriver getDriver() {
		return this.driver;
	}

	/**
	 * @return the {@link #log}
	 */
	public Logger getLog() {
		return this.log;
	}

	/**
	 * For a given property in config.properties, return the property's value.
	 * Uses AutoBasics class getProp().
	 *
	 * @param propName
	 *            One of the field names in src/main/resouces/config.properties
	 * @return Value of property in src/main/resouces/config.properties
	 */
	public String getProp(String propName) {
		return AutoBasics.getProp(propName, "src/main/resources/", "config.properties", getLog());
	}

	/**
	 * Checks if a WebElement is present
	 *
	 * @param by
	 * @return True if Web Element exists or False if Web Element is not present
	 */
	public boolean isPresent(By by) {
		return AutoBasics.isElementPresent(getDriver(), by, getLog());
	}

	/**
	 * @param {@link
	 * 			#driver}
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Saves a screenshot of the current webpage with the default name (See
	 * {@link #testingName} and {@link #screenshotCount})
	 *
	 * @return True if screenshot successful and False if screenshot failed
	 */
	public boolean takeScreenshot() {
		boolean tookScreenShot = AutoBasics.takeScreenshot("screenshots", this.testingName + this.screenshotCount,
				getDriver(), getLog());
		if (tookScreenShot) {
			this.screenshotCount++;
		}
		return tookScreenShot;
	}

	/**
	 * Saves a screenshot of the current webpage
	 *
	 * @param fileName
	 *            File name for screenshot
	 * @return True if screenshot successful and False if screenshot failed
	 */
	public boolean takeScreenshot(String fileName) {
		return AutoBasics.takeScreenshot("screenshots", fileName, getDriver(), getLog());
	}

	/**
	 * A different format than regular toString()
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " driver=" + this.driver + ", baseURL=" + this.baseURL + ", log="
				+ this.log + "]";
	}
}
