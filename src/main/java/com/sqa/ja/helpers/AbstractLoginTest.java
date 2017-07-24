/**
 * File Name: AbstractLoginTest.java<br>
 * Alvarez, Jason<br>
 * Java Boot Camp Exercise<br>
 * Instructor: Jean-francois Nepton<br>
 * Created: Jul 1, 2017
 */
package com.sqa.ja.helpers;

import org.testng.annotations.*;

import com.sqa.ja.auto.*;

/**
 * AbstractLoginTest - Abstract class for testing login and logout
 *
 * @author Alvarez, Jason
 * @version 1.0.0
 * @since 1.0
 */
public abstract class AbstractLoginTest extends BasicTest {

	/**
	 * Constructor for passing URL to test to BasicTest
	 *
	 * @param baseUrl
	 *            Website to test
	 */
	public AbstractLoginTest(String baseUrl) {
		super(baseUrl);
	}

	/**
	 * Login function
	 *
	 * @param username
	 * @param password
	 */
	abstract public void login(String username, String password);

	/**
	 * Logout function
	 */
	abstract public void logout();

	/**
	 * Before any tests, grab username and password from a config file to login
	 */
	@BeforeClass
	public void setupLogin() {
		login(getProp("username"), getProp("password"));
	}

	/**
	 * After all the tests, logout
	 */
	@AfterClass
	public void setupLogout() {
		logout();
	}
}
