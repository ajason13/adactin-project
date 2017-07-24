package com.sqa.ja.auto;

import java.util.concurrent.*;

import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.remote.*;
import org.testng.annotations.*;

/**
 * BasicTest - Initializes browers before each test class and setup/teardown
 * before/after each test method
 *
 * @author Alvarez, Jason
 * @version 1.0.0
 * @since 1.0
 */
public class BasicTest extends Core {

	/**
	 * Constructor to initialize Core constructor
	 *
	 * @param baseURL
	 *            URL of the website
	 */
	public BasicTest(String baseUrl) {
		super(baseUrl);
	}

	/**
	 * Initiallize test class to use Chrome browswer
	 */
	@BeforeClass(enabled = false, groups = "chrome")
	public void setUpChrome() {
		System.out.println("Setup Chrome");
		// Set system property to use Chrome driver
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		// Setup the driver to use Chrome
		setDriver(new ChromeDriver());
		// Set an implicit wait of 30 second (If pass 30 seconds to find
		// element, fail test case)
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Maximize the window
		getDriver().manage().window().maximize();
		// Goto Base URL
		getDriver().get(getBaseURL());
	}

	/**
	 * Initiallize test class to use FireFox browswer
	 */
	@BeforeClass(enabled = true, groups = "firefox")
	public void setUpFirefox() {
		System.out.println("Setup Firefox");
		// Setup the driver to use Firefox
		setDriver(new FirefoxDriver());
		// Set an implicit wait of 30 second (If pass 30 seconds to find
		// element, fail test case)
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Maximize the window
		getDriver().manage().window().maximize();
		// Goto Base URL
		getDriver().get(getBaseURL());
	}

	/**
	 * Initiallize test class to use IE browswer
	 */
	@BeforeClass(enabled = false, groups = "ie")
	public void setUpIE() {
		System.out.println("Setup IE");
		// Set capability of IE driver to Ignore all zones browser protected
		// mode settings.
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		// Set system property to use IE driver
		System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
		// Initialize InternetExplorerDriver Instance using new capability.
		setDriver(new InternetExplorerDriver(caps));
		// Set an implicit wait of up to 30 seconds
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Maximize the window
		getDriver().manage().window().maximize();
	}

	/**
	 * Runs before each test method
	 */
	@BeforeMethod()
	public void setupTest() {
		// Goto Base URL
		getDriver().get(getBaseURL());
	}

	/**
	 * Runs after each test method
	 *
	 * @throws Exception
	 */
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		// Quit all instances of the bowser
		getDriver().quit();
		// Close method would close all instances
	}
	// @Test
	// public void test1() {
	// getDriver().get(getBaseURL());
	// this.takeScreenshot(); // Auto Test 01
	// this.takeScreenshot("Test Case 01"); // Test Case 01
	// this.takeScreenshot(); // Test Case 02
	// this.takeScreenshot(); // Test Case 03
	// this.testingName = "Adactin";
	// this.takeScreenshot("Adactin Test"); // Adactin Test
	// this.takeScreenshot(); // Adactin Test 04
	// AutoBasics.takeScreenshot("screenshots", "Adactin Test", getDriver(),
	// getLog());
	// }
}
