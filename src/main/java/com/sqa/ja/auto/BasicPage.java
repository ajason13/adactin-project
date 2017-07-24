/**
 * File Name: BasicPage.java<br>
 * Alvarez, Jason<br>
 * Java Boot Camp Exercise<br>
 * Instructor: Jean-francois Nepton<br>
 * Created: Jul 1, 2017
 */
package com.sqa.ja.auto;

import org.openqa.selenium.support.*;

/**
 * BasicPage - Foundation for Page Object Model
 *
 * @author Alvarez, Jason
 * @version 1.0.0
 * @since 1.0
 */
public class BasicPage extends Core {

	/**
	 * Constructor to setup a basic page model
	 *
	 * @param test
	 *            BasicTest instance
	 */
	public BasicPage(BasicTest test) {
		super(test);
		PageFactory.initElements(getDriver(), this);
	}
}
