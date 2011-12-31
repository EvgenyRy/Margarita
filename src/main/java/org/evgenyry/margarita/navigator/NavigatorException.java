package org.evgenyry.margarita.navigator;

import org.openqa.selenium.WebDriver;

public class NavigatorException extends RuntimeException {

    private static final long serialVersionUID = -6504652081816685144L;

    /**
     * @param message
     */
    public NavigatorException(String message, WebDriver driver) {
	super(createMessage(message, driver));
    }

    /**
     * @param message
     * @param cause
     */
    public NavigatorException(String message, Throwable cause, WebDriver driver) {
	super(createMessage(message, driver), cause);
    }

    private static String createMessage(String message, WebDriver driver) {
	if (driver != null) {
	    return message + "\nCurrent page URL: " + driver.getCurrentUrl() + "\nCurrent page title: "
		    + driver.getTitle();
	    // + "\nCurrent page source: \n" + driver.getPageSource());
	} else {
	    return message;
	}
    }
}
