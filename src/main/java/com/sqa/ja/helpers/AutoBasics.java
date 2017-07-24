/**
 * File Name: AutoBasics.java<br>
 * Alvarez, Jason<br>
 * Java Boot Camp Exercise<br>
 * Instructor: Jean-francois Nepton<br>
 * Created: Jun 28, 2017
 */
package com.sqa.ja.helpers;

import java.io.*;
import java.util.*;
import java.util.NoSuchElementException;

import org.apache.commons.io.*;
import org.apache.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

/**
 * AutoBasics - Helper functions to get a list of WebElements, check for
 * WebElements, take a screenshot, and read a config file
 *
 * @author Alvarez, Jason
 * @version 1.0.0
 * @since 1.0
 */
public class AutoBasics {

	/**
	 * Find WebElements by tag name
	 *
	 * @param driver
	 *            WebDriver for Chrome, FireFox, or IE
	 * @param tagName
	 *            Tag name to find on webpage
	 * @return A list of WebElements based on tag name
	 */
	public static List<WebElement> getByTagName(WebDriver driver, String tagName) {
		return driver.findElements(By.tagName(tagName));
	}

	/**
	 * Find WebElements based on CSS property and value
	 *
	 * @param driver
	 *            WebDriver for Chrome, Firefox, or IE
	 * @param locator
	 *            Locates objects in webpage
	 * @param prop
	 *            The CSS property to find
	 * @param value
	 *            The value in the CSS property to find
	 * @return A list of Elements based on CSS property and value
	 */
	public static List<WebElement> getCSSPropBasedElements(WebDriver driver, By locator, String prop, String value) {
		List<WebElement> elements = driver.findElements(locator);
		ArrayList<WebElement> filteredElements = new ArrayList<WebElement>();
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getCssValue(prop).equalsIgnoreCase(value)) {
				filteredElements.add(elements.get(i));
			}
		}
		return filteredElements;
	}

	/**
	 * Returns all links on current webpage
	 *
	 * @param driver
	 *            WebDriver for Chrome, FireFox, or IE
	 * @return A list of links of the current webpage
	 */
	public static List<WebElement> getLinks(WebDriver driver) {
		return driver.findElements(By.tagName("a"));
	}

	/**
	 * Returns all pictures on current webpage
	 *
	 * @param driver
	 *            WebDriver for Chrome, FireFox, or IE
	 * @return All pictures of the current webpage
	 */
	public static List<WebElement> getPictures(WebDriver driver) {
		return driver.findElements(By.tagName("img"));
	}

	/**
	 * Locates a file, searches for a field in that file, and return the field's
	 * value
	 *
	 * @param propName
	 *            Field to find
	 * @param fileLocation
	 *            Path of file
	 * @param fileName
	 *            File name
	 * @param logger
	 *            Log4j logger
	 * @return Field's value
	 */
	public static String getProp(String propName, String fileLocation, String fileName, Logger logger) {
		Properties props = new Properties();
		InputStream input;
		try {
			input = new FileInputStream(fileLocation + fileName);
			props.load(input);
		} catch (FileNotFoundException e) {
			logger.warn("Can not load config properties file because it was not found: " + fileName);
		} catch (IOException e) {
			logger.warn("Can not load config properties file can not be read: " + fileName);
		}
		return props.getProperty(propName);
	}

	/**
	 * Under construction
	 *
	 * @param driver
	 *            WebDriver for Chrome, FireFox, or IE
	 * @param locator
	 *            Locates objects in a webpage
	 * @return Under Construction
	 */
	public static List<String> getTextContents(WebDriver driver, By locator) {
		return null;
	}

	/**
	 * Check if a WebElement is on webpage
	 *
	 * @param driver
	 *            WebDriver for Chrome, FireFox, or IE
	 * @param by
	 *            WebElement to find
	 * @param logger
	 *            Log4j logger
	 * @return True if WebElement is on webpage or False if WebElement not on
	 *         webpage
	 */
	public static boolean isElementPresent(WebDriver driver, By by, Logger logger) {
		try {
			WebElement element = driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			logger.warn("Element was not found: " + by);
			return false;
		}
	}

	/**
	 * Select a value in a dropdown men WebElement
	 *
	 * @param dropDown
	 *            Dropdown menu in webpage
	 * @param value
	 *            Specific option in dropdown menu
	 */
	public static void selectDropDown(WebElement dropDown, String value) {
		new Select(dropDown).selectByVisibleText(value);
	}

	/**
	 * Create a screenshot of a webpage
	 *
	 * @param fileLocation
	 *            Path of screenshot directory
	 * @param fileName
	 *            File name of screenshot
	 * @param driver
	 *            WebDriver for Chrome, FireFox, or IE
	 * @param logger
	 *            Log4j logger
	 * @return True if screenshot is successful or False if screenshot failed
	 */
	public static boolean takeScreenshot(String fileLocation, String fileName, WebDriver driver, Logger logger) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(fileLocation + "/" + fileName + ".png"));
		} catch (IOException e) {
			logger.warn("Screenshot " + fileName + " was not captured to disk correctly.");
			return false;
		}
		return true;
	}
}
